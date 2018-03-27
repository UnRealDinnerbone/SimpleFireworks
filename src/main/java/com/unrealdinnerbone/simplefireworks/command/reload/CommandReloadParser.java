package com.unrealdinnerbone.simplefireworks.command.reload;

import com.unrealdinnerbone.simplefireworks.command.base.FireworkCommandBase;
import com.unrealdinnerbone.simplefireworks.parsar.SimpleParser;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandReloadParser extends FireworkCommandBase {

    private SimpleParser parser;

    public CommandReloadParser(SimpleParser parser) {
        this.parser = parser;
    }

    @Override
    public String getName() {
        return parser.getName();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        parser.scan();
        sender.sendMessage(new TextComponentString("Parser Reloaded"));
    }
}
