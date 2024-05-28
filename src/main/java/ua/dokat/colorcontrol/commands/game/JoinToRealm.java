package ua.dokat.colorcontrol.commands.game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.commands.Executor;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

public class JoinToRealm extends Executor {

    public JoinToRealm(String name){
        super(name);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (isPlayer(sender)) return;

        Player player = (Player) sender;
        RealmService service = RealmService.getInstance();

        if (args.length > 1){
            player.sendMessage(color("&6" + command.getUsage()));
            return;
        }

        if (args.length == 1){
            String id = args[0];

            if (isNum(id)){
                service.joinById(player, Integer.parseInt(id));
            }
        }else{
            service.joinFreeClassicRealm(player);
        }
    }

    private boolean isNum(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
