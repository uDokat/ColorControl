package ua.dokat.colorcontrol.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.InventoryUtil;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.gui.items.FindGame;

public class JoinPlayerListener implements Listener, Utils {

    public JoinPlayerListener(){
        Bukkit.getPluginManager().registerEvents(this, ColorControl.getInstance());
    }

    @EventHandler
    public void join(PlayerJoinEvent event){
        event.joinMessage(Component.text(""));
        Player player = event.getPlayer();

        InventoryUtil.giveItems(player, new FindGame());

        player.teleport(new Location(ColorControl.getMainWorld(), -90.5, 56, 18.5, -130, 0));
    }
}
