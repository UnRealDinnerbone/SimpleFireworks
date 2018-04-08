package com.unrealdinnerbone.simplefireworks.config;

import com.unrealdinnerbone.simplefireworks.lib.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Reference.MOD_ID, name = Reference.MOD_ID + "/" + Reference.MOD_ID)
@Config.LangKey(Reference.MOD_ID + ".config.title")
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class FireworkConfig {

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }

}