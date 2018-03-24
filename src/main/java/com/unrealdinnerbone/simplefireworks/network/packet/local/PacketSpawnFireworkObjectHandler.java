package com.unrealdinnerbone.simplefireworks.network.packet.local;

import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.IFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;
import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkBase;
import com.unrealdinnerbone.simplefireworks.parsar.FireworkParser;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnFireworkObjectHandler implements IMessageHandler<PacketSpawnFireworkObject, IMessage> {

    @Override
    public IMessage onMessage(PacketSpawnFireworkObject message, MessageContext ctx) {
        if(message.isGood()) {
            EnumFacing facing = EnumFacing.NORTH;
            IFireworkObject fireworkObject = message.getObject();
            BlockPos pos = message.getBlockPos().add(0, fireworkObject.getObject().length * 5, 0);

            for(String[] row : fireworkObject.getObject()){
                pos = pos.add(0,-5, 0);
                for(String s : row) {
                    if(!s.equalsIgnoreCase(" ")) {
                        for(String key: fireworkObject.getIdentifiers().keySet()) {
                            if(SimpleFirework.getObjectParser().contains(key)) {
                                FireworkParser.FireworkWrapper wrapper = SimpleFirework.getFireworkParser().getFireworkObjects().get(fireworkObject.getIdentifiers().get(key));
                                BlockPos finalPos = pos;
                                for (SimpleFireworkBase firework : wrapper.getFireworks()) {
                                    firework.spawnFirework(finalPos, 0, 0, 0);
                                }
                            }else {
                                SimpleFirework.LOG_HELPER.error("Firework is not there " + key);
                            }
                        }
                    }
                    pos = pos.add(facing.getFrontOffsetX() * 5, 0, facing.getFrontOffsetZ() * 5);
                }
                pos = pos.add(row.length * -5 * facing.getFrontOffsetX(), 0, row.length * -5 * facing.getFrontOffsetZ());
            }
        }else {
            ctx.getClientHandler().handleChat(new SPacketChat(new TextComponentString("Error Test")));
        }
        return null;
    }
}
