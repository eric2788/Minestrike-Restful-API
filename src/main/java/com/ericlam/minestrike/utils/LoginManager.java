package com.ericlam.minestrike.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginManager {
    private static LoginManager loginManager;
    private HashSet<String> session = new HashSet<>();

    public static LoginManager getInstance() {
        if (loginManager == null) loginManager = new LoginManager();
        return loginManager;
    }

    public Map addSession() {
        HashMap<String, Object> map = new HashMap<>();
        String randomUUID;
        while (true) {
            randomUUID = UUID.randomUUID().toString().replaceAll("-", "");
            if (session.contains(randomUUID)) continue;
            session.add(randomUUID);
            break;
        }
        map.put("success", true);
        map.put("session", randomUUID);
        return map;
    }

    public boolean haveSession(String uuid) {
        return session.contains(uuid);
    }

    public boolean deleteSession(String uuid) {
        return session.remove(uuid);
    }


}
