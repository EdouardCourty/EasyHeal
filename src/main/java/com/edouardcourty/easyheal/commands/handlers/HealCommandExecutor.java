package com.edouardcourty.easyheal.commands.handlers;

import com.edouardcourty.easyheal.handlers.HealPlayerHandler;
import com.edouardcourty.easyheal.permissions.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        if (commandSender instanceof Player) { // Sent by a player
            final Player sender = (Player) commandSender;
            if (args.length == 0) { // Heals himself
                if (sender.hasPermission(Permissions.HEAL_SELF)) {
                    HealPlayerHandler.handle(sender);
                } else {
                    sender.sendMessage(ChatColor.RED.toString() + "You don't have the permission to do this.");
                }
            } else { // Heals another player
                if (sender.hasPermission(Permissions.HEAL_OTHERS)) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null) {
                        HealPlayerHandler.handle(player);
                    } else {
                        commandSender.sendMessage(String.format("Player %s not found.", args[0]));
                    }
                } else {
                    sender.sendMessage(ChatColor.RED.toString() + "You don't have the permission to do this.");
                }
            }
        } else { // Sent by the server
            if (args.length == 0) { // Tries to heal himself
                Bukkit.getLogger().info("Heal command issued by a non-player. Nothing happened.");
            } else { // Heals a player
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    HealPlayerHandler.handle(player);
                } else {
                    commandSender.sendMessage(String.format("Player %s not found.", args[0]));
                }
            }
        }
        return false;
    }
}
