package com.friendby.database.repository;

import java.util.HashMap;
import java.util.Map;

public class StorageRepository {

    private static final Map<String, Integer> memory = new HashMap<>();


    public static Integer get(String key) {
        return memory.get(key);
    }

    public static void set(String key, Integer value) {
        memory.put(key, value);
    }

    public static void remove(String key) {
        memory.remove(key);
    }

    public static Long countByValue(Integer value) {
        return memory.values().stream().filter(v -> v.intValue() == value).count();
    }

    public static void merge(Map<String, Integer> transactionValues){
        memory.putAll(transactionValues);
    }

}
