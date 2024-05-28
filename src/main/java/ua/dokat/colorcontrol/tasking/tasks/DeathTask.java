package ua.dokat.colorcontrol.tasking.tasks;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.InventoryUtil;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.newrealms.services.TeleportService;

public class DeathTask implements Task, Utils {

    @Setter
    private int taskId;
    private final Player player;
    private int timeToRevival = 3;

    public DeathTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (timeToRevival == 0){
            TeleportService.getInstance().teleportToTeamSpawn(player);
            player.setGameMode(GameMode.SURVIVAL);
            InventoryUtil.giveStartItems(player);

            Bukkit.getScheduler().cancelTask(taskId);
        }

        player.sendTitle(color("&c" + timeToRevival), "", 5, 5, 5);
        timeToRevival--;
    }
}
