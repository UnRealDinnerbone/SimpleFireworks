package com.unrealdinnerbone.simplefireworks.command;

import com.unrealdinnerbone.simplefireworks.command.base.FireworkCommandTreeBase;
import com.unrealdinnerbone.simplefireworks.command.reload.CommandReloadTree;
import com.unrealdinnerbone.simplefireworks.command.spawn.CommandSpawnFireworkObject;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandFireworkTree extends FireworkCommandTreeBase {

    public CommandFireworkTree() {
        super("simplefirework");
    }

    @Override
    public void addSubCommands() {
        this.addSubcommand(new CommandSpawnFireworkObject());
        this.addSubcommand(new CommandReloadTree());
    }

}