package com.unrealdinnerbone.simplefireworks.network.packet;

import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;
import com.unrealdinnerbone.simplefireworks.lib.FacingUtil;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumFireworkEffect;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PacketSpawnFirework implements IMessage {

    private List<Integer> colors;
    private List<Integer> fadeColors;
    private List<EnumFireworkEffect> enumFireworkEffects;
    private BlockPos blockPos;
    private CharSequence fireworkObjectKey;
    private EnumExplodeEffect enumExplodeEffect;
    private EnumFacing facing;

    public PacketSpawnFirework() {  }


    public PacketSpawnFirework(String fireworkObjectKey, BlockPos blockPos, EnumExplodeEffect enumExplodeEffect, EnumFacing facing, List<Integer> colors, List<Integer> fadeColors, List<EnumFireworkEffect> effects) {
        this.blockPos = blockPos;
        this.fireworkObjectKey = fireworkObjectKey;
        this.colors = colors;
        this.fadeColors = fadeColors;
        this.enumExplodeEffect = enumExplodeEffect;
        this.facing = facing;
        this.enumFireworkEffects = effects;
    }

    public PacketSpawnFirework(IFireworkObject lettter, BlockPos blockPos, EnumExplodeEffect enumExplodeEffect, EnumFacing facing, List<Integer> colors, List<Integer> fadeColors, List<EnumFireworkEffect> effects) {
        this.blockPos = blockPos;
        this.fireworkObjectKey = fireworkObjectKey;
        this.colors = colors;
        this.fadeColors = fadeColors;
        this.enumExplodeEffect = enumExplodeEffect;
        this.facing = facing;
        this.enumFireworkEffects = effects;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.facing = FacingUtil.getFacingFormID(buf.readInt());
        this.enumExplodeEffect = EnumExplodeEffect.getEffectFormID(buf.readInt());
        blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        fireworkObjectKey = buf.readCharSequence(buf.readInt(), Charset.forName("utf-8"));
        int colorsX = buf.readInt();
        int fadeColorsX = buf.readInt();
        int fireworkEffectX = buf.readInt();
        colors = new ArrayList<>();
        fadeColors = new ArrayList<>();
        enumFireworkEffects = new ArrayList<>();

        for(int x = 1; x <= colorsX; x++) {
            colors.add(buf.readInt());
        }

        for(int x = 1; x <= fadeColorsX; x++) {
            fadeColors.add(buf.readInt());
        }

        for(int x = 1; x <= fireworkEffectX; x++) {
            enumFireworkEffects.add(EnumFireworkEffect.getEffectFormID(buf.readInt()));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(facing.getIndex());
        buf.writeInt(enumExplodeEffect.getExplodeID());
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeInt(fireworkObjectKey.length());
        buf.writeCharSequence(fireworkObjectKey, Charset.forName("utf-8"));
        buf.writeInt(colors.size());
        buf.writeInt(fadeColors.size());
        buf.writeInt(enumFireworkEffects.size());
        colors.stream().mapToInt(Integer::intValue).forEach(buf::writeInt);
        fadeColors.stream().mapToInt(Integer::intValue).forEach(buf::writeInt);
        enumFireworkEffects.stream().mapToInt(EnumFireworkEffect::getExplodeID).forEach(buf::writeInt);
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public List<Integer> getFadeColors() {
        return fadeColors;
    }

    public List<Integer> getColors() {
        return colors;
    }

    public CharSequence getFireworkObjectKey() {
        return fireworkObjectKey;
    }

    public EnumExplodeEffect getEnumExplodeEffect() {
        return enumExplodeEffect;
    }

    public List<EnumFireworkEffect> getEnumFireworkEffects() {
        return enumFireworkEffects;
    }

    public EnumFacing getFacing() {
        return facing;
    }
}
