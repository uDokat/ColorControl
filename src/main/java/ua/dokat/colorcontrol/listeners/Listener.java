package ua.dokat.colorcontrol.listeners;

import org.bukkit.Bukkit;
import ua.dokat.colorcontrol.ColorControl;

public abstract class Listener implements org.bukkit.event.Listener {

    public Listener(){
        Bukkit.getPluginManager().registerEvents(this, ColorControl.getInstance());
    }
}
