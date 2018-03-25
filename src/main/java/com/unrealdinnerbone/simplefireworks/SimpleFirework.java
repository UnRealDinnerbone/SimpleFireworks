package com.unrealdinnerbone.simplefireworks;

import com.unrealdinnerbone.simplefireworks.command.CommandFireworkTree;
import com.unrealdinnerbone.simplefireworks.firework.JsonMaker;
import com.unrealdinnerbone.simplefireworks.lib.LogHelper;
import com.unrealdinnerbone.simplefireworks.lib.Reference;
import com.unrealdinnerbone.simplefireworks.network.NetworkManager;
import com.unrealdinnerbone.simplefireworks.network.packet.PacketSpawnFireworkHandler;
import com.unrealdinnerbone.simplefireworks.network.packet.PacketSpawnFirework;
import com.unrealdinnerbone.simplefireworks.network.packet.local.PacketSpawnFireworkObject;
import com.unrealdinnerbone.simplefireworks.network.packet.local.PacketSpawnFireworkObjectHandler;
import com.unrealdinnerbone.simplefireworks.parsar.FireworkParser;
import com.unrealdinnerbone.simplefireworks.parsar.ObjectParser;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;

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

    private static FireworkParser fireworkParser;
    private static ObjectParser objectParser;

    @Mod.EventHandler
    public static void preInt(FMLPreInitializationEvent event) {
        File configFolder = new File(event.getSuggestedConfigurationFile().getParent(), Reference.MOD_ID);
        if (!configFolder.exists()) {
            configFolder.mkdir();
        }
        File fireworksFolder = new File(configFolder, "fireworks");
        if (!fireworksFolder.exists()) {
            fireworksFolder.mkdir();
            JsonMaker.makeBaseFireworkFile(fireworksFolder);
        }
        File objectsFolder = new File(configFolder, "objects");
        if (!objectsFolder.exists()) {
            objectsFolder.mkdir();
            JsonMaker.makeBaseJsonForObjects(objectsFolder);
        }

        fireworkParser = new FireworkParser(fireworksFolder);
        fireworkParser.scan();

        objectParser = new ObjectParser(objectsFolder);
        objectParser.scan();


        NetworkManager.INSTANCE.registerMessage(PacketSpawnFireworkHandler.class, PacketSpawnFirework.class, 0, Side.CLIENT);
        NetworkManager.INSTANCE.registerMessage(PacketSpawnFireworkObjectHandler.class, PacketSpawnFireworkObject.class, 1, Side.CLIENT);
    }

    @Mod.EventHandler
    public static void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFireworkTree());
//        event.registerServerCommand(new CommandSpawnFTBLogo());
//        event.registerServerCommand(new CommandSpawnLetter());
    }

    public static FireworkParser getFireworkParser() {
        return fireworkParser;
    }

    public static ObjectParser getObjectParser() {
        return objectParser;
    }
}
