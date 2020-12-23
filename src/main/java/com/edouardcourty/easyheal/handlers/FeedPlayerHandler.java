package com.edouardcourty.easyheal.handlers;

import com.edouardcourty.easyheal.exceptions.CooldownStillRunningException;
import com.edouardcourty.easyheal.exceptions.PlayerHasNoPermissionException;
import com.edouardcourty.easyheal.exceptions.PlayerNotFoundException;
import com.edouardcourty.easyheal.handlers.commands.EffectPlayerCommand;
import com.edouardcourty.easyheal.permissions.Permissions;
import com.edouardcourty.easyheal.repositories.ConfigurationRepository;
import com.edouardcourty.easyheal.repositories.FeedCooldownRepository;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class FeedPlayerHandler {

    public static long cooldownDuration;
    public static boolean giveSaturation;
    public static boolean isCooldownUsed;
    public static int feedAmount;
    public static boolean needPermissionToFeedSelf;
    public static boolean needPermissionToFeedOthers;
    public static int saturationAmount;

    public static void init() {
        cooldownDuration = ConfigurationRepository.getFeedCooldownDuration();
        isCooldownUsed = ConfigurationRepository.getIsFeedCooldownActivated();
        giveSaturation = ConfigurationRepository.getIsSaturationActivated();
        feedAmount = ConfigurationRepository.getFeedAmount();
        needPermissionToFeedSelf = ConfigurationRepository.getIsPermissionNeededToFeedSelf();
        needPermissionToFeedOthers = ConfigurationRepository.getIsPermissionNeededToFeedOthers();
        saturationAmount = ConfigurationRepository.getSaturationAmount();
    }

    public static void handle(EffectPlayerCommand effectPlayerCommand) throws PlayerNotFoundException, CooldownStillRunningException, PlayerHasNoPermissionException {
        CommandSender sender = effectPlayerCommand.getSender();
        String playerName = effectPlayerCommand.getPlayerName();
        Player player = Bukkit.getPlayer(playerName);

        // Player is not found
        if (player == null) {
            throw PlayerNotFoundException.fromPlayerName(playerName);
        }

        boolean playerHasBypassPermission = sender.hasPermission(Permissions.BYPASS_COOLDOWN);
        boolean playerCanSelfFeed = sender.hasPermission(Permissions.FEED_SELF);
        boolean playerCanFeedOthers = sender.hasPermission(Permissions.FEED_OTHERS);
        boolean isSentByPlayer = sender instanceof Player;
        boolean isFeedingHimself = isSentByPlayer && player.getName().equals(sender.getName());

        // The sender does not has the right permissions for what he's trying to do.
        if (
                isSentByPlayer && // Server does not need permission to execute the command
                ((!playerCanSelfFeed && isFeedingHimself && needPermissionToFeedSelf) ||
                (!playerCanFeedOthers && !isFeedingHimself && needPermissionToFeedOthers))
        ) {
            throw new PlayerHasNoPermissionException();
        }

        // Only checks for the cooldown value if the cooldown has to be used and if the user does not bypasses it.
        // Does not check for the cooldown if the command is executed by the server directly.
        if (isCooldownUsed && !playerHasBypassPermission && isSentByPlayer) {
            long lastlyUsedTimestamp = FeedCooldownRepository.getLastUsage(playerName);
            long currentTimestamp = new Date().getTime();

            long secondsSinceLastUse = (currentTimestamp - lastlyUsedTimestamp) / 1000;

            if (secondsSinceLastUse < cooldownDuration) {
                throw new CooldownStillRunningException();
            }
            FeedCooldownRepository.saveUsage(playerName);
        }

        // Gives for and saturation if activated and notifies the player.
        player.setFoodLevel(feedAmount);
        if (giveSaturation) {
            player.setSaturation(saturationAmount);
        }
        player.sendMessage(ConfigurationRepository.getFeedReceivedMessage());
        // Sends a message back to the command sender when the target has been fed.
        // Only triggers if the sender is not the target
        if (!isFeedingHimself) {
            sender.sendMessage(ConfigurationRepository.getFeedGivenMessage(playerName));
        }
    }
}
