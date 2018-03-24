package com.unrealdinnerbone.simplefireworks.network.packet;

import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnFireworkHandler implements IMessageHandler<PacketSpawnFirework, IMessage> {

    @Override
    public IMessage onMessage(PacketSpawnFirework message, MessageContext ctx) {
        SimpleFireworkBase firework = new SimpleFireworkBase();
        firework.setColors(message.getColors());
        firework.setFadeColors(message.getFadeColors());
        firework.setExplodeEffect(message.getEnumExplodeEffect());
        firework.setFireworkEffects(message.getEnumFireworkEffects());
//        String[][] pattern = SimpleFirework.getFireworkParser().getFireworkObjects().get(message.getFireworkObjectKey().toString()).;
//        if (pattern == null) {
//            SimpleFirework.LOG_HELPER.error("Someone Tired to spawn a letter " +  message.getFireworkObjectKey() + " it has not been defined ");
//            return null;
//        }
//        BlockPos pos = message.getBlockPos().add(0, pattern.length * 5, 0);
//
//        for(String[] row : pattern){
//            pos = pos.add(0,-5, 0);
//            for(String s : row) {
//                if(!s.equalsIgnoreCase(" ")) {
//                    firework.spawnFirework(pos, 0,0,0);
//                }
//                pos = pos.add(message.getFacing().getFrontOffsetX() * 5, 0, message.getFacing().getFrontOffsetZ() * 5);
//            }
//            pos = pos.add(row.length * -5 * message.getFacing().getFrontOffsetX(), 0, row.length * -5 * message.getFacing().getFrontOffsetZ());
//        }
        return null;
    }
}
