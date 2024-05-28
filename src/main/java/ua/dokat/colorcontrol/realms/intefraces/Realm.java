package ua.dokat.colorcontrol.realms.intefraces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ua.dokat.colorcontrol.commands.test.TestReamUtils;
import ua.dokat.colorcontrol.cubes.Cube;
import ua.dokat.colorcontrol.cubes.IronCube;
import ua.dokat.colorcontrol.services.TeamServiceImpl;

import java.util.List;

public interface Realm {
    void teleportPlayerToLobby(Player player);

    void teleportPlayerToRealm(Player player);

    void addPlayer(Player player);

    void removePlayer(Player player);

    int getPlayerCount();

    boolean isPlayers();

    void sendJoinMessage(String nickName);

    void sendLeaveMessage(String nickName);

    void create();

    void close();

    void restart();

    boolean isOnline();

    World getWorld();

    String getWorldName();

    List<Player> getPlayers();

    TeamServiceImpl getTeamService();

    TestReamUtils getUtils();

    IronCube getCube();
}
