package ua.dokat.colorcontrol.services;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NeutralTeam implements Team {
    private final List<UUID> uuids = new ArrayList<>();

    @Override
    public ItemStack addPlayer(Player player) {
        if (!uuids.contains(player.getUniqueId())) uuids.add(player.getUniqueId());
        return null;
    }

    @Override
    public ItemStack removePlayer(Player player) {
        uuids.remove(player.getUniqueId());
        return null;
    }

    @Override
    public void teleport(Player player) {

    }

    @Override
    public void updateItem(List<String> lor) {

    }

    @Override
    public boolean isPlayerInTeam(Player player) {
        return uuids.contains(player.getUniqueId());
    }

    @Override
    public boolean isTeamNotFull() {
        return false;
    }

    @Override
    public List<UUID> getPlayers() {
        return uuids;
    }

    @Override
    public ItemStack getTeamItem() {
        return null;
    }

    @Override
    public void setColor() {

    }
}
