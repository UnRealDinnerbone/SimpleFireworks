package com.unrealdinnerbone.simplefireworks;

import com.unrealdinnerbone.simplefireworks.command.CommandFireworkTree;
import com.unrealdinnerbone.simplefireworks.firework.JsonMaker;
import com.unrealdinnerbone.simplefireworks.lib.Reference;
import com.unrealdinnerbone.simplefireworks.parsar.FireworkParser;
import com.unrealdinnerbone.simplefireworks.parsar.ObjectParser;
import com.unrealdinnerbone.yaum.api.IYaumMod;
import com.unrealdinnerbone.yaum.api.util.LangHelper;
import com.unrealdinnerbone.yaum.api.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;

@Mod
    (
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.MC_VERSION,
        guiFactory = Reference.GUI_FACTORY_CLASS
    )
public class SimpleFirework implements IYaumMod {

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


    }

    @Mod.EventHandler
    public static void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFireworkTree());
    }

    public static FireworkParser getFireworkParser() {
        return fireworkParser;
    }

    public static ObjectParser getObjectParser() {
        return objectParser;
    }

    @Override
    public String getModName() {
        return Reference.MOD_ID;
    }

    @Override
    public LogHelper getLogHelper() {
        return null;
    }

    @Override
    public LangHelper getLangHelper() {
        return null;
    }
}
