package com.edouardcourty.easyheal.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FeedPlayerHandler {
    public static void handle(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.sendMessage(ChatColor.GREEN.toString() + "You've been fed");
    }
}
