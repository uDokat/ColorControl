package ua.dokat.colorcontrol.gui.items;

import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.Utils;

public interface GUIItem extends Utils {
    ItemStack crete();
    int getSlot();
}
