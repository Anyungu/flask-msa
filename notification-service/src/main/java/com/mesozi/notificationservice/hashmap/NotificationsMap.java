package com.mesozi.notificationservice.hashmap;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class NotificationsMap {

    private Map<String, String> map = new HashMap<String, String>();

    public Map<String, String> populateMap(String name, String message) {
        map.put(name, message);

        Map<String, String> mapIn = new HashMap<String, String>();
        mapIn.put(name, "Notice uploaded successfully");
        return mapIn;
    }

    public Map<String, String> getFullMap() {
        return map;
    }

    public Map<String, String> getKeyMap(String name) {

        boolean containsKey = map.containsKey(name);

        if (containsKey) {
            Map<String, String> mapIn = new HashMap<String, String>();
            mapIn.put(name, map.get(name));
            return mapIn;
        }

        return new HashMap<String, String>();
    }

}