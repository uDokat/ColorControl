package ua.dokat.colorcontrol.commands.realmoperation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ua.dokat.colorcontrol.commands.Executor;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

public class RestartRealm extends Executor {

    public RestartRealm(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        RealmService service = RealmService.getInstance();

        if (args.length == 1){
            Realm realm = service.getByName(args[0]);

            if (realm == null){
                sender.sendMessage("Realm is null");
                return;
            }

            service.restart(realm);
            sender.sendMessage(args[0] + " restarted");
        }
    }
}
