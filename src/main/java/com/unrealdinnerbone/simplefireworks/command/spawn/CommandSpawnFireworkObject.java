package com.unrealdinnerbone.simplefireworks.command.spawn;
import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;
import com.unrealdinnerbone.simplefireworks.command.base.FireworkCommandBase;
import com.unrealdinnerbone.simplefireworks.lib.FireworkHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommandSpawnFireworkObject extends FireworkCommandBase {
    @Override
    public String getName() {
        return "spawnObject";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws NumberInvalidException {
        if(args.length >= 5) {
            int dim = sender.getEntityWorld().provider.getDimension();
            String letterName = args[0];
            double x = parseDouble(sender.getPositionVector().x, args[1], true);
            double y = parseDouble(sender.getPositionVector().y, args[2], false);
            double z = parseDouble(sender.getPositionVector().z, args[3], true);
            FireworkHelper.spawnFireworkObject(letterName, new BlockPos(x, y, z), dim);
        } else {
            sendUsageMessage(sender);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 1) {
            return getListOfStringsMatchingLastWord(args, SimpleFirework.getObjectParser().getFireworkObjects().stream().map(IFireworkObject::getID).collect(Collectors.toList()));
        } else if(args.length > 1 && args.length < 5) {
            return getTabCompletionCoordinate(args, 1, targetPos);
        } else if (args.length == 5) {
            return getListOfStringsMatchingLastWord(args, "true", "false");
        }
        return Collections.emptyList();
    }
}