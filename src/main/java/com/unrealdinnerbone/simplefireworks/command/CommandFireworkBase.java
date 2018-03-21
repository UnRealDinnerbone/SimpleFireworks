package com.unrealdinnerbone.simplefireworks.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandFireworkBase extends CommandTreeBase {


    public CommandFireworkBase() {
        this.addSubcommand(new CommandSpawnFTBLogo());
        this.addSubcommand(new CommandSpawnLetter());
    }

    @Override
    public String getName() {
        return "simplefirework";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

}