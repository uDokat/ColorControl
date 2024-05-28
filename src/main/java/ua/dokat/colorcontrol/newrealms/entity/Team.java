package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public abstract class Team {

    private Location spawn = null;
    private final List<UUID> players = new ArrayList<>();

    public Team(Location spawn) {
        this.spawn = spawn;
    }

    public Team(){

    }

}
