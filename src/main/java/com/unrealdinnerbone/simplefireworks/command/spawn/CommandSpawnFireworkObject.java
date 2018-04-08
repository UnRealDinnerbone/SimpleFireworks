package com.unrealdinnerbone.simplefireworks.command.spawn;
import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.lib.SimpleFireworkHelper;
import com.unrealdinnerbone.yaum.api.firework.FireworkObject;
import com.unrealdinnerbone.simplefireworks.command.base.FireworkCommandBase;
import com.unrealdinnerbone.yaum.libs.helpers.FireworkHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommandSpawnFireworkObject extends FireworkCommandBase {
    @Override
    public String getName() {
        return "spawnObject";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length >= 5) {
            int dim = sender.getEntityWorld().provider.getDimension();
            String letterName = args[0];
            double x = parseDouble(sender.getPositionVector().x, args[1], true);
            double y = parseDouble(sender.getPositionVector().y, args[2], false);
            double z = parseDouble(sender.getPositionVector().z, args[3], true);
            EnumFacing facing = parseFacing(args[4]);
            SimpleFireworkHelper.spawnFireworkObject(letterName, new BlockPos(x, y, z), facing, dim);
        } else {
            sendUsageMessage(sender);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 1) {
            return getListOfStringsMatchingLastWord(args, SimpleFirework.getObjectParser().getFireworkObjects().stream().map(FireworkObject::getID).collect(Collectors.toList()));
        } else if(args.length > 1 && args.length < 5) {
            return getTabCompletionCoordinate(args, 1, targetPos);
        } else if (args.length == 5) {
            return getListOfStringsMatchingLastWord(args, Arrays.stream(EnumFacing.values()).map(EnumFacing::getName).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }
}
