package com.unrealdinnerbone.simplefireworks.lib;

import java.util.concurrent.ThreadLocalRandom;

public class MathHelper extends net.minecraft.util.math.MathHelper
{

    public static int getRandomInt(int min, int max) {
        return getRandom().nextInt(min, max + 1);
    }

    public static double getRandomDouble(int min, int max) {
       return min + (max - min) * getRandom().nextDouble();
    }

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }


    public static int getRandomHexColor() {
        return getRandomInt(0, 16777215);
    }
}

