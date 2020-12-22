package com.edouardcourty.easyheal.events;

import com.edouardcourty.easyheal.events.listeners.PlayerQuitEventListener;
import com.edouardcourty.easyheal.repositories.ConfigurationRepository;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegisterer {
    public static void init(JavaPlugin plugin) {
        Boolean isFeedCooldownActivated = ConfigurationRepository.getIsFeedCooldownActivated();
        Boolean isHealCooldownActivated = ConfigurationRepository.getIsHealCooldownActivated();
        // Only listen to the event if needed.
        if (isFeedCooldownActivated || isHealCooldownActivated) {
            plugin.getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(), plugin);
        }
    }
}
