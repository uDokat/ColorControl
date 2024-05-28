package ua.dokat.colorcontrol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.UUID;

public class PlayerDamageListener extends Listener{

    @EventHandler
    public void damage(EntityDamageByEntityEvent event){
        if (!(event.getEntity() instanceof Player)) return;

        UUID damager = event.getDamager().getUniqueId();
        UUID victim = event.getEntity().getUniqueId();

        if (TeamService.getInstance().isPlayersInSameTeam(damager, victim)) event.setCancelled(true);
    }
}
