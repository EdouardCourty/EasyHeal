package com.edouardcourty.easyheal.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler {
    public static void handle(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        if (config.getInt("version") != 1) {
            Bukkit.getLogger().severe(String.format("[%s] The config file version is wrong. Some features of the plugin may not work.\nTo reset the config, delete the file and restart the server.", plugin.getName()));
        }
    }
}
