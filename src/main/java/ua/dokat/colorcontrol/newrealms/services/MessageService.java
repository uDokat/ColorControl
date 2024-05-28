package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.Utils;

import java.util.List;

public class MessageService implements Utils {

    private static MessageService instance;

    public void joinMessage(List<Player> players, String joinPlayerName, int countPlayers){
        sendEveryone(players, "&cColorControl &7>> &a" + joinPlayerName + " &ejoin (&b" + countPlayers + "&e/&b" + Config.getC("maxPlayers") + "&e)");
    }

    public void leaveMessage(List<Player> players, String leavePlayerName, int countPlayers){

    }

    public void killPlayer(List<Player> players, Player killer, Player victim){
        sendEveryone(players, "&cColorControl&f: " + getColorTeam(killer.getUniqueId()) + killer.getName() + " &fkilled " + getColorTeam(victim.getUniqueId())+ victim.getName());
    }

    public void deathPlayer(List<Player> players, Player player){
        sendEveryone(players, "&cColorControl&f: " + getColorTeam(player.getUniqueId()) + player.getName() + " &fdeath");
    }

    private void sendEveryone(List<Player> players, String message){
        for (Player player : players) player.sendMessage(color(message));
    }

    public static MessageService getInstance() {
        if (instance == null) return instance = new MessageService();
        return instance;
    }
}
