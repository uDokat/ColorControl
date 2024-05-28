package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.Bukkit;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.tasking.TaskBuilder;
import ua.dokat.colorcontrol.tasking.tasks.TimerTask;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.repositories.GameRepository;

import java.util.List;
import java.util.UUID;

public class GameService implements Utils {

    private static GameService instance;
    private final GameRepository repository = new GameRepository();

    private final TeamService teamService = TeamService.getInstance();
    private final TeleportService teleportService = TeleportService.getInstance();
    private final RealmService realmService = RealmService.getInstance();

    public void start(Realm realm){
        if (!realm.isStarted()){
            TaskBuilder.runTaskTimer(new TimerTask(realm));
        }
    }

    public void stop(Realm realm){
        if (realm.isStarted()){
            Bukkit.getScheduler().cancelTask(realm.getGame().getTaskId());

            teleportService.teleportEveryoneToLobby(players(realm.getPlayers()));
            realmService.restart(realm);
        }
    }

    public void addGame(UUID uuid, Realm realm){
        repository.add(uuid, realm);
    }

    public void removeGame(UUID uuid){
        teamService.leave(uuid);
        realmService.removePlayer(getRealmByUUID(uuid), uuid);
        repository.remove(uuid);
    }

    public void removeGame(List<UUID> uuids){
        for (UUID uuid : uuids){
            removeGame(uuid);
        }
    }

    public boolean isInGame(UUID uuid){
        return repository.findGameByUUID(uuid) != null;
    }

    public Realm getRealmByUUID(UUID uuid){
        return repository.findGameByUUID(uuid);
    }

    public static GameService getInstance() {
        if (instance == null) return instance = new GameService();
        return instance;
    }
}
