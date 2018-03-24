package com.unrealdinnerbone.simplefireworks.network.packet.local;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PackSpawnLetterLocalMessageHandler implements IMessageHandler<PacketSpawnLetterLocal, IMessage> {

    @Override
    public IMessage onMessage(PacketSpawnLetterLocal message, MessageContext ctx) {
        return null;
    }
}
