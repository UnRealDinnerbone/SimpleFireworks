package com.unrealdinnerbone.simplefireworks.lib;

public class EnumUtil
{
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = MathHelper.getRandom().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
