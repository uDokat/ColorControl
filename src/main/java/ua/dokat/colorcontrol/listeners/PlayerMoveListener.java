package ua.dokat.colorcontrol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import ua.dokat.colorcontrol.newrealms.services.TeleportService;

public class PlayerMoveListener extends Listener{

    @EventHandler
    public void move(PlayerMoveEvent event){
        if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("lobby")) return;

        if (event.getTo().getY() <= 1){
            Player player = event.getPlayer();
            TeleportService.getInstance().teleportToLobby(player);
        }
    }
}
