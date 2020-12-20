package com.edouardcourty.easyheal.repositories;

import com.edouardcourty.easyheal.repositories.storage.PlayerCooldownStorage;

import java.util.Date;

public class FeedCooldownRepository {
    public static Long getLastUsage(String playerName) {
        return PlayerCooldownStorage.feedCooldownMap.getOrDefault(playerName, (long) 0);
    }

    public static void saveUsage(String playerName) {
        PlayerCooldownStorage.feedCooldownMap.put(playerName, new Date().getTime());
    }

    public static void delete(String playerName) {
        PlayerCooldownStorage.feedCooldownMap.remove(playerName);
    }
}
