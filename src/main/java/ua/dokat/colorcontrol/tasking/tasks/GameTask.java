package ua.dokat.colorcontrol.tasking.tasks;

import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.BossBar;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.newrealms.entity.Cube;
import ua.dokat.colorcontrol.newrealms.entity.PlayerGameStats;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.CubeService;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.GameProfileService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.List;

public class GameTask implements Runnable, Utils {

    private int time = 15 * 60;

    private final Realm realm;
    private final List<Player> players;
    private final BossBar bossBar;

    private final CubeService cubeService = CubeService.getInstance();
    private final TeamService teamService = TeamService.getInstance();

    public GameTask(Realm realm) {
        this.realm = realm;
        this.players = players(realm.getPlayers());
        bossBar = realm.getGame().getBossBar();
        bossBar.showBossBar();
    }

    @Override
    public void run() {
        if (time == 0){
            GameService.getInstance().stop(realm);
            for (Player player : players) player.sendMessage("Game close");
            bossBar.hideBossBar();
        }

        time--;
        bossBar.update(time);

        for (Player player : players){
//            giveEffect(player);
//            if (GameProfileService.getInstance().isAlive(player.getUniqueId())) testHp(player);
        }
    }

    private void giveEffect(Player player){
        Cube cube = cubeService.getCube(player.getBoundingBox(), realm);

        if (cube == null) return;

        if (time % cube.getTime() == 0 && cubeService.isDug(cube, realm, teamService.findTeamByUUID(player.getUniqueId()))) cube.giveEffect(player);
    }

    private void testHp(Player player){
        PlayerGameStats stats = GameProfileService.getInstance().getStats(player.getUniqueId());

        int death = stats.getDeaths();

        if (death == 3) return;

        if (death == 0){
            player.sendActionBar(color("&c❤❤❤"));
        } else if (death == 1) {
            player.sendActionBar(color("&c❤❤&f❤"));
        } else if (death == 2) {
            player.sendActionBar(color("&c❤&f❤❤"));
        }
    }
}
