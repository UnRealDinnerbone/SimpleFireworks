package com.unrealdinnerbone.simplefireworks.network.packet;

import com.unrealdinnerbone.simplefireworks.lib.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumFireworkEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.FireworkColor;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PacketSpawnLetter implements IMessage {

    private List<FireworkColor> colors;
    private List<FireworkColor> fadeColors;
    private List<EnumFireworkEffect> enumFireworkEffects;
    private BlockPos blockPos;
    private CharSequence fireworkObjectKey;
    private EnumExplodeEffect enumExplodeEffect;

    public PacketSpawnLetter() {  }


    public PacketSpawnLetter(String fireworkObjectKey, BlockPos blockPos, EnumExplodeEffect enumExplodeEffect, List<FireworkColor> colors, List<FireworkColor> fadeColors, List<EnumFireworkEffect> effects) {
        this.blockPos = blockPos;
        this.fireworkObjectKey = fireworkObjectKey;
        this.colors = colors;
        this.fadeColors = fadeColors;
        this.enumExplodeEffect = enumExplodeEffect;
        this.enumFireworkEffects = effects;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
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
            colors.add(new FireworkColor(buf.readInt()));
        }

        for(int x = 1; x <= fadeColorsX; x++) {
            fadeColors.add(new FireworkColor(buf.readInt()));
        }

        for(int x = 1; x <= fireworkEffectX; x++) {
            enumFireworkEffects.add(EnumFireworkEffect.getEffectFormID(buf.readInt()));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(enumExplodeEffect.getExplodeID());
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeInt(fireworkObjectKey.length());
        buf.writeCharSequence(fireworkObjectKey, Charset.forName("utf-8"));
        buf.writeInt(colors.size());
        buf.writeInt(fadeColors.size());
        buf.writeInt(enumFireworkEffects.size());
        for(FireworkColor fireworkColor: colors) {
            buf.writeInt(fireworkColor.getDecimalCode());
        }
        for(FireworkColor fireworkColor: fadeColors) {
            buf.writeInt(fireworkColor.getDecimalCode());
        }
        for(EnumFireworkEffect fireworkEffect: enumFireworkEffects) {
            buf.writeInt(fireworkEffect.getExplodeID());
        }
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public List<FireworkColor> getFadeColors() {
        return fadeColors;
    }

    public List<FireworkColor> getColors() {
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
}
