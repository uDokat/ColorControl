package ua.dokat.colorcontrol.commands.game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.commands.Executor;
import ua.dokat.colorcontrol.newrealms.services.RealmService;
import ua.dokat.colorcontrol.newrealms.services.TeleportService;

public class Lobby extends Executor {

    public Lobby(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (isPlayer(sender)) return;

        Player player = (Player) sender;

        if (player.getWorld() == ColorControl.getMainWorld()) return;

        if (args.length == 0){
            TeleportService.getInstance().teleportToLobby(player);
        }
    }
}
