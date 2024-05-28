package ua.dokat.colorcontrol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.UUID;

public class LeavePlayerListener extends Listener{

    private final GameService gameService = GameService.getInstance();
    private final TeamService teamService = TeamService.getInstance();

    @EventHandler
    public void leave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!gameService.isInGame(uuid)) return;

        gameService.removeGame(uuid);
    }
}
