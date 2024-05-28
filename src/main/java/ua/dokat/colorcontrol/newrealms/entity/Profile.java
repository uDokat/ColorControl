package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final Player player;
    private final UUID uuid;
    private final String nickName;

    private final String nameColor = "&f";

    private final int win = 0;
    private final int lose = 0;
    private final int kills = 0;
    private final int deaths = 0;

    public Profile(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.nickName = player.getName();
    }
}
