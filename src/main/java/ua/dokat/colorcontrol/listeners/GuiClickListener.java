package ua.dokat.colorcontrol.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.RealmService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.UUID;

public class GuiClickListener implements Listener, Utils {

    public GuiClickListener(){
        Bukkit.getPluginManager().registerEvents(this, ColorControl.getInstance());
    }

    @EventHandler
    public void clickToJoin(InventoryClickEvent event){
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) return;

        if (event.getWhoClicked().getWorld().getName().equalsIgnoreCase("lobby")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if (itemStack.getType() == Material.DIAMOND){
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(color("&b2x5"))){
                    Realm realm = RealmService.getInstance().joinFreeClassicRealm(player);
                    if (realm == null) return;

                    TeamService.getInstance().addPlayer(realm.getNeutral(), player.getUniqueId());

                    if (realm.getPlayers().size() >= (Integer) Config.getC("playersForStart")) GameService.getInstance().start(realm);
                    return;
                }

                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(color("&b2x5 Defenders"))){
                    Realm realm = RealmService.getInstance().joinFreeDefRealm(player);
                    if (realm == null) return;

                    TeamService.getInstance().addPlayer(realm.getNeutral(), player.getUniqueId());

                    if (realm.getPlayers().size() >= 2) GameService.getInstance().start(realm);
                }
            }
        }
    }

    @EventHandler
    public void clickToJoinInTeam(InventoryClickEvent event){
        if (event.getCurrentItem() == null || !event.getView().title().contains(Component.text("Select a command"))) return;

        Player player = (Player) event.getWhoClicked();
        TeamService.getInstance().join(player, event.getCurrentItem().getType());

        event.setCancelled(true);
    }
}
