package ua.dokat.colorcontrol.commands.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.realms.intefraces.Realm;
import ua.dokat.colorcontrol.realms.intefraces.RealmManager;
import ua.dokat.colorcontrol.services.TeamService;

import java.util.List;

public class TestReamUtils implements Runnable{

    private final Realm realm;
    private final TeamService service;

    private BukkitTask task;

    private int timeToStart = 10;

    public TestReamUtils(Realm realm) {
        this.realm = realm;
        service = realm.getTeamService();
    }

    public void start(){
        task = Bukkit.getScheduler().runTaskTimer(ColorControl.getInstance(), this, 0L, 20L);
    }

    public void stop(){
        task.cancel();
        timeToStart = 10;
    }

    public boolean isStarting(){
        if (task == null) return false;
        return !task.isCancelled();
    }

    @Override
    public void run() {
        List<Player> players = realm.getPlayers();

        if (timeToStart > 0){
            for (Player player : players){
                player.sendTitle(ChatColor.RED + String.valueOf(timeToStart), "");
            }
            timeToStart--;
        }else {
            for (Player player : players){
                if (service.getNeutralTeam().isPlayerInTeam(player)){
                    if (service.getRedTeam().isTeamNotFull()){
                        service.getRedTeam().addPlayer(player);
                    }else {
                        service.getBlueTeam().addPlayer(player);
                    }
                }

                player.sendTitle(ChatColor.DARK_RED + "Start!", "");
                service.getPlayerTeam(player).teleport(player);
            }

            service.getRedTeam().setColor();
            service.getBlueTeam().setColor();

            task.cancel();
        }
    }
}
