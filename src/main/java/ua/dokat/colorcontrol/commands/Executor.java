package ua.dokat.colorcontrol.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Utils;

public abstract class Executor implements CommandExecutor, Utils {

    public Executor(String name){
        PluginCommand command = ColorControl.getInstance().getCommand(name);
        if (command != null) command.setExecutor(this);
    }

    public abstract void execute(CommandSender sender, Command command, String label, String[] args);

    public boolean isPlayer(CommandSender sender){
        if (!(sender instanceof Player)){
            sender.sendMessage(color("&cThis command only for players"));
            return true;
        }

        return false;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        execute(sender, command, label, args);
        return true;
    }
}
