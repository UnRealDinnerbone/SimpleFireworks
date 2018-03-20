package com.unrealdinnerbone.simplefireworks.lib;

import com.unrealdinnerbone.simplefireworks.config.FireworkConfig;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumFireworkEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.FireworkColor;
import com.unrealdinnerbone.simplefireworks.network.NetworkManager;
import com.unrealdinnerbone.simplefireworks.network.packet.PacketSpawnLetter;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.List;

public class FireworkHelper
{
    public static void spawnFireworkObject(String objectID, BlockPos pos, int dim, EnumExplodeEffect enumExplodeEffect, List<FireworkColor> colors, List<FireworkColor> fadeColors, List<EnumFireworkEffect> effects) {
        NetworkManager.INSTANCE.sendToAllAround(new PacketSpawnLetter(objectID, pos, enumExplodeEffect, colors, fadeColors, effects), new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), FireworkConfig.general.fireworkPacketRange));
    }
}
