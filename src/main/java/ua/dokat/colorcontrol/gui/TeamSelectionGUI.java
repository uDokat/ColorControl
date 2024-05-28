package ua.dokat.colorcontrol.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.gui.items.BlueTeam;
import ua.dokat.colorcontrol.gui.items.RedTeam;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.HashMap;

public class TeamSelectionGUI implements GUIManager {
    private Inventory inventory;
    private final HashMap<Material, Integer> items = new HashMap<>();

    public TeamSelectionGUI(Realm realm){
        createInventory(realm);
    }

    @Override
    public void createInventory(Realm realm) {
        inventory = Bukkit.createInventory(null, 9, Component.text("Select a command"));
        ItemStack redItem = TeamService.getInstance().redItem(realm.getRed());
        ItemStack blueItem = TeamService.getInstance().blueItem(realm.getBlue());

        items.put(redItem.getType(), 2);
        items.put(blueItem.getType(), 6);

        inventory.setItem(2, redItem);
        inventory.setItem(6, blueItem);
    }

    @Override
    public void createInventory() {

    }

    @Override
    public void openGUI(Player player) {
        player.openInventory(inventory);
    }

    public int getItemSlot(Material material) {
        return items.get(material);
    }

    public HashMap<Material, Integer> getItems() {
        return items;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
