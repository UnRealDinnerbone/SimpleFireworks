package com.unrealdinnerbone.simplefireworks.command;

import com.unrealdinnerbone.simplefireworks.config.FireworkConfig;
import com.unrealdinnerbone.simplefireworks.lib.FireworkHelper;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.EnumFireworkEffect;
import com.unrealdinnerbone.simplefireworks.lib.firework.FireworkColor;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//Todo Better Command Logic
public class CommandSpawnFTBLogo extends FireworkCommandBase {

    private List<FireworkColor> red = new ArrayList<>();
    private List<FireworkColor> green = new ArrayList<>();
    private List<FireworkColor> blue = new ArrayList<>();
    private List<EnumFireworkEffect> effects = new ArrayList<>();
    private List<String> facing = new ArrayList<>();
    private List<String> trueFalse = new ArrayList<>();

    public CommandSpawnFTBLogo() {
        red.add(new FireworkColor(16711680));
        green.add(new FireworkColor(65280));
        blue.add(new FireworkColor(255));
        effects.add(EnumFireworkEffect.FLICKER);
        Arrays.stream(EnumFacing.values()).forEach(facing1 -> facing.add(facing1.getName()));
        trueFalse.add("true");
        trueFalse.add("false");
    }


    @Override
    public String getName() {
        return "spawnFTB";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) {
        if(strings.length >= 4) {
            boolean showCenter = false;
            if(strings.length == 5) {
                try {
                    showCenter = parseBoolean(strings[4]);
                } catch (CommandException e) {
                    iCommandSender.sendMessage(new TextComponentString("error boolean must be true/false"));
                    return;
                }
            }
            EnumFacing facing = EnumFacing.byName(strings[0]);
            if(facing == null) {
                iCommandSender.sendMessage(new TextComponentString(getUsage(iCommandSender)));
                return;
            }
            if(facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
                iCommandSender.sendMessage(new TextComponentString(getUsage(iCommandSender)));
                return;
            }

            BlockPos basPos;
            try {
                basPos = getBlockPosFormArgs(iCommandSender.getPositionVector(), strings, 1);
            } catch (NumberInvalidException e) {
                iCommandSender.sendMessage(new TextComponentString("Error values must be doubles"));
                return;
            }


            if(showCenter) {
                FireworkHelper.spawnFireworkObject("TEST", basPos, iCommandSender.getEntityWorld().provider.getDimension(), EnumExplodeEffect.SPARKLE, facing, red, blue, effects);
            }

            FireworkHelper.spawnFireworkObject("F", basPos.add(facing.getFrontOffsetX() * -15, 0, facing.getFrontOffsetZ() * -15), iCommandSender.getEntityWorld().provider.getDimension(), FireworkConfig.general.enumExplodeEffect, facing, red, red, effects);
            FireworkHelper.spawnFireworkObject("T", basPos.add(0, 15, 0), iCommandSender.getEntityWorld().provider.getDimension(), FireworkConfig.general.enumExplodeEffect, facing, green, green, effects);
            FireworkHelper.spawnFireworkObject("B", basPos.add(facing.getFrontOffsetX() * 15, 0, facing.getFrontOffsetZ() * 15), iCommandSender.getEntityWorld().provider.getDimension(), FireworkConfig.general.enumExplodeEffect, facing, blue, blue, effects);
        }else {
            iCommandSender.sendMessage(new TextComponentString(getUsage(iCommandSender)));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        switch (args.length) {
            case 1: return getListOfStringsMatchingLastWord(args, facing);
            case 2: return Collections.singletonList(sender.getPosition().getX() + "");
            case 3: return Collections.singletonList(sender.getPosition().getY() + "");
            case 4: return Collections.singletonList(sender.getPosition().getZ() + "");
            case 5: return getListOfStringsMatchingLastWord(args, trueFalse);
            default: return Collections.emptyList();
        }
    }
}
