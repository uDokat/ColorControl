package ua.dokat.colorcontrol.newrealms.repositories;

import ua.dokat.colorcontrol.newrealms.entity.Realm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameRepository {

    private final Map<UUID, Realm> games = new HashMap<>();

    public void add(UUID uuid, Realm realm){
        if (!games.containsKey(uuid)) games.put(uuid, realm);
    }

    public void remove(UUID uuid){
        games.remove(uuid);
    }

    public Realm findGameByUUID(UUID uuid){
        return games.get(uuid);
    }
}
