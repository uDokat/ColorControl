package ua.dokat.colorcontrol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.gui.LobbyGUI;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;
import ua.dokat.colorcontrol.newrealms.services.TeleportService;

import java.util.UUID;

public class ClickItemListener implements Listener, Utils {

    public ClickItemListener(){
        Bukkit.getPluginManager().registerEvents(this, ColorControl.getInstance());
    }

    @EventHandler
    public void click(PlayerInteractEvent event){
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("lobby")){
            if (event.getMaterial() == Material.BLAZE_ROD){
                if (event.getAction().toString().contains("RIGHT")) new LobbyGUI().openGUI(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void clickToHub(PlayerInteractEvent event){
        ItemStack item = event.getItem();

        if (item != null && item.getItemMeta().getDisplayName().equalsIgnoreCase(color("&cto lobby"))){
            if (event.getAction().toString().contains("RIGHT")){
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                TeamService teamService = TeamService.getInstance();
                GameService gameService = GameService.getInstance();

                Realm realm = gameService.getRealmByUUID(uuid);

                TeleportService.getInstance().teleportToLobby(player);
                teamService.leave(teamService.findTeamByUUID(uuid), uuid);

                if (realm.getPlayers().size() < 2) GameService.getInstance().stop(realm);
            }
        }
    }
}