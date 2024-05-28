package ua.dokat.colorcontrol.tasking;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.tasking.tasks.DeathTask;
import ua.dokat.colorcontrol.tasking.tasks.Task;

public class TaskBuilder{

    public static void runTaskTimer(Task task){
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimer(ColorControl.getInstance(), task, 0L, 20L);
        task.setTaskId(bukkitTask.getTaskId());
    }

    public static int runTaskGame(Runnable runnable){
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(ColorControl.getInstance(), runnable, 0, 20);
    }
}
