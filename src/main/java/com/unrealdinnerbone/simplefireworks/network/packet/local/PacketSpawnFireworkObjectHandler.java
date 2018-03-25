package com.unrealdinnerbone.simplefireworks.network.packet.local;

import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.FireworkObject;
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
        if (message.isGood()) {
            EnumFacing facing = message.getFacing();
            FireworkObject fireworkObject = message.getObject();
            BlockPos pos = message.getBlockPos().add(0, fireworkObject.getObjectArray().length * 5, 0);

            for (String[] row : fireworkObject.getObjectArray()) {
                pos = pos.add(0, -5, 0);
                for (String key : row) {
                    String fireworkName = fireworkObject.getFireworkNameFormIdentifier(key);
                    if(fireworkName != null) {
                        if(SimpleFirework.getFireworkParser().getFireworkObjects().containsKey(fireworkName)) {
                            FireworkParser.FireworkWrapper wrapper = SimpleFirework.getFireworkParser().getFireworkObjects().get(fireworkName);
                            wrapper.spawnAllFireworks(pos, 0, 0, 0);
                        }else {
                            //Todo Better Message
                            SimpleFirework.LOG_HELPER.error("Firework is not there " + fireworkName);
                        }
                    } else {

                        //Todo Better Message for when key is set but no identifer found
                        SimpleFirework.LOG_HELPER.debug("No ");
                    }
                    pos = pos.add(facing.getFrontOffsetX() * 5, 0, facing.getFrontOffsetZ() * 5);
                }
                pos = pos.add(row.length * -5 * facing.getFrontOffsetX(), 0, row.length * -5 * facing.getFrontOffsetZ());
            }
        } else {
            ctx.getClientHandler().handleChat(new SPacketChat(new TextComponentString("Error Test")));
        }
        return null;
    }
}
