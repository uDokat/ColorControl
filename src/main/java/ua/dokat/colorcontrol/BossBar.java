package ua.dokat.colorcontrol;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class BossBar implements Utils{

    @Getter
    private final net.kyori.adventure.bossbar.BossBar bossBar = net.kyori.adventure.bossbar.BossBar.bossBar(Component.text("/"), 1, net.kyori.adventure.bossbar.BossBar.Color.YELLOW, net.kyori.adventure.bossbar.BossBar.Overlay.NOTCHED_20);
    @Setter
    private List<Player> players;

    public void update(int time){
        float progress = (float) time / 900;
        bossBar.progress(progress);
        bossBar.name(Component.text("Конец игры через: " + formatSeconds(time)));
    }

    public void showBossBar(){
        for (Player player : players) player.showBossBar(bossBar);
    }

    public void hideBossBar(){
        for (Player player : players) player.hideBossBar(bossBar);
    }
}
