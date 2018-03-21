package com.unrealdinnerbone.simplefireworks;

import com.unrealdinnerbone.simplefireworks.command.CommandFireworkBase;
import com.unrealdinnerbone.simplefireworks.command.CommandSpawnFTBLogo;
import com.unrealdinnerbone.simplefireworks.command.CommandSpawnLetter;
import com.unrealdinnerbone.simplefireworks.lib.LogHelper;
import com.unrealdinnerbone.simplefireworks.lib.Reference;
import com.unrealdinnerbone.simplefireworks.network.NetworkManager;
import com.unrealdinnerbone.simplefireworks.network.packet.PackSpawnLetterMessageHandler;
import com.unrealdinnerbone.simplefireworks.network.packet.PacketSpawnLetter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod
    (
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.MC_VERSION,
        guiFactory = Reference.GUI_FACTORY_CLASS
    )
public class SimpleFirework {

    @Mod.Instance(Reference.MOD_ID)
    public static SimpleFirework instance;

    public static final LogHelper LOG_HELPER = new LogHelper(Reference.MOD_ID);

    @Mod.EventHandler
    public static void preInt(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public static void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFireworkBase());
        event.registerServerCommand(new CommandSpawnFTBLogo());
        event.registerServerCommand(new CommandSpawnLetter());

        NetworkManager.INSTANCE.registerMessage(PackSpawnLetterMessageHandler.class, PacketSpawnLetter.class, 0, Side.CLIENT);

    }

}
