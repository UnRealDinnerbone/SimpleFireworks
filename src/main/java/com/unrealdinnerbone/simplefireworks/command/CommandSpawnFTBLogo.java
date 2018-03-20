package com.unrealdinnerbone.simplefireworks.command;

import com.unrealdinnerbone.simplefireworks.config.FireworkConfig;
import com.unrealdinnerbone.simplefireworks.lib.FireworkHelper;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumFireworkEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.FireworkColor;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;

import java.util.ArrayList;
import java.util.List;


public class CommandSpawnFTBLogo extends CommandBase {

    @Override
    public String getName() {
        return "spawnFTB";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return getName();
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) {
        List<FireworkColor> red = new ArrayList<>();
        List<FireworkColor> green = new ArrayList<>();
        List<FireworkColor> blue = new ArrayList<>();
        List<EnumFireworkEffect> effects = new ArrayList<>();
        red.add(new FireworkColor(16711680));
        green.add(new FireworkColor(65280));
        blue.add(new FireworkColor(255));
        effects.add(EnumFireworkEffect.FLICKER);

        FireworkHelper.spawnFireworkObject("F", iCommandSender.getPosition().add(-15, 5, 0), iCommandSender.getEntityWorld().provider.getDimension(), FireworkConfig.general.enumExplodeEffect, red, red, effects);
        FireworkHelper.spawnFireworkObject("T", iCommandSender.getPosition().add(0, 20, 0), iCommandSender.getEntityWorld().provider.getDimension(), FireworkConfig.general.enumExplodeEffect, green, green, effects);
        FireworkHelper.spawnFireworkObject("B", iCommandSender.getPosition().add(15, 5, 0), iCommandSender.getEntityWorld().provider.getDimension(), FireworkConfig.general.enumExplodeEffect, blue, blue, effects);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if(sender instanceof CommandBlockBaseLogic) {
            return true;
        }else {
            return super.checkPermission(server, sender);
        }
    }
}
