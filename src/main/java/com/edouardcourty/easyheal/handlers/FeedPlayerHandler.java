package com.edouardcourty.easyheal.handlers;

import com.edouardcourty.easyheal.permissions.Permissions;
import com.edouardcourty.easyheal.repositories.ConfigurationRepository;
import com.edouardcourty.easyheal.repositories.FeedCooldownRepository;
import org.bukkit.entity.Player;

import java.util.Date;

public class FeedPlayerHandler {

    public static long cooldownDuration;
    public static boolean giveSaturation;
    public static int feedAmount;
    public static int saturationAmount;

    public static void init() {
        cooldownDuration = ConfigurationRepository.getFeedCooldownDuration();
        giveSaturation = ConfigurationRepository.getIsSaturationActivated();
        feedAmount = ConfigurationRepository.getFeedAmount();
        saturationAmount = ConfigurationRepository.getSaturationAmount();
    }

    public static void handle(Player player) {
        long lastlyUsedTimestamp = FeedCooldownRepository.getLastUsage(player.getName());
        long currentTimestamp = new Date().getTime();

        long secondsSinceLastUse = (currentTimestamp - lastlyUsedTimestamp) / 1000;

        if (
                (secondsSinceLastUse > cooldownDuration || player.hasPermission(Permissions.BYPASS_COOLDOWN)) ||
                (!ConfigurationRepository.getIsFeedCooldownActivated())
        ) {
            player.setFoodLevel(feedAmount);
            if (giveSaturation) {
                player.setSaturation(saturationAmount);
            }
            player.sendMessage(ConfigurationRepository.getFeedReceivedMessage());
            FeedCooldownRepository.saveUsage(player.getName());
        } else {
            player.sendMessage(ConfigurationRepository.getCooldownNotFinishedMessage());
        }
    }
}
