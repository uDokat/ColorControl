package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.newrealms.services.GameProfileService;

import java.util.UUID;

@Getter
public class GameProfile implements Utils {

     private final Player player;
     private final UUID uuid;
     private String nickName;
     private final Profile profile = null;

     @Setter
     private Team team;
     private final PlayerGameStats stats;

     private final String teamColor = "&f";

    public GameProfile(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.nickName = player.getName();
        this.stats = new PlayerGameStats(uuid);
    }

    public GameProfile(UUID uuid){
        this.player = Bukkit.getPlayer(uuid);
        this.uuid = uuid;
        if (player != null) this.nickName = player.getName();
        this.stats = new PlayerGameStats(uuid);
    }
}
