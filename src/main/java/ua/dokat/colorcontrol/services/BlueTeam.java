package ua.dokat.colorcontrol.services;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ua.dokat.colorcontrol.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlueTeam implements Team, Utils {
    private final List<UUID> uuids = new ArrayList<>();

    private final ItemStack item;
    private final Location spawn = new Location(null, -160.5, 41, 186.4, -180, 0);

    public BlueTeam(ItemStack item){
        this.item = item;
    }

    @Override
    public ItemStack addPlayer(Player player){
        String nickName = player.getName();
        ArrayList<String> lor = (ArrayList<String>) item.getItemMeta().getLore();
        if (lor == null) lor = new ArrayList<>();

        if (!lor.contains(color("&9" + nickName))){
            lor.add(color("&9" + nickName));
            updateItem(lor);
            uuids.add(player.getUniqueId());
        }

        return item;
    }

    @Override
    public ItemStack removePlayer(Player player){
        String nickName = player.getName();
        List<String> lor = item.getItemMeta().getLore();
        if (lor == null) lor = new ArrayList<>();

        if (lor.contains(color("&9" + nickName))){
            lor.remove(color("&9" + nickName));
            updateItem(lor);
            uuids.remove(player.getUniqueId());
        }

        return item;
    }

    @Override
    public void teleport(Player player) {
        spawn.setWorld(player.getWorld());
        player.teleport(spawn);
    }

    @Override
    public void updateItem(List<String> lor){
        ItemMeta meta = item.getItemMeta();

        meta.setLore(lor);
        item.setItemMeta(meta);
    }

    @Override
    public boolean isPlayerInTeam(Player player) {
        return uuids.contains(player.getUniqueId());
    }

    @Override
    public boolean isTeamNotFull(){
        return uuids.size() < 5;
    }

    @Override
    public List<UUID> getPlayers() {
        return uuids;
    }

    @Override
    public ItemStack getTeamItem() {
        return item;
    }

    @Override
    public void setColor() {
        for (UUID uuid : uuids){
            if (Bukkit.getPlayer(uuid) != null) {
                Player player = Bukkit.getPlayer(uuid);
                player.playerListName(Component.text(color("&9" + player.getName())));
            }
        }
    }
}
