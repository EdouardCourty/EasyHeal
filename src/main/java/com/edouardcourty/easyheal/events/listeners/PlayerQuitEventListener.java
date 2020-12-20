package com.edouardcourty.easyheal.events.listeners;

import com.edouardcourty.easyheal.repositories.HealCooldownRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        HealCooldownRepository.delete(event.getPlayer().getName());
    }
}
