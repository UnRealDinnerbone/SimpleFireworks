package com.unrealdinnerbone.simplefireworks.api.firework;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class FireworkBase {

    private List<EnumFireworkEffect> fireworkEffects;
    private EnumExplodeEffect explodeEffect;
    private List<Integer> colors;
    private List<Integer> fadeColors;

    public FireworkBase() {
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

    public List<EnumFireworkEffect> getFireworkEffects() {
        return fireworkEffects;
    }

    public EnumExplodeEffect getExplodedEffect() {
        return explodeEffect;
    }

    public List<Integer> getBrustColors() {
        return colors;
    }

    public List<Integer> getFadeColors() {
        return fadeColors;
    }

    public NBTTagCompound getExplodeCompound()  {
        NBTTagCompound compound = new NBTTagCompound();
        //Todo do i need this?
//        compound.setInteger("Flight", 1);
        NBTTagCompound explosionsCompound = new NBTTagCompound();
        explosionsCompound.setInteger("Type", getExplodedEffect().getExplodeID() - 1);
        this.getFireworkEffects().forEach(effect -> explosionsCompound.setInteger(effect.getEffectName(), 1));

        int burstColorsArray[] = getBrustColors().stream().mapToInt(Integer::intValue).toArray();
        int fadeColorArray[] = getFadeColors().stream().mapToInt(Integer::intValue).toArray();

        explosionsCompound.setIntArray("Colors", burstColorsArray);
        explosionsCompound.setIntArray("FadeColors", fadeColorArray);
        NBTTagList nbtList = new NBTTagList();
        nbtList.appendTag(explosionsCompound);
        compound.setTag("Explosions", nbtList);

        return compound;
    }

    @SideOnly(Side.CLIENT)
    public void spawnFirework(BlockPos pos, int xSpeed, int ySpeed, int zSpeed) {
        Minecraft.getMinecraft().world.makeFireworks(pos.getX(), pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed, getExplodeCompound());
    }
}




