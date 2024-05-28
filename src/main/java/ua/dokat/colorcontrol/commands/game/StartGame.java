package ua.dokat.colorcontrol.commands.game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.commands.Executor;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.GameService;

public class StartGame extends Executor {

    public StartGame(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        Realm realm = GameService.getInstance().getRealmByUUID(player.getUniqueId());

        if (!realm.isStarted()) GameService.getInstance().start(realm);
    }
}
