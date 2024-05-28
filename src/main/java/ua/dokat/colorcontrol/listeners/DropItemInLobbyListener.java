package ua.dokat.colorcontrol.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemInLobbyListener extends Listener {

    @EventHandler
    public void breakDrop(PlayerDropItemEvent event){
        Material material = event.getItemDrop().getItemStack().getType();

        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("lobby")){
            event.setCancelled(true);
        } else if (material == Material.BOOK || material == Material.REDSTONE) {
            event.setCancelled(true);
        }
    }
}
