package ua.dokat.colorcontrol.services;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.realms.intefraces.Realm;

public interface TeamService {
    void assignPlayerToTeam(Player player, ItemStack item);

    void updateTeamInInventory(ItemStack item, int slot);

    Team getPlayerTeam(Player player);

    Team getTeamByMaterial(Material material);

    Team getNeutralTeam();

    Team getRedTeam();

    Team getBlueTeam();
}
