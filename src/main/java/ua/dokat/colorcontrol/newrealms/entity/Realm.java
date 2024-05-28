package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BoundingBox;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.gui.TeamSelectionGUI;

import java.util.*;

@Getter
public class Realm {

    private final String name;
    private final World world;
    private final int id;

    @Setter
    private boolean online;
    @Setter
    private boolean isStarted = false;

    private final NeutralTeam neutral = new NeutralTeam();
    private final RedTeam red = new RedTeam(Config.getLoc("redSpawn"));
    private final BlueTeam blue = new BlueTeam(Config.getLoc("blueSpawn"));

    private final Game game = new Game();

    private final List<UUID> players = new ArrayList<>();
    private final Map<BoundingBox, Cube> cubes;
    private final Map<UUID, GameProfile> profiles = new HashMap<>(); //experimental

    public Realm(String name, World world, int id, boolean online, Map<BoundingBox, Cube> cubes) {
        this.name = name;
        this.world = world;
        this.id = id;
        this.online = online;
        this.cubes = cubes;
    }
}
