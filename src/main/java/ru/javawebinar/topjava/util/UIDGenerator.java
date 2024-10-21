package ru.javawebinar.topjava.util;

import java.util.HashMap;
import java.util.Map;

public class UIDGenerator {
    private static final Map<String, Long> map = new HashMap<>();

    public static void setUID(String key, long uid) {
        synchronized (map) {
            map.put(key, uid);
        }
    }

    public static long getUID(String key) {
        synchronized (map) {
            if (!map.containsKey(key)) {
                map.put(key, 0L);
            }
            long uid = map.get(key);
            map.put(key, uid + 1);
            return uid;
        }
    }

}
