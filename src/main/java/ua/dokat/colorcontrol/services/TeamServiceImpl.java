package ua.dokat.colorcontrol.services;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.gui.TeamSelectionGUI;

public class TeamServiceImpl implements TeamService{
    private final TeamSelectionGUI gui = new TeamSelectionGUI(null);
    private final Inventory inventory = gui.getInventory();

    private final RedTeam redTeam = new RedTeam(inventory.getItem(2));
    private final BlueTeam blueTeam = new BlueTeam(inventory.getItem(6));
    private final NeutralTeam neutralTeam = new NeutralTeam();

    @Override
    public void assignPlayerToTeam(Player player, ItemStack item) {
        Team currentTeam = getPlayerTeam(player);
        if (currentTeam != neutralTeam){
            updateTeamInInventory(currentTeam.removePlayer(player), gui.getItemSlot(currentTeam.getTeamItem().getType()));
        }else {
            neutralTeam.removePlayer(player);
        }

        Team newTeam = getTeamByMaterial(item.getType());
        if (newTeam != null){
            updateTeamInInventory(newTeam.addPlayer(player), gui.getItemSlot(newTeam.getTeamItem().getType()));
        }
    }

    @Override
    public void updateTeamInInventory(ItemStack item, int slot) {
        inventory.setItem(slot, item);
    }

    @Override
    public Team getPlayerTeam(Player player) {
        if (redTeam.isPlayerInTeam(player)) return redTeam;
        if (blueTeam.isPlayerInTeam(player)) return blueTeam;
        return neutralTeam;
    }

    @Override
    public Team getTeamByMaterial(Material material) {
        if (material == Material.RED_CONCRETE) return redTeam;
        if (material == Material.BLUE_CONCRETE) return blueTeam;
        return neutralTeam;
    }

    public TeamSelectionGUI getGui() {
        return gui;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public RedTeam getRedTeam() {
        return redTeam;
    }

    @Override
    public BlueTeam getBlueTeam() {
        return blueTeam;
    }

    @Override
    public NeutralTeam getNeutralTeam() {
        return neutralTeam;
    }
}
