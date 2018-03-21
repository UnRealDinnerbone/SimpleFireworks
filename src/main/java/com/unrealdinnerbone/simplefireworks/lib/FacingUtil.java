package com.unrealdinnerbone.simplefireworks.lib;

import net.minecraft.util.EnumFacing;

import java.util.Arrays;

public class FacingUtil
{
    public static EnumFacing getFacingFormID(int id) {
        return Arrays.stream(EnumFacing.values()).filter(facing -> facing.getIndex() == id).findFirst().orElse(null);
    }
}
