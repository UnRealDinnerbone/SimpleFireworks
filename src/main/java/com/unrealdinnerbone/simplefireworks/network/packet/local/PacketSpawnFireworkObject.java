package com.unrealdinnerbone.simplefireworks.network.packet.local;

import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.FireworkObject;
import com.unrealdinnerbone.simplefireworks.lib.FacingUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;

public class PacketSpawnFireworkObject implements IMessage {

    private static Charset UTF_8 = Charset.forName("utf-8");
    private CharSequence charSequence;
    private BlockPos blockPos;
    private EnumFacing facing;

    private boolean isGood;
    private FireworkObject object;

    public PacketSpawnFireworkObject() {

    }


    public PacketSpawnFireworkObject(CharSequence objectName, BlockPos pos, EnumFacing facing) {
        this.charSequence = objectName;
        this.blockPos = pos;
        this.facing = facing;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        charSequence = buf.readCharSequence(length, UTF_8);
        for (FireworkObject fireworkObject : SimpleFirework.getObjectParser().getFireworkObjects()) {
            if (fireworkObject.getID().equalsIgnoreCase(String.valueOf(charSequence))) {
                this.object = fireworkObject;
            }
        }
        isGood = object != null;
        this.blockPos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
        this.facing = FacingUtil.getFacingFormID(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(charSequence.length());
        buf.writeCharSequence(charSequence, UTF_8);
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeInt(facing.getIndex());
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public FireworkObject getObject() {
        return object;
    }

    public boolean isGood() {
        return isGood;
    }

    public EnumFacing getFacing() {
        return facing;
    }
}
