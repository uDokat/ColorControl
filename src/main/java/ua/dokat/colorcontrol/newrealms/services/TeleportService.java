package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.InventoryUtil;
import ua.dokat.colorcontrol.gui.items.FindGame;

import java.util.List;
import java.util.UUID;

public class TeleportService {

    private static TeleportService instance;

    private final TeamService teamService = TeamService.getInstance();
    private final GameService gameService = GameService.getInstance();

    public void teleportToTeamSpawn(Player player){
        Location location = teamService.findTeamByUUID(player.getUniqueId()).getSpawn();
        location.setWorld(player.getWorld());

        player.teleport(location);
    }

    public void teleportToLobby(Player player){
        UUID uuid = player.getUniqueId();

        if (gameService.isInGame(uuid)){
            gameService.removeGame(uuid);
            player.hideBossBar(gameService.getRealmByUUID(uuid).getGame().getBossBar().getBossBar());

            InventoryUtil.giveItems(player, new FindGame());
            player.playerListName(player.displayName());
            player.setGameMode(GameMode.CREATIVE);
        }

        player.teleport(ColorControl.getLobby());
    }

    public void teleportEveryoneToLobby(List<Player> players){
        for (Player player : players) teleportToLobby(player);
    }

    public static TeleportService getInstance() {
        if (instance == null) instance = new TeleportService();
        return instance;
    }
}
