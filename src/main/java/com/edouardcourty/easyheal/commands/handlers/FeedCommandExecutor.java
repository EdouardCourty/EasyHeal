package com.edouardcourty.easyheal.commands.handlers;

import com.edouardcourty.easyheal.handlers.FeedPlayerHandler;
import com.edouardcourty.easyheal.permissions.Permissions;
import com.edouardcourty.easyheal.repositories.ConfigurationRepository;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        if (commandSender instanceof Player) { // Sent by a player
            final Player sender = (Player) commandSender;
            if (args.length == 0) { // Feeds himself
                if (sender.hasPermission(Permissions.FEED_SELF)) {
                    FeedPlayerHandler.handle(sender);
                } else {
                    sender.sendMessage(ConfigurationRepository.getNoPermissionMessage());
                }
            } else { // Feeds another player
                if (sender.hasPermission(Permissions.FEED_OTHERS)) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null) {
                        FeedPlayerHandler.handle(player);
                        sender.sendMessage(ConfigurationRepository.getFeedGivenMessage(player.getName()));
                    } else {
                        commandSender.sendMessage(ConfigurationRepository.getPlayerNotFoundMessage(args[0]));
                    }
                } else {
                    sender.sendMessage(ConfigurationRepository.getNoPermissionMessage());
                }
            }
        } else { // Sent by the server
            if (args.length == 0) { // Tries to feed himself
                Bukkit.getLogger().info("Heal command issued by a non-player. Nothing happened.");
            } else { // Feeds a player
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    FeedPlayerHandler.handle(player);
                    Bukkit.getLogger().info(ConfigurationRepository.getFeedGivenMessage(player.getName()));
                } else {
                    commandSender.sendMessage(ConfigurationRepository.getPlayerNotFoundMessage(args[0]));
                }
            }
        }
        return false;
    }
}
