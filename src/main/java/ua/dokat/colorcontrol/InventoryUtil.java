package ua.dokat.colorcontrol;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ua.dokat.colorcontrol.gui.items.GUIItem;

import java.util.List;

public class InventoryUtil {

    public static void giveItems(Player player, GUIItem... items){
        player.getInventory().clear();

        for (GUIItem item : items){
            player.getInventory().setItem(item.getSlot(), item.crete());
        }
    }

    public static void giveStartItems(Player player){
        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        item.setItemMeta(meta);

        player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD), item);
    }

    public static void giveEveryoneStartItems(List<Player> players){
        for (Player player : players) giveStartItems(player);
    }
}
