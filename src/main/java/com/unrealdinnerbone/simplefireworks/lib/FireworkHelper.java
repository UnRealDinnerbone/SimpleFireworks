package com.unrealdinnerbone.simplefireworks.lib;

import com.unrealdinnerbone.simplefireworks.api.firework.*;
import com.unrealdinnerbone.simplefireworks.config.FireworkConfig;
import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkBase;
import com.unrealdinnerbone.simplefireworks.network.NetworkManager;
import com.unrealdinnerbone.simplefireworks.network.packet.PacketSpawnFirework;
import com.unrealdinnerbone.simplefireworks.network.packet.local.PacketSpawnFireworkObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.List;

public class FireworkHelper
{
    public static void spawnFireworkObject(String objectID, BlockPos pos, int dim, EnumExplodeEffect enumExplodeEffect, EnumFacing facing, List<Integer> colors, List<Integer> fadeColors, List<EnumFireworkEffect> effects) {
        NetworkManager.INSTANCE.sendToAllAround(new PacketSpawnFirework(objectID, pos, enumExplodeEffect, facing, colors, fadeColors, effects), new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), FireworkConfig.general.fireworkPacketRange));
    }
    public static void spawnFireworkObject(CharSequence objectName, BlockPos pos, int dim) {
        NetworkManager.INSTANCE.sendToAllAround(new PacketSpawnFireworkObject(objectName, pos), new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), FireworkConfig.general.fireworkPacketRange));
    }

    public static IFirework getRandom(int maxColors, int maxFadeColors) {
        SimpleFireworkBase firework = new SimpleFireworkBase();
        for (int i1 = 0; i1 < MathHelper.getRandomInt(1, maxColors); i1++) {
            firework.addColor(MathHelper.getRandomHexColor());
        }
        for (int i = 0; i < MathHelper.getRandomInt(1, maxFadeColors); i++) {
            firework.addFadeColor(MathHelper.getRandomHexColor());
        }
        if (MathHelper.getRandomInt(0, 1) == 1) {
            firework.addFireworkEffect(EnumFireworkEffect.TRAIL);
        }
        if (MathHelper.getRandomInt(0, 1) == 1) {
            firework.addFireworkEffect(EnumFireworkEffect.FLICKER);
        }
        firework.setExplodeEffect(EnumUtil.randomEnum(EnumExplodeEffect.class));
        return firework;
    }
}
