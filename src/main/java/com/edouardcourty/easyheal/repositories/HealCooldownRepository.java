package com.edouardcourty.easyheal.repositories;

import com.edouardcourty.easyheal.repositories.storage.PlayerCooldownStorage;

import java.util.Date;

public class HealCooldownRepository {
    public static long getLastUsage(String playerName) {
        return PlayerCooldownStorage.healCooldownMap.getOrDefault(playerName, (long) 0);
    }

    public static void saveUsage(String playerName) {
        PlayerCooldownStorage.healCooldownMap.put(playerName, new Date().getTime());
    }

    public static void delete(String playerName) {
        PlayerCooldownStorage.healCooldownMap.remove(playerName);
    }
}
