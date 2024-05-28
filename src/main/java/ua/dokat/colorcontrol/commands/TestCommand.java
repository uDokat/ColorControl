package ua.dokat.colorcontrol.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.newrealms.entity.BlueTeam;
import ua.dokat.colorcontrol.newrealms.entity.Cube;
import ua.dokat.colorcontrol.newrealms.entity.RedTeam;
import ua.dokat.colorcontrol.newrealms.entity.Team;
import ua.dokat.colorcontrol.newrealms.services.CubeService;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.awt.*;
import java.util.*;

public class TestCommand implements CommandExecutor {

    public TestCommand(){
        ColorControl.getInstance().getCommand("test").setExecutor(this);
    }
    BukkitScheduler scheduler = Bukkit.getScheduler();

    int taskid;

    int d = 15 * 60;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        startTimer(player);

        return true;
    }

    public void startTimer(Player player){
        taskid = scheduler.scheduleSyncRepeatingTask(ColorControl.getInstance(), () -> {
            if (d > 0){
                d--;
                for (Player p : player.getWorld().getPlayers()){
                    Cube cube = CubeService.getInstance().getCube(p.getBoundingBox(), GameService.getInstance().getRealmByUUID(p.getUniqueId()));
                    if (cube != null){
                        if (d % cube.getTime() == 0) {
                            if (isDub(cube, TeamService.getInstance().findTeamByUUID(p.getUniqueId()))) cube.giveEffect(p);
                        }
                    }
                }
            }else {
                scheduler.cancelTask(taskid);
            }
        }, 0, 5);

        ColorControl.getInstance().getLogger().info("Timer Started");
    }

    private boolean isDub(Cube cube, Team team){
        if (team instanceof RedTeam && cube.getRed() == 9) return true;
        return team instanceof BlueTeam && cube.getBlue() == 9;
    }

    public String get(Player player, String message) {
        Random random = new Random();
        int s = 100;
        int b = 100;

        ChatColor startColor = ChatColor.of(new Color(random.nextInt(56), random.nextInt(56), random.nextInt(56))); // Тёмные оттенки
        ChatColor endColor = ChatColor.of(new Color(random.nextInt(200) + 56, random.nextInt(200) + 56, random.nextInt(200) + 56)); // Светлые оттенки

        char[] chars = message.toCharArray();

        for (int i = 0; i < 1; i++) {
            StringBuilder builder = new StringBuilder();

            int step = chars.length;
            for (char c : chars) {
                ChatColor color = blendColors(startColor, endColor, step, chars.length, s, b, random);
                builder.append(color).append(String.valueOf(c));
                step--;
            }

            return builder.toString();
        }

        return null;
    }

    private ChatColor blendColors(ChatColor start, ChatColor end, int step, int steps, int s, int b, Random random) {
        int red = start.getColor().getRed() + ((end.getColor().getRed() - start.getColor().getRed()) * step / steps);
        int green = start.getColor().getGreen() + ((end.getColor().getGreen() - start.getColor().getGreen()) * step / steps);
        int blue = start.getColor().getBlue() + ((end.getColor().getBlue() - start.getColor().getBlue()) * step / steps);

        return ChatColor.of(new Color(red, green, blue));
    }
}
