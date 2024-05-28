package ua.dokat.colorcontrol.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.gui.TeamSelectionGUI;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;
import ua.dokat.colorcontrol.realms.intefraces.Realm;
import ua.dokat.colorcontrol.services.TeamServiceImpl;

public class TeamSelectionListener extends Listener implements Utils {

    @EventHandler
    public void c(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (event.getMaterial() == Material.BOOK){
            if (event.getAction().toString().contains("RIGHT")) new TeamSelectionGUI(GameService.getInstance().getRealmByUUID(player.getUniqueId())).openGUI(player);
        }
    }

    @EventHandler
    public void click(InventoryClickEvent event){

        if (!event.getView().title().toString().equals("Select a command")) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item == null) return;

        if (item.getType() == Material.RED_CONCRETE || item.getType() == Material.BLUE_CONCRETE){
            event.setCancelled(true);
            TeamService.getInstance().join(player, item.getType());
        }
    }
}
