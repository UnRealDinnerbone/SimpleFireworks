package com.unrealdinnerbone.simplefireworks.command.base;

import com.unrealdinnerbone.simplefireworks.lib.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

public abstract class FireworkCommandBase extends CommandBase {

    @Override
    public String getUsage(ICommandSender sender) {
        return Reference.MOD_ID + "commands." + getName() + ".usage";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if(sender instanceof CommandBlockBaseLogic) {
            return canCommandBlocksUse(server, sender);
        }else {
            return super.checkPermission(server, sender);
        }
    }

    public void sendUsageMessage(ICommandSender sender) {
        sender.sendMessage(new TextComponentString(getUsage(sender)));
    }

    public boolean canCommandBlocksUse(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    public BlockPos getBlockPosFormArgs(Vec3d base, String[] args, int xSpot) throws NumberInvalidException {
        return new BlockPos(parseDouble(base.x, args[xSpot], true), parseDouble(base.y, args[xSpot + 1], true), parseDouble(base.z, args[xSpot + 2], true));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
