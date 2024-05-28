package ua.dokat.colorcontrol.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ua.dokat.colorcontrol.gui.items.JoinToClassic;
import ua.dokat.colorcontrol.gui.items.JoinToDef;
import ua.dokat.colorcontrol.newrealms.entity.Realm;

public class LobbyGUI implements GUIManager {
    private Inventory inventory;

    public LobbyGUI(){
        createInventory();
    }

    @Override
    public void createInventory(Realm realm) {
    }

    @Override
    public void createInventory() {
        inventory = Bukkit.createInventory(null, 9, Component.text(""));
        inventory.setItem(2, new JoinToClassic().crete());
        inventory.setItem(6, new JoinToDef().crete());
    }

    @Override
    public void openGUI(Player player) {
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
