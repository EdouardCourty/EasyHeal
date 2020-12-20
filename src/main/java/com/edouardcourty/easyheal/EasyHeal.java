package com.edouardcourty.easyheal;

import com.edouardcourty.easyheal.commands.CommandRegisterer;
import com.edouardcourty.easyheal.events.EventRegisterer;
import com.edouardcourty.easyheal.handlers.ConfigHandler;
import com.edouardcourty.easyheal.handlers.FeedPlayerHandler;
import com.edouardcourty.easyheal.handlers.HealPlayerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyHeal extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled !");

        ConfigHandler.handle(this);
        EventRegisterer.init(this);
        CommandRegisterer.init(this);

        FeedPlayerHandler.init();
        HealPlayerHandler.init();
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled ! Thanks for usinng it :)");
    }
}
