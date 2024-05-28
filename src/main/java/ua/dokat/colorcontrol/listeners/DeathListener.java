package ua.dokat.colorcontrol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.newrealms.services.DeathService;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.KillService;
import ua.dokat.colorcontrol.newrealms.services.MessageService;

import java.util.List;

public class DeathListener extends Listener implements Utils {

    private final MessageService messageService = MessageService.getInstance();

    @EventHandler
    public void deathInGame(PlayerDeathEvent event){
        Player player = event.getEntity();
        Player killer = player.getKiller();

        List<Player> players = players(GameService.getInstance().getRealmByUUID(player.getUniqueId()).getPlayers());

        if (killer != null){
            KillService.getInstance().register(killer);
            messageService.killPlayer(players, killer, player);
        }else {
            messageService.deathPlayer(players, player);
        }

        Bukkit.getScheduler().runTaskLater(ColorControl.getInstance(), () -> {
            DeathService.getInstance().register(player);
        }, 1);

        for (ItemStack item : player.getInventory().getContents()){
            if (item != null) player.getWorld().dropItem(player.getLocation(), item);
        }

        player.getInventory().clear();
        event.setCancelled(true);
    }
}