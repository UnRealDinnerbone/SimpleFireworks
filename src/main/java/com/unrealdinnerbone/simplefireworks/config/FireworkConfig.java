package com.unrealdinnerbone.simplefireworks.config;

import com.unrealdinnerbone.simplefireworks.lib.Reference;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumExplodeEffect;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Reference.MOD_ID)
@Config.LangKey(Reference.MOD_ID + ".config.title")
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class FireworkConfig {

    public static final General general = new General();

    public static class General {

        @Config.Comment("Range to send packet of display fireworks")
        public int fireworkPacketRange = 64;

        @Config.Comment("Temp Config to define explode type")
        public EnumExplodeEffect enumExplodeEffect = EnumExplodeEffect.SMALL_BALL;

    }


    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }

}