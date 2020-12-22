package com.edouardcourty.easyheal.exceptions;

import com.edouardcourty.easyheal.repositories.ConfigurationRepository;

public class CooldownStillRunningException extends Exception {
    public CooldownStillRunningException() {
        super(ConfigurationRepository.getCooldownNotFinishedMessage());
    }
}
