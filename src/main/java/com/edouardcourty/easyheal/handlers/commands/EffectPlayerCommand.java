package com.edouardcourty.easyheal.handlers.commands;

import org.bukkit.command.CommandSender;

public class EffectPlayerCommand {
    private final CommandSender sender;
    private final String playerName;

    public EffectPlayerCommand(CommandSender commandSender, String toEFfectPlayerName) {
        sender = commandSender;
        playerName = toEFfectPlayerName;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getPlayerName() {
        return playerName;
    }
}
