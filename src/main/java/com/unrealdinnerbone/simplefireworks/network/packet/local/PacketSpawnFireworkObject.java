package com.unrealdinnerbone.simplefireworks.network.packet.local;

import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;

public class PacketSpawnFireworkObject implements IMessage {

    private static Charset UTF_8 = Charset.forName("utf-8");
    private CharSequence charSequence;
    private BlockPos blockPos;

    private boolean isGood;
    private IFireworkObject object;

    public PacketSpawnFireworkObject() {  }


    public PacketSpawnFireworkObject(CharSequence charSequence, BlockPos blockPos) {
        this.charSequence = charSequence;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        charSequence = buf.readCharSequence(length, UTF_8);
        SimpleFirework.getObjectParser().getFireworkObjects().stream().filter(object -> object.getID().equalsIgnoreCase(String.valueOf(charSequence))).forEach(object -> this.object = object);
        isGood = object != null;
        this.blockPos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(charSequence.length());
        buf.writeCharSequence(charSequence, UTF_8);
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public IFireworkObject getObject() {
        return object;
    }

    public boolean isGood() {
        return isGood;
    }
}
