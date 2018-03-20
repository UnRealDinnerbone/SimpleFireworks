package com.unrealdinnerbone.simplefireworks.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.common.ModContainer;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen parentScreen, String modId, String name) {
        super(parentScreen, modId, name);
    }

}
