package com.unrealdinnerbone.simplefireworks.firework;

import com.unrealdinnerbone.simplefireworks.api.firework.IFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumFireworkEffect;

import java.util.ArrayList;
import java.util.List;

public class SimpleFireworkBase implements IFirework {

    private List<EnumFireworkEffect> fireworkEffects;
    private EnumExplodeEffect explodeEffect;
    private List<Integer> colors;
    private List<Integer> fadeColors;

    public SimpleFireworkBase() {
        fireworkEffects = new ArrayList<>();
        explodeEffect = null;
        colors = new ArrayList<>();
        fadeColors = new ArrayList<>();
    }

    public void setFireworkEffects(List<EnumFireworkEffect> fireworkEffects) {
        this.fireworkEffects = fireworkEffects;
    }

    public void addFireworkEffect(EnumFireworkEffect fireworkEffect) {
        if (!this.fireworkEffects.contains(fireworkEffect)) {
            this.fireworkEffects.add(fireworkEffect);
        }
    }

    public void removeFireworkEffect(EnumFireworkEffect fireworkEffect) {
        this.fireworkEffects.remove(fireworkEffect);
    }

    public void setExplodeEffect(EnumExplodeEffect explodeEffect) {
        this.explodeEffect = explodeEffect;
    }

    public void setColors(List<Integer> colors) {
        this.colors = colors;
    }

    public void addColor(Integer fireworkColor) {
        if (!this.colors.contains(fireworkColor)) {
            this.colors.add(fireworkColor);
        }
    }

    public void removeColor(Integer fireworkColor) {
        this.colors.remove(fireworkColor);
    }


    public void setFadeColors(List<Integer> fadeColors) {
        this.fadeColors = fadeColors;
    }

    public void addFadeColor(Integer fireworkColor) {
        if (!this.fadeColors.contains(fireworkColor)) {
            this.fadeColors.add(fireworkColor);
        }
    }

    public void removeFadeColor(Integer fireworkColor) {
        this.fadeColors.remove(fireworkColor);
    }

    @Override
    public List<EnumFireworkEffect> getFireworkEffects() {
        return fireworkEffects;
    }

    @Override
    public EnumExplodeEffect getExplodedEffect() {
        return explodeEffect;
    }

    @Override
    public List<Integer> getBrustColors() {
        return colors;
    }

    @Override
    public List<Integer> getFadeColors() {
        return fadeColors;
    }
}




