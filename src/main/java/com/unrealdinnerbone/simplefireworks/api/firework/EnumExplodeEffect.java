package com.unrealdinnerbone.simplefireworks.api.firework;

public enum EnumExplodeEffect
{
    SMALL_BALL(0),
    LAVAGE_BALL(2),
    STAR(3),
    CREEPER_FACE(4),
    SPARKLE(5);

    private int explodeID;

    EnumExplodeEffect(int i) {
        this.explodeID = i;
    }

    public int getExplodeID() {
        return explodeID;
    }


    public static EnumExplodeEffect getEffectFormID(int id) {
        for(EnumExplodeEffect enumExplodeEffect: values()) {
            if(enumExplodeEffect.explodeID == id) {
                return enumExplodeEffect;
            }
        }
        return null;
    }
}
