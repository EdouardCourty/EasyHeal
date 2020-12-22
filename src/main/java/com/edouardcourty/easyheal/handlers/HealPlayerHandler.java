package com.edouardcourty.easyheal.handlers;

import com.edouardcourty.easyheal.exceptions.CooldownStillRunningException;
import com.edouardcourty.easyheal.exceptions.PlayerHasNoPermissionException;
import com.edouardcourty.easyheal.exceptions.PlayerNotFoundException;
import com.edouardcourty.easyheal.handlers.commands.EffectPlayerCommand;
import com.edouardcourty.easyheal.permissions.Permissions;
import com.edouardcourty.easyheal.repositories.ConfigurationRepository;
import com.edouardcourty.easyheal.repositories.HealCooldownRepository;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class HealPlayerHandler {

    public static long cooldownDuration;
    public static boolean isCooldownUsed;
    public static int healAmount;
    public static boolean needPermissionToHealSelf;
    public static boolean needPermissionToHealOthers;

    public static void init() {
        cooldownDuration = ConfigurationRepository.getHealCooldownDuration();
        isCooldownUsed = ConfigurationRepository.getIsHealCooldownActivated();
        healAmount = ConfigurationRepository.getHealAmount();
        needPermissionToHealSelf = ConfigurationRepository.getIsPermissionNeededToHealSelf();
        needPermissionToHealOthers = ConfigurationRepository.getIsPermissionNeededToHealOthers();
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
        boolean playerCanSelfHeal = sender.hasPermission(Permissions.HEAL_SELF);
        boolean playerCanHealOthers = sender.hasPermission(Permissions.HEAL_OTHERS);
        boolean isSentByPlayer = sender instanceof Player;
        boolean isHealingHimself = isSentByPlayer && player.getName().equals(sender.getName());

        // The sender does not has the right permissions for what he's trying to do.
        if (isSentByPlayer && ((!playerCanSelfHeal && isHealingHimself) || (!playerCanHealOthers && !isHealingHimself))) {
            throw new PlayerHasNoPermissionException();
        }

        // Only checks for the cooldown value if the cooldown has to be used and if the user does not bypasses it.
        // Does not check for the cooldown if the command is executed by the server directly.
        if (isCooldownUsed && !playerHasBypassPermission && isSentByPlayer) {
            long lastlyUsedTimestamp = HealCooldownRepository.getLastUsage(playerName);
            long currentTimestamp = new Date().getTime();

            long secondsSinceLastUse = (currentTimestamp - lastlyUsedTimestamp) / 1000;

            if (secondsSinceLastUse < cooldownDuration) {
                throw new CooldownStillRunningException();
            }
            HealCooldownRepository.saveUsage(playerName);
        }

        // Heals the target
        player.setFoodLevel(healAmount);
        player.sendMessage(ConfigurationRepository.getHealReceivedMessage());
        // Sends a message back to the command sender when the target has been healed.
        // Only triggers if the sender is not the target
        if (!isHealingHimself) {
            sender.sendMessage(ConfigurationRepository.getHealGivenMessage(playerName));
        }
    }
}
