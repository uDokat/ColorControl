package ua.dokat.colorcontrol.gui.items;

import lombok.Getter;
import me.anfanik.steda.api.utility.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

@Getter
public class JoinToDef implements GUIItem {

    private final int slot = 0;

    @Override
    public ItemStack crete() {
        ItemBuilder.ItemStackItemBuilder builder = ItemBuilder.fromItem(new ItemStack(Material.DIAMOND));
        builder.setName(color("&b2x5 Defenders"));

        RealmService service = RealmService.getInstance();

        builder.appendLore(color("&aServers online: &6" + service.defRealmCount()));
        builder.appendLore(color("&aPlayer in game: &6" + service.onlinePlayers((Integer) Config.getD("startId"), (Integer) Config.getD("endId"))));

        return builder.build();
    }
}
