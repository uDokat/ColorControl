package ua.dokat.colorcontrol.tasking.tasks;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.InventoryUtil;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.newrealms.entity.*;
import ua.dokat.colorcontrol.newrealms.services.GameProfileService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;
import ua.dokat.colorcontrol.newrealms.services.TeleportService;
import ua.dokat.colorcontrol.tasking.TaskBuilder;

import java.util.List;

public class TimerTask implements Task, Utils {

    private int time = (Integer) Config.get("timeToStart");

    @Setter
    private int taskId;
    private final Realm realm;
    private final List<Player> players;

    public TimerTask(Realm realm) {
        this.realm = realm;
        this.players = players(realm.getPlayers());
    }

    @Override
    public void run() {
        if (time == 0){
            sendTitleEveryone(color("&cStart!"));
            teleport();

            Game game = realm.getGame();
            game.getBossBar().setPlayers(players);
            game.setTaskId(TaskBuilder.runTaskGame(new GameTask(realm)));

            realm.setStarted(true);

            for (Player player : players){
                player.setGameMode(GameMode.SURVIVAL);
            }

            GameProfileService.getInstance().fill(realm);
            InventoryUtil.giveEveryoneStartItems(players);

            ColorControl.getInstance().getLogger().info(realm.getName() + " Started");
            Bukkit.getScheduler().cancelTask(taskId);
            return;
        }

        sendTitleEveryone(color("&c" + time));
        time--;
    }

    private void sendTitleEveryone(String message){
        for (Player player : players){
            player.sendTitle(message, "");
        }
    }

    private void teleport(){
        for (Player player : players){
            player.getInventory().clear();

            TeamService service = TeamService.getInstance();

            if (service.findTeamByUUID(player.getUniqueId()) instanceof NeutralTeam) service.forcedJoining(player);

            setTabColor(player);
            TeleportService.getInstance().teleportToTeamSpawn(player);
        }
    }

    private void setTabColor(Player player){
        Team team = TeamService.getInstance().findTeamByUUID(player.getUniqueId());
        if (team instanceof RedTeam) player.setPlayerListName(color("&c" + player.getName()));
        if (team instanceof BlueTeam) player.setPlayerListName(color("&9" + player.getName()));
    }
}
