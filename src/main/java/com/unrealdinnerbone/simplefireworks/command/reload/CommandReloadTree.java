package com.unrealdinnerbone.simplefireworks.command.reload;

import com.unrealdinnerbone.simplefireworks.command.base.FireworkCommandTreeBase;
import com.unrealdinnerbone.simplefireworks.parsar.SimpleParser;

public class CommandReloadTree extends FireworkCommandTreeBase {

    public CommandReloadTree() {
        super("reload");
    }

    @Override
    public void addSubCommands() {
        SimpleParser.getSimpleParsers().stream().map(CommandReloadParser::new).forEach(this::addSubcommand);
    }
}
