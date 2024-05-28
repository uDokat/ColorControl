package ua.dokat.colorcontrol.gui;

import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.newrealms.entity.Realm;

public interface GUIManager {
    void createInventory(Realm realm);
    void createInventory();
    void openGUI(Player player);
}
