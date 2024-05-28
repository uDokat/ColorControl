package ua.dokat.colorcontrol.commands.realmoperation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ua.dokat.colorcontrol.commands.Executor;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

public class CloseRealm extends Executor {

    public CloseRealm(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1){
            sender.sendMessage(command.getUsage());
            return;
        }

        RealmService service = RealmService.getInstance();

        if (args.length == 1){
            Realm realm = service.getByName(args[0]);

            if (realm == null){
                sender.sendMessage("Realm is null");
                return;
            }

            boolean b = service.close(realm);
            if (b) sender.sendMessage(args[0] + " closed");
        }
    }
}
