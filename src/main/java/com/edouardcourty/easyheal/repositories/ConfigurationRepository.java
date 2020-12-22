package com.edouardcourty.easyheal.repositories;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationRepository {
    
    public static final String PLAYER_NAME_PLACEHOLDER = "%playerName%";

    public static int ACCEPTED_VERSION = 1;

    static FileConfiguration config;

    public static void init(FileConfiguration myConfig) {
        config = myConfig;
    }

    public static int getVersion() {
        return config.getInt("version");
    }

    public static boolean getIsPermissionNeededToHealOthers() {
        return config.getBoolean("need_permission_to_heal_others");
    }

    public static boolean getIsPermissionNeededToHealSelf() {
        return config.getBoolean("need_permission_to_heal_self");
    }

    public static boolean getIsPermissionNeededToFeedOthers() {
        return config.getBoolean("need_permission_to_feed_others");
    }

    public static boolean getIsPermissionNeededToFeedSelf() {
        return config.getBoolean("need_permission_to_feed_self");
    }

    public static long getHealCooldownDuration() {
        return config.getLong("heal_cooldown_duration");
    }

    public static long getFeedCooldownDuration() {
        return config.getLong("feed_cooldown_duration");
    }

    public static boolean getIsHealCooldownActivated() {
        return config.getBoolean("use_heal_cooldown");
    }

    public static boolean getIsFeedCooldownActivated() {
        return config.getBoolean("use_feed_cooldown");
    }

    public static boolean getIsSaturationActivated() {
        return config.getBoolean("give_saturation");
    }

    public static int getFeedAmount() {
        return config.getInt("feed_amount");
    }

    public static int getHealAmount() {
        return config.getInt("heal_amount");
    }

    public static int getSaturationAmount() {
        return config.getInt("saturation_amount");
    }

    public static String getNoPermissionMessage() {
        return config.getConfigurationSection("messages").getString("no_permission");
    }

    public static String getHealReceivedMessage() {
        return config.getConfigurationSection("messages").getString("heal_received");
    }

    public static String getHealGivenMessage(String playerName) {
        return config
                .getConfigurationSection("messages")
                .getString("heal_given")
                .replace(PLAYER_NAME_PLACEHOLDER, playerName);
    }

    public static String getFeedReceivedMessage() {
        return config.getConfigurationSection("messages").getString("feed_received");
    }

    public static String getFeedGivenMessage(String playerName) {
        return config
                .getConfigurationSection("messages")
                .getString("feed_given")
                .replace(PLAYER_NAME_PLACEHOLDER, playerName);
    }

    public static String getCooldownNotFinishedMessage() {
        return config.getConfigurationSection("messages").getString("cooldown_not_finished");
    }

    public static String getPlayerNotFoundMessage(String playerName) {
        return config
                .getConfigurationSection("messages")
                .getString("player_not_found")
                .replace(PLAYER_NAME_PLACEHOLDER, playerName);
    }
}
