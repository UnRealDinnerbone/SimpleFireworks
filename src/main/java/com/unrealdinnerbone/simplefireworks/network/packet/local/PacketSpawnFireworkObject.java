package com.unrealdinnerbone.simplefireworks.network.packet.local;

import com.unrealdinnerbone.simplefireworks.api.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumFireworkEffect;
import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkColor;
import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;
import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkColor;
import com.unrealdinnerbone.simplefireworks.lib.FacingUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PacketSpawnLetterLocal implements IMessage {

    private IFireworkObject object;
    private BlockPos blockPos;

    public PacketSpawnLetterLocal() {  }


    public PacketSpawnLetterLocal(IFireworkObject lettter, BlockPos blockPos) {
        this.object = lettter;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        CharSequence objectID = buf.readCharSequence(length, Charset.forName("utf-8"));
        this.blockPos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        CharSequence objectID = object.getID();
        buf.writeInt(objectID.length());
        buf.writeCharSequence(objectID, Charset.forName("utf-8"));
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
}
