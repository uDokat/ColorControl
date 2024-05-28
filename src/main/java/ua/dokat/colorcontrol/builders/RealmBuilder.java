package ua.dokat.colorcontrol.builders;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.CubeService;

public class RealmBuilder {

    public static Realm build(String name, int id){
        return new Realm(name, buildWorld(name), id, true, CubeService.getInstance().fill());
    }

    private static World buildWorld(String name){
        WorldCreator creator = new WorldCreator(name);
        World world = Bukkit.createWorld(creator);

        creator.generator("null");
        creator.generateStructures(false);

        world.setKeepSpawnInMemory(false);
        world.setMonsterSpawnLimit(0);
        world.setAnimalSpawnLimit(0);
        world.setWaterAnimalSpawnLimit(0);
        world.setAutoSave(false);
        world.setGameRule(GameRule.KEEP_INVENTORY, false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, true);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);

        return world;
    }
}
