package ua.dokat.colorcontrol;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.newrealms.entity.BlueTeam;
import ua.dokat.colorcontrol.newrealms.entity.RedTeam;
import ua.dokat.colorcontrol.newrealms.entity.Team;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface Utils {

   default String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

   default String formatSeconds(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

   default List<String> nicknames(List<UUID> uuids, String color){
        List<String> list = new ArrayList<>();

        for (UUID uuid : uuids){
            Player player = Bukkit.getPlayer(uuid);

            if (player != null) list.add(color(color + player.getName()));
        }

        return list;
    }

    default List<Player> players(List<UUID> uuids){
        List<Player> list = new ArrayList<>();

        for (UUID uuid : uuids){
            Player player = Bukkit.getPlayer(uuid);

            if (player != null) list.add(player);
        }

        return list;
    }

    default String getColorTeam(UUID uuid){
        Team team = TeamService.getInstance().findTeamByUUID(uuid);

        if (team instanceof RedTeam) return "&c";
        if (team instanceof BlueTeam) return "&9";

        return "&f";
    }
}
