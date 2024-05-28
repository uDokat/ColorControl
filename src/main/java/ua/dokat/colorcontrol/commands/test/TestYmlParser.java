package ua.dokat.colorcontrol.commands.test;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.newrealms.entity.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class TestYmlParser {

    private static TestYmlParser instance;
    private final FileConfiguration config = ColorControl.getMapConfig();

    public Map<BoundingBox, Cube> fill(List<BoundingBox> boxes){
        Map<BoundingBox, Cube> cubes = new HashMap<>();

        for (String cube : config.getKeys(false)){
            BoundingBox box = BoundingBox.of(vec(cube, "vec1"), vec(cube, "vec2"));
            cubes.put(box, buildCube(cube, box.getCenter()));
            if (!boxes.contains(box)) boxes.add(box);
        }

        return cubes;
    }

    private Vector vec(String cube, String vec){
        List<Integer> loc = config.getIntegerList(cube + "." + vec);
        return new Vector(loc.get(0), loc.get(1), loc.get(2));
    }

    private ItemStack item(String cube){
        return new ItemStack(Material.getMaterial(config.getString(cube + ".item")));
    }

    private PotionEffectType effect(String cube){
        return PotionEffectType.getByName(config.getString(cube + ".effect"));
    }

    private int exp(String cube){
        return config.getInt(cube + ".exp");
    }

    private Material block(String cube){
        if (config.getString(cube + ".block") == null) return null;

        Material material = Material.getMaterial(config.getString(cube + ".block"));
        if (material == null) ColorControl.getInstance().getLogger().info(ChatColor.RED + "ERROR " + ChatColor.GOLD + "item <" + cube + "> is null");

        return material;
    }

    private int dug(String cube){
        if (config.isSet(cube + ".red")) return config.getInt(cube + ".red");
        if (config.isSet(cube + ".blue")) return config.getInt(cube + ".blue");
        return 0;
    }

    private Cube buildCube(String cube, Vector vector){
        if (config.isSet(cube + ".item")){
            return new ItemCube(item(cube), time(cube), dug(cube), dug(cube));
        } else if (config.isSet(cube + ".effect")) {
            return new EffectCube(effect(cube), time(cube));
        } else if (config.isSet(cube + ".exp")) {
            return new ExpCube(exp(cube), time(cube));
        } else if (config.isSet(cube + ".block")) {
            return new BlockCube(block(cube), vector, time(cube));
        }

        return null;
    }

    private int time(String cube){
        return config.getInt(cube + ".time");
    }

    public static TestYmlParser getInstance() {
        if (instance == null) return instance = new TestYmlParser();
        return instance;
    }
}
