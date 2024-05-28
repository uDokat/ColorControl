package ua.dokat.colorcontrol.gui.items;

import lombok.Getter;
import me.anfanik.steda.api.utility.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public class TeamSelection implements GUIItem {

    private final int slot = 0;

    @Override
    public ItemStack crete() {
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.BOOK));
        builder.setName(color("&bSelect a command"));
        return builder.build();
    }
}
