package ua.dokat.colorcontrol.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.InventoryUtil;
import ua.dokat.colorcontrol.commands.test.TestReamUtils;
import ua.dokat.colorcontrol.gui.items.FindGame;
import ua.dokat.colorcontrol.realms.intefraces.Realm;
import ua.dokat.colorcontrol.realms.intefraces.RealmManager;
import ua.dokat.colorcontrol.services.Team;
import ua.dokat.colorcontrol.services.TeamServiceImpl;

public class PlayerTeleportListener implements Listener {

    public PlayerTeleportListener(){
        Bukkit.getPluginManager().registerEvents(this, ColorControl.getInstance());
    }

    @EventHandler
    public void teleported(PlayerTeleportEvent event){
        if (event.getFrom().getWorld().equals(event.getTo().getWorld())) return;
        if (event.getTo().getWorld().getName().equalsIgnoreCase("lobby")){
            Player player = event.getPlayer();

            InventoryUtil.giveItems(player, new FindGame());
            player.playerListName(Component.text(player.getName()));

            Realm realm = null;
            if (realm == null) return;
            
            TeamServiceImpl service = realm.getTeamService();
            Team team = service.getPlayerTeam(player);

            realm.removePlayer(player);
            realm.sendLeaveMessage(player.getName());
            
            if (team == null) return;
            if (service.getNeutralTeam().isPlayerInTeam(player)){
                service.getNeutralTeam().removePlayer(player);
                return;
            }
            service.updateTeamInInventory(team.removePlayer(player), service.getGui().getItemSlot(team.getTeamItem().getType()));
        }
    }

    @EventHandler
    public void teleportToRealm(PlayerTeleportEvent event){
        if (event.getFrom().getWorld().equals(event.getTo().getWorld())) return;
        if (!event.getTo().getWorld().getName().equalsIgnoreCase("lobby")){

            Player player = event.getPlayer();
            RealmManager manager = null;
            if (manager == null) return;

            Realm realm = manager.getRealmByPlayer(player);
            if (realm == null) return;

            TestReamUtils reamUtils = realm.getUtils();

            if (reamUtils.isStarting()) return;
            if (realm.getPlayerCount() >= 2){
                reamUtils.start();
            }
        }
    }

    @EventHandler
    public void teleportFromRealm(PlayerTeleportEvent event){
        if (event.getFrom().getWorld().equals(event.getTo().getWorld())) return;
        if (event.getTo().getWorld().getName().equalsIgnoreCase("lobby")){
            World world = event.getFrom().getWorld();

            RealmManager manager = null;
            if (manager == null) return;

            Realm realm = manager.getRealmByWorld(world);
            if (realm == null) return;

            if (realm.getPlayerCount() < 2) realm.getUtils().stop();
        }
    }
}
