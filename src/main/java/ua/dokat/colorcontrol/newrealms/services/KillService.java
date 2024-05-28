package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.newrealms.entity.PlayerGameStats;

public class KillService implements Service{

    private static KillService instance;

    @Override
    public void register(Player player) {
        PlayerGameStats stats = GameProfileService.getInstance().getStats(player.getUniqueId());
        stats.addKill();
    }

    public static KillService getInstance() {
        if (instance == null) return instance = new KillService();
        return instance;
    }
}
