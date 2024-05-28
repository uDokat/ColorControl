package ua.dokat.colorcontrol;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    private final static FileConfiguration config = ColorControl.getConfiguration();

    public static Object getC(String path){
        return config.get("classic." + path);
    }

    public static Object getD(String path){
        return config.get("def." + path);
    }

    public static Object getW(String path){
        return config.get("world_rules." + path);
    }

    public static Object get(String path){
        return config.get(path);
    }

    public static Location getLoc(String path){
        List<Double> nums = config.getDoubleList(path);
        return new Location(null, nums.get(0), nums.get(1), nums.get(2), Float.parseFloat(nums.get(3).toString()), Float.parseFloat(nums.get(4).toString()));
    }
}
