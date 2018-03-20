package com.unrealdinnerbone.simplefireworks.network.packet;

import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.lib.firework.FireworkLetter;
import com.unrealdinnerbone.simplefireworks.lib.firework.Firework;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PackSpawnLetterMessageHandler implements IMessageHandler<PacketSpawnLetter, IMessage> {

    @Override
    public IMessage onMessage(PacketSpawnLetter message, MessageContext ctx) {
        Firework firework = new Firework();
        firework.setColors(message.getColors());
        firework.setFadeColors(message.getFadeColors());
        firework.setExplodeEffect(message.getEnumExplodeEffect());
        firework.setFireworkEffects(message.getEnumFireworkEffects());
        String[][] pattern = FireworkLetter.formLetter(message.getFireworkObjectKey().toString());
        if (pattern == null) {
            SimpleFirework.LOG_HELPER.error("Someone Tired to spawn a letter " +  message.getFireworkObjectKey() + " it has not been defined ");
            return null;
        }
        BlockPos pos = message.getBlockPos();

        pos =  pos.add(0,pattern.length * 5, 0);
        for(String[] row : pattern){
            pos = pos.add(0,-5, 0);
            for(String s : row) {
                if(!s.equalsIgnoreCase(" ")) {
                    firework.spawnFirework(pos, 0,0,0);
                }
                pos = pos.add(5, 0, 0);
            }
            pos = pos.add(row.length * -5, 0, 0);
        }
        return null;
    }
}
