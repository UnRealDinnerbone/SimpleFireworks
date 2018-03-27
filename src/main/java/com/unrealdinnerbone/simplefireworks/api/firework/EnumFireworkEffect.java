package com.unrealdinnerbone.simplefireworks.api.firework;

public enum EnumFireworkEffect
{
    FLICKER("Flicker", 0),
    TRAIL("Trail", 1);

    private final String effectName;
    private final int explodeID;

    EnumFireworkEffect(String effectName, int id) {
        this.effectName = effectName;
        this.explodeID = id;
    }

    public String getEffectName() {
        return effectName;
    }

    public static EnumFireworkEffect getEffectFormID(int id) {
        for(EnumFireworkEffect enumExplodeEffect: values()) {
            if(enumExplodeEffect.explodeID == id) {
                return enumExplodeEffect;
            }
        }
        return null;
    }

    public int getExplodeID() {
        return explodeID;
    }
}
