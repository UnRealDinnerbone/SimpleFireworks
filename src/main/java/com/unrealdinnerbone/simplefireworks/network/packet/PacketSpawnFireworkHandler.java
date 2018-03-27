package com.unrealdinnerbone.simplefireworks.network.packet;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnFireworkHandler implements IMessageHandler<PacketSpawnFirework, IMessage> {

    @Override
    public IMessage onMessage(PacketSpawnFirework message, MessageContext ctx) {
        message.getFireworkBase().spawnFirework(message.getBlockPos(), 0, 0, 0);
        return null;
    }
}
