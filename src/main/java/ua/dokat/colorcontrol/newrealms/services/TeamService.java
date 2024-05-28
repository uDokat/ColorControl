package ua.dokat.colorcontrol.newrealms.services;

import me.anfanik.steda.api.utility.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.gui.TeamSelectionGUI;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.entity.Team;

import java.util.*;

public class TeamService implements Utils {

    private static TeamService instance;
    private final Map<UUID, Team> playerTeam = new HashMap<>();

    private final GameProfileService profileService = GameProfileService.getInstance();

    public void join(Player player, Material material){
        Realm realm = GameService.getInstance().getRealmByUUID(player.getUniqueId());

        Team currentTeam = findTeamByUUID(player.getUniqueId());
        Team clickTeam = findTeamByMaterial(realm, material);

        if (currentTeam == clickTeam) return;

        leave(player.getUniqueId());
        addPlayer(clickTeam, player.getUniqueId());

        updateInventory(realm, player);
    }

    public void leave(UUID uuid){
        removePlayerFromTeam(findTeamByUUID(uuid), uuid);
        playerTeam.remove(uuid);
    }

    public void forcedJoining(Player player){
        Team team = findFreeTeam(GameService.getInstance().getRealmByUUID(player.getUniqueId()));
        addPlayer(team, player.getUniqueId());
    }

    public void addPlayerInTeam(Team team, UUID uuid){
        if (!team.getPlayers().contains(uuid)) team.getPlayers().add(uuid);
        profileService.getProfile(uuid).setTeam(team);
    }

    public void removePlayerFromTeam(Team team, UUID uuid){
        team.getPlayers().remove(uuid);
    }

    public void addPlayer(Team team, UUID uuid){
        addPlayerInTeam(team, uuid);
        if (playerTeam.containsKey(uuid)){
            if (!(playerTeam.get(uuid) == team)) playerTeam.put(uuid, team);
        }else {
            playerTeam.put(uuid, team);
        }
    }

    public ItemStack redItem(Team team){
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.RED_CONCRETE));
        builder.setName(color("&cRed"));
        builder.setLore(nicknames(team.getPlayers(), "&c"));

        return builder.build();
    }

    public ItemStack blueItem(Team team){
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.BLUE_CONCRETE));
        builder.setName(color("&9Blue"));
        builder.setLore(nicknames(team.getPlayers(), "&9"));

        return builder.build();
    }

    public Team findTeamByUUID(UUID uuid){
        return playerTeam.get(uuid);
    }

    public Team findTeamByMaterial(Realm realm, Material material){
        if (Material.RED_CONCRETE == material) return realm.getRed();
        if (Material.BLUE_CONCRETE == material) return realm.getBlue();
        return realm.getNeutral();
    }

    public Team findFreeTeam(Realm realm){
        Team red = realm.getRed();
        Team blue = realm.getBlue();

        int countRedPlayers = red.getPlayers().size();
        int countBluePlayers = blue.getPlayers().size();

        if (countRedPlayers < countBluePlayers && countRedPlayers < 5) return realm.getRed();
        if (countBluePlayers < countRedPlayers && countBluePlayers < 5) return realm.getBlue();
        return red;
    }

    public boolean isPlayerInTeam(UUID uuid){
        return playerTeam.containsKey(uuid);
    }

    public boolean isPlayersInSameTeam(UUID one, UUID two){
        return playerTeam.get(one) == playerTeam.get(two);
    }

    public void updateInventory(Realm realm, Player player){
        new TeamSelectionGUI(realm).openGUI(player);
    }

    public static TeamService getInstance() {
        if (instance == null) return instance = new TeamService();
        return instance;
    }
}
