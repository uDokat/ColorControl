package ua.dokat.colorcontrol.newrealms.services;

import ua.dokat.colorcontrol.newrealms.entity.GameProfile;
import ua.dokat.colorcontrol.newrealms.entity.PlayerGameStats;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.repositories.GameProfileRepository;

import java.util.List;
import java.util.UUID;

public class GameProfileService {

    private static GameProfileService instance;
    private final GameProfileRepository repository = new GameProfileRepository();

    public void fill(Realm realm){
//        for (UUID uuid : realm.getPlayers()) repository.getProfiles().put(uuid, new GameProfile(uuid));
    }

    public void clear(List<UUID> uuids){
        for (UUID uuid : uuids) repository.getProfiles().remove(uuid);
    }

    public GameProfile getProfile(UUID uuid){
        return repository.findProfile(uuid);
    }

    public PlayerGameStats getStats(UUID uuid){
        return repository.findStats(uuid);
    }

    public boolean isAlive(UUID uuid){
        return getStats(uuid).getDeaths() < 3;
    }

    public static GameProfileService getInstance() {
        if (instance == null) return instance = new GameProfileService();
        return instance;
    }
}
