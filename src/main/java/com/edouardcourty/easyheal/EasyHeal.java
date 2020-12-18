package com.edouardcourty.easyheal;

import com.edouardcourty.easyheal.commands.FeedCommandExecutor;
import com.edouardcourty.easyheal.commands.HealCommandExecutor;
import com.edouardcourty.easyheal.handlers.ConfigHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyHeal extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled !");

        ConfigHandler.handle(this);

        getCommand("heal").setExecutor(new HealCommandExecutor());
        getCommand("feed").setExecutor(new FeedCommandExecutor());
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled ! Thanks for usinng it :)");
    }
}
