package com.edouardcourty.easyheal.commands;

import com.edouardcourty.easyheal.commands.handlers.FeedCommandExecutor;
import com.edouardcourty.easyheal.commands.handlers.HealCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegisterer {
    public static void init(JavaPlugin plugin) {
        plugin.getCommand("heal").setExecutor(new HealCommandExecutor());
        plugin.getCommand("feed").setExecutor(new FeedCommandExecutor());
    }
}
