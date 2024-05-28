package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.newrealms.entity.PlayerGameStats;
import ua.dokat.colorcontrol.tasking.TaskBuilder;
import ua.dokat.colorcontrol.tasking.tasks.DeathTask;

public class DeathService implements Service{

    private static DeathService instance;

    private final TeleportService teleportService = TeleportService.getInstance();
    private final GameProfileService gameProfileService = GameProfileService.getInstance();

    @Override
    public void register(Player player){
        teleportService.teleportToTeamSpawn(player);
        player.setGameMode(GameMode.SPECTATOR);

        PlayerGameStats stats = gameProfileService.getStats(player.getUniqueId());

        if (stats.getDeaths() < 2){
            TaskBuilder.runTaskTimer(new DeathTask(player));
            stats.addDeath();
        }
    }

    public static DeathService getInstance(){
        if (instance == null) return instance = new DeathService();
        return instance;
    }
}
