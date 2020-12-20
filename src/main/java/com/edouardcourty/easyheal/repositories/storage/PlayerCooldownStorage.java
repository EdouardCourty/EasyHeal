package com.edouardcourty.easyheal.repositories.storage;

import java.util.HashMap;
import java.util.Map;

public class PlayerCooldownStorage {
    public static Map<String, Long> healCooldownMap = new HashMap<>();
    public static Map<String, Long> feedCooldownMap = new HashMap<>();
}
