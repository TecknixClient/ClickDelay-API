package com.tecknix.impl;

import com.tecknix.clickdelay.TCClickDelayAPI;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This plugin disables the 1.8x CPS cooldown on Tecknix Client.
 * It can be used here or through another plugin.
 * Our fully fledged Bukkit api also does this if you wish to have other features.
 */
public class ClickDelayImpl extends JavaPlugin {

    /**
     * Example implementation of the API.
     */
    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        final boolean disabled = this.getConfig().getBoolean("disable-cps-cooldown");

        //Developers, You can use this line to implement this api into your own plugin.
        new TCClickDelayAPI(this).setDisabled(disabled);
    }
}
