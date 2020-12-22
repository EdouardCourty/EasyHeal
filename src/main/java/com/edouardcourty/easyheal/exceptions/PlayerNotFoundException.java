package com.edouardcourty.easyheal.exceptions;

import com.edouardcourty.easyheal.repositories.ConfigurationRepository;

public class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException(String message) {
        super(message);
    }

    public static PlayerNotFoundException fromPlayerName(String playerName) {
        return new PlayerNotFoundException(ConfigurationRepository.getPlayerNotFoundMessage(playerName));
    }
}
