package com.edouardcourty.easyheal.commands.handlers;

import com.edouardcourty.easyheal.exceptions.CooldownStillRunningException;
import com.edouardcourty.easyheal.exceptions.PlayerHasNoPermissionException;
import com.edouardcourty.easyheal.exceptions.PlayerNotFoundException;
import com.edouardcourty.easyheal.handlers.FeedPlayerHandler;
import com.edouardcourty.easyheal.handlers.commands.EffectPlayerCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FeedCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        String playerName = args.length > 0 ? args[0] : commandSender.getName();
        EffectPlayerCommand feedPlayerCommand = new EffectPlayerCommand(commandSender, playerName);
        try {
            FeedPlayerHandler.handle(feedPlayerCommand);
        } catch (PlayerNotFoundException | CooldownStillRunningException | PlayerHasNoPermissionException e) {
            commandSender.sendMessage(e.getMessage());
        }
        return false;
    }
}
