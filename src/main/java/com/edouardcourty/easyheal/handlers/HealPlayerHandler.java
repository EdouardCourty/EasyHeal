package com.edouardcourty.easyheal.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HealPlayerHandler {
    public static void handle(Player player) {
        player.setHealth(20);
        player.sendMessage(ChatColor.GREEN.toString() + "You've been healed");
    }
}
