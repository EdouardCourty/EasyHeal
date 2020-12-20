package com.edouardcourty.easyheal.handlers;

import com.edouardcourty.easyheal.permissions.Permissions;
import com.edouardcourty.easyheal.repositories.ConfigurationRepository;
import com.edouardcourty.easyheal.repositories.HealCooldownRepository;
import org.bukkit.entity.Player;

import java.util.Date;

public class HealPlayerHandler {

    public static long cooldownDuration;
    public static int healAmount;

    public static void init() {
        cooldownDuration = ConfigurationRepository.getHealCooldownDuration();
        healAmount = ConfigurationRepository.getHealAmount();
    }

    public static void handle(Player player) {

        long lastlyUsedTimestamp = HealCooldownRepository.getLastUsage(player.getName());
        long currentTimestamp = new Date().getTime();

        long secondsSinceLastUse = (currentTimestamp - lastlyUsedTimestamp) / 1000;

        if (
                (secondsSinceLastUse > cooldownDuration || player.hasPermission(Permissions.BYPASS_COOLDOWN)) ||
                (!ConfigurationRepository.getIsHealCooldownActivated())
        ) {
            player.setHealth(healAmount);
            player.sendMessage(ConfigurationRepository.getHealReceivedMessage());
            HealCooldownRepository.saveUsage(player.getName());
        } else {
            player.sendMessage(ConfigurationRepository.getCooldownNotFinishedMessage());
        }
    }
}
