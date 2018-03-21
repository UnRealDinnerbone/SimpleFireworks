package com.unrealdinnerbone.simplefireworks.lib;

public class EnumUtil
{
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        return clazz.getEnumConstants()[MathHelper.getRandom().nextInt(clazz.getEnumConstants().length)];
    }
}
