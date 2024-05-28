package ua.dokat.colorcontrol.realms.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.commands.test.TestReamUtils;
import ua.dokat.colorcontrol.cubes.IronCube;
import ua.dokat.colorcontrol.newrealms.services.TeamService;
import ua.dokat.colorcontrol.realms.intefraces.Realm;
import ua.dokat.colorcontrol.services.TeamServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class RealmImpl implements Realm, Utils {

    private final String name;
    private World world;
    private boolean online;
    private final List<Player> players = new ArrayList<>();

    private final TeamServiceImpl teamService = new TeamServiceImpl();
    private final TestReamUtils testReamUtils = new TestReamUtils(this);
    private final IronCube ironCube = new IronCube();

    public RealmImpl(String name){
        this.name = name;
    }

    @Override
    public void teleportPlayerToLobby(Player player) {
        player.teleport(new Location(ColorControl.getMainWorld(), -90.5, 56, 18.5, -130, 0));
    }

    @Override
    public void teleportPlayerToRealm(Player player){
        player.teleport(world.getSpawnLocation());
    }

    @Override
    public void addPlayer(Player player) {
        if (!players.contains(player)){
            players.add(player);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public int getPlayerCount() {
        return world.getPlayerCount();
    }

    @Override
    public boolean isPlayers() {
        return players.size() == 0;
    }

    @Override
    public void sendJoinMessage(String nickName) {
        for (Player p : players){
            p.sendMessage(color("&cColorControl &7>> &a" + nickName + " &ejoin (&b" + getPlayerCount() + "&e/&b" + Config.getC("maxPlayers") + "&e)"));
        }
    }

    @Override
    public void sendLeaveMessage(String nickName) {
        for (Player p : players){
            p.sendMessage(color("&cColorControl &7>> &a" + nickName + " &eleave (&b" + getPlayerCount() + "&e/&b" + Config.getC("maxPlayers") + "&e)"));
        }
    }

    @Override
    public void create() {
        if (world == null){
            WorldCreator creator = new WorldCreator(name);
            creator.generator("null");
            creator.generateStructures(false);

            world = Bukkit.createWorld(creator);

            world.setKeepSpawnInMemory(false);
            world.setMonsterSpawnLimit(0);
            world.setAnimalSpawnLimit(0);
            world.setWaterAnimalSpawnLimit(0);
            world.setAutoSave(false);
            world.setGameRuleValue("maxPlayers", String.valueOf(Config.getC("maxPlayers")));

            online = true;
        }
    }

    @Override
    public void close() {
        if (world != null){
            for (Player player : players){
                teleportPlayerToLobby(player);
                removePlayer(player);
            }
            online = !Bukkit.unloadWorld(world, false);
        }
    }

    @Override
    public void restart() {
        if (world != null){
            close();
            create();
        }
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public String getWorldName() {
        return name;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public TeamServiceImpl getTeamService() {
        return teamService;
    }

    @Override
    public TestReamUtils getUtils(){
        return testReamUtils;
    }

    @Override
    public IronCube getCube() {
        return ironCube;
    }
}
