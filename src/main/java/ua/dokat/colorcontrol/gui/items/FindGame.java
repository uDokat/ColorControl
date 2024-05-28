package ua.dokat.colorcontrol.gui.items;

import lombok.Getter;
import me.anfanik.steda.api.utility.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public class FindGame implements GUIItem {

    private final int slot = 4;

    @Override
    public ItemStack crete() {
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.BLAZE_ROD));
        builder.setName(color("&bFind Game"));

        return builder.build();
    }
}
