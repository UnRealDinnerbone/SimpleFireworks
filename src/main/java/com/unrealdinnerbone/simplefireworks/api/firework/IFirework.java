package com.unrealdinnerbone.simplefireworks.api.firework;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public interface IFirework
{
    List<EnumFireworkEffect> getFireworkEffects();
    EnumExplodeEffect getExplodedEffect();
    List<Integer> getBrustColors();
    List<Integer> getFadeColors();

    default NBTTagCompound getExplodeCompound()  {
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
    default void spawnFirework(BlockPos pos, int xSpeed, int ySpeed, int zSpeed) {
        Minecraft.getMinecraft().world.makeFireworks(pos.getX(), pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed, getExplodeCompound());
    }

}
