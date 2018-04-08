package com.unrealdinnerbone.simplefireworks.lib;

import com.unrealdinnerbone.simplefireworks.network.PacketSpawnFireworkObject;
import com.unrealdinnerbone.yaum.network.PacketHandler;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class SimpleFireworkHelper
{
    public static void spawnFireworkObject(CharSequence objectName, BlockPos pos, EnumFacing facing, int dim) {
        PacketHandler.INSTANCE.sendToAllAround(new PacketSpawnFireworkObject(objectName, pos, facing), new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), 64));
    }
}
