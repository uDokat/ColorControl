package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ua.dokat.colorcontrol.BossBar;

import java.util.List;

@Getter
@Setter
public class Game {

    private int taskId;
    private BossBar bossBar;

    public Game() {
        bossBar = new BossBar();
    }
}
