package ua.dokat.colorcontrol;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ua.dokat.colorcontrol.commands.test.TestYmlParser;
import ua.dokat.colorcontrol.initializer.CommandInitializer;
import ua.dokat.colorcontrol.initializer.EventListenerInitializer;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

import java.io.File;

public final class ColorControl extends JavaPlugin {

    private static ColorControl instance;

    private static World mainWorld;
    private static Location lobby;
    private static FileConfiguration configuration;
    private static FileConfiguration mapConfig;
    private static TestYmlParser parser;

    @Override
    public void onEnable() {
        instance = this;
        mainWorld = Bukkit.getWorld("lobby");
        lobby = new Location(mainWorld, -90.5, 56, 18.5, -130, 0);
        configuration = loadConfig("realm_settings.yml");
        mapConfig = loadConfig("test.yml");
        parser = new TestYmlParser();

        RealmService.getInstance().create((String) Config.getC("name"), (Integer) Config.getC("startId"), (Integer) Config.getC("realmCount"));
//        RealmService.getInstance().create((String) Config.getD("name"), (Integer) Config.getD("startId"), (Integer) Config.getD("realmCount"));
        new CommandInitializer();
        new EventListenerInitializer();

        getLogger().info(ChatColor.BLUE + "Test Enable");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Location getLobby() {
        return lobby;
    }

    public static ColorControl getInstance() {
        return instance;
    }

    public static World getMainWorld(){
        return mainWorld;
    }

    public static FileConfiguration getConfiguration(){
        return configuration;
    }

    public static FileConfiguration getMapConfig() {
        return mapConfig;
    }

    public static TestYmlParser getParser() {
        return parser;
    }

    private FileConfiguration loadConfig(String configName){
        File configFile = new File(getDataFolder(), configName);
        saveResource(configName, true);
        return YamlConfiguration.loadConfiguration(configFile);
    }
}
