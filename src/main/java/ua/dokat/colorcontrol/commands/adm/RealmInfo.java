package ua.dokat.colorcontrol.commands.adm;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ua.dokat.colorcontrol.commands.Executor;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.services.RealmService;

import java.awt.*;

public class RealmInfo extends Executor {

    public RealmInfo(String name) {
        super(name);
    }

    private final ChatColor cRealm = ChatColor.of(new Color(241, 103, 103));
    private final ChatColor cParam = ChatColor.of(new Color(246, 187, 72));
    private final ChatColor cNums = ChatColor.of(new Color(147, 103, 241));
    private final ChatColor cTrue = ChatColor.of(new Color(139, 241, 103));
    private final ChatColor cFalse = ChatColor.of(new Color(241, 103, 103));
    private final ChatColor cEl = ChatColor.of(new Color(109, 109, 109));
    private final ChatColor w = ChatColor.of(new Color(255,255,255));

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {

        for (Realm realm : RealmService.getInstance().getAll()) {
            sender.sendMessage(color(
                    cRealm + realm.getName() + cEl + " : " + w + "[ " +
                            cParam + "Players" + cEl + " : " + cNums + realm.getPlayers().size() + w + " | " +
                            cParam + "isOnline" + cEl + " : " + get(realm.isOnline()) + w + " | " +
                            cParam + "isStarted" + cEl + " : " + get(realm.isStarted()) + w + " | " +
                            cParam + "id" + cEl + " : " + cNums + realm.getId() + w + " ]"
            ));
        }
    }

    private String get(boolean b){
        if (b){
            return cTrue + "true";
        }else {
            return cFalse + "false";
        }
    }
}