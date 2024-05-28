package ua.dokat.colorcontrol.gui.items;

import lombok.Getter;
import me.anfanik.steda.api.utility.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public class BackToHub implements GUIItem {

    private final int slot = 8;

    @Override
    public ItemStack crete() {
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.REDSTONE));
        builder.setName(color("&cTo lobby"));
        return builder.build();
    }
}
