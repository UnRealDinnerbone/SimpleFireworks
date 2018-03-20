package com.unrealdinnerbone.simplefireworks.lib.firework;

import com.unrealdinnerbone.simplefireworks.lib.MathHelper;

public class FireworkColor {

    public final static int MAX_VALUE = 16777215;

    private int decimalCode;

    public FireworkColor(int decimalCode) {
        this.decimalCode = decimalCode;
    }


    public static FireworkColor getRandom() {
        return new FireworkColor(MathHelper.getRandomInt(0, MAX_VALUE));
    }

    public int getDecimalCode() {
        return decimalCode;
    }

    public void setDecimalCode(int decimalCode) {
        this.decimalCode = decimalCode;
    }
}
