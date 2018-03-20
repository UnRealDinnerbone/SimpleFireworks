package com.unrealdinnerbone.simplefireworks.lib.firework;

import com.unrealdinnerbone.simplefireworks.lib.EnumUtil;
import com.unrealdinnerbone.simplefireworks.lib.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class Firework
{
    private List<EnumFireworkEffect> fireworkEffects;
    private EnumExplodeEffect explodeEffect;
    private List<FireworkColor> colors;
    private List<FireworkColor> fadeColors;

    public Firework() {
        fireworkEffects = new ArrayList<>();
        explodeEffect = null;
        colors = new ArrayList<>();
        fadeColors = new ArrayList<>();
    }

    public void setFireworkEffects(List<EnumFireworkEffect> fireworkEffects) {
        this.fireworkEffects = fireworkEffects;
    }

    public void addFireworkEffect(EnumFireworkEffect fireworkEffect) {
        if(!this.fireworkEffects.contains(fireworkEffect)) {
            this.fireworkEffects.add(fireworkEffect);
        }
    }

    public void removeFireworkEffect(EnumFireworkEffect fireworkEffect) {
        this.fireworkEffects.remove(fireworkEffect);
    }

    public void setExplodeEffect(EnumExplodeEffect explodeEffect) {
        this.explodeEffect = explodeEffect;
    }

    public void setColors(List<FireworkColor> colors) {
        this.colors = colors;
    }

    public void addColor(FireworkColor fireworkColor) {
        if(!this.colors.contains(fireworkColor)) {
            this.colors.add(fireworkColor);
        }
    }

    public void removeColor(FireworkColor fireworkColor) {
        this.colors.remove(fireworkColor);
    }


    public void setFadeColors(List<FireworkColor> fadeColors) {
        this.fadeColors = fadeColors;
    }

    public void addFadeColor(FireworkColor fireworkColor) {
        if(!this.fadeColors.contains(fireworkColor)) {
            this.fadeColors.add(fireworkColor);
        }
    }

    public void removeFadeColor(FireworkColor fireworkColor) {
        this.fadeColors.remove(fireworkColor);
    }


    public List<EnumFireworkEffect> getFireworkEffects() {
        return fireworkEffects;
    }

    public EnumExplodeEffect getExplodeEffect() {
        return explodeEffect;
    }

    public List<FireworkColor> getColors() {
        return colors;
    }

    public List<FireworkColor> getFadeColors() {
        return fadeColors;
    }



    public NBTTagCompound getExplodeCompound()  {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("Flight", 1);
        NBTTagCompound explosionsCompound = new NBTTagCompound();
        explosionsCompound.setInteger("Type", explodeEffect.getExplodeID() - 1);
        this.fireworkEffects.forEach(effect -> explosionsCompound.setInteger(effect.getEffectName(), 1));

        int colorArray[] = new int[colors.size()];
        int fadeColorArray[] = new int[fadeColors.size()];

        for (int i = 0; i < colors.size(); i++) {
            FireworkColor fireworkColor = colors.get(i);
            colorArray[i] = fireworkColor.getDecimalCode();
        }

        for (int i = 0; i < fadeColors.size(); i++) {
            FireworkColor fireworkColor = fadeColors.get(i);
            fadeColorArray[i] = fireworkColor.getDecimalCode();
        }


        explosionsCompound.setIntArray("Colors", colorArray);
        explosionsCompound.setIntArray("FadeColors", fadeColorArray);
        NBTTagList nbtList = new NBTTagList();
        nbtList.appendTag(explosionsCompound);
        compound.setTag("Explosions", nbtList);

        return compound;
    }

    public static Firework getRandom(int maxColors, int maxFadeColors) {
        Firework firework = new Firework();
        for (int i1 = 0; i1 < MathHelper.getRandomInt(1, maxColors); i1++) {
            FireworkColor random = FireworkColor.getRandom();
            firework.addColor(random);
        }
        for (int i = 0; i < MathHelper.getRandomInt(1, maxFadeColors); i++) {
            FireworkColor random = FireworkColor.getRandom();
            firework.addFadeColor(random);
        }
        if (MathHelper.getRandomInt(0, 1) == 1) {
            firework.addFireworkEffect(EnumFireworkEffect.TRAIL);
        }
        if (MathHelper.getRandomInt(0, 1) == 1) {
            firework.addFireworkEffect(EnumFireworkEffect.FLICKER);
        }
        firework.setExplodeEffect(EnumUtil.randomEnum(EnumExplodeEffect.class));
        return firework;
    }

    @SideOnly(Side.CLIENT)
    public void spawnFirework(BlockPos pos, int xSpeed, int ySpeed, int zSpeed) {
        Minecraft.getMinecraft().world.makeFireworks(pos.getX(), pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed, getExplodeCompound());
    }

    @SideOnly(Side.CLIENT)
    public static void spawnFirework(Firework firework, BlockPos pos, int xSpeed, int ySpeed, int zSpeed) {
        firework.spawnFirework(pos, xSpeed, ySpeed, zSpeed);
    }

}
