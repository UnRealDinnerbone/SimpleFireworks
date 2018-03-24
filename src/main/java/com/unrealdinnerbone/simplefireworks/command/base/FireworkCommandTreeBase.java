package com.unrealdinnerbone.simplefireworks.command.base;

import com.unrealdinnerbone.simplefireworks.lib.Reference;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public abstract class FireworkCommandTreeBase extends CommandTreeBase {

    private final String name;

    public FireworkCommandTreeBase(String name) {
        this.name = name;
        addSubCommands();
    }

    public abstract void addSubCommands();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return Reference.MOD_ID + "commands." + getName() + ".usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

}