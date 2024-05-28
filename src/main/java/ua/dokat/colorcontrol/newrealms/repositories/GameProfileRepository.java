package ua.dokat.colorcontrol.newrealms.repositories;

import ua.dokat.colorcontrol.newrealms.entity.GameProfile;
import ua.dokat.colorcontrol.newrealms.entity.PlayerGameStats;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameProfileRepository {

    private final Map<UUID, GameProfile> profiles = new HashMap<>();

    public GameProfile findProfile(UUID uuid){
        return profiles.get(uuid);
    }

    public PlayerGameStats findStats(UUID uuid){
        return profiles.get(uuid).getStats();
    }

    public Map<UUID, GameProfile> getProfiles() {
        return profiles;
    }
}
