package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PlayerGameStats {

    private final UUID uuid;
    private int kills = 0;
    private int deaths = 0;

    public PlayerGameStats(UUID uuid) {
        this.uuid = uuid;
    }

    public void addKill(){
        kills++;
    }

    public void addDeath(){
        deaths++;
    }
}
