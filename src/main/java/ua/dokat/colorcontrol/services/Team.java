package ua.dokat.colorcontrol.services;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface Team {
    ItemStack addPlayer(Player player);

    ItemStack removePlayer(Player player);

    void teleport(Player player);

    void updateItem(List<String> lor);

    boolean isPlayerInTeam(Player player);

    boolean isTeamNotFull();

    List<UUID> getPlayers();

    ItemStack getTeamItem();

    void setColor();
}
