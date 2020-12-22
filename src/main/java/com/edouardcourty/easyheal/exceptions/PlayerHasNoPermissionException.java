package com.edouardcourty.easyheal.exceptions;

import com.edouardcourty.easyheal.repositories.ConfigurationRepository;

public class PlayerHasNoPermissionException extends Exception {
    public PlayerHasNoPermissionException() {
        super(ConfigurationRepository.getNoPermissionMessage());
    }
}
