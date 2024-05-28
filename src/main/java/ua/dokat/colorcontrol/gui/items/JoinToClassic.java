package ua.dokat.colorcontrol.gui.items;

import lombok.Getter;
import me.anfanik.steda.api.utility.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

@Getter
public class JoinToClassic implements GUIItem {

    private final int slot = 0;

    @Override
    public ItemStack crete() {
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.DIAMOND));
        builder.setName(color("&b2x5"));

        RealmService service = RealmService.getInstance();

        builder.appendLore(color("&aServers online: &6" + service.classicRealmCount()));
        builder.appendLore(color("&aPlayer in game: &6" + service.onlinePlayers((Integer) Config.getC("startId"), (Integer) Config.getC("endId"))));

        return builder.build();
    }
}
