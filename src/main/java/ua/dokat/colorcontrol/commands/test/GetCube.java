package ua.dokat.colorcontrol.commands.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.newrealms.entity.Cube;
import ua.dokat.colorcontrol.newrealms.services.CubeService;
import ua.dokat.colorcontrol.newrealms.services.GameService;

public class GetCube implements CommandExecutor {

    public GetCube(){
        ColorControl.getInstance().getCommand("cube").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        BoundingBox box = CubeService.getInstance().testGetCube(player.getBoundingBox());

        if (box == null){
            player.sendMessage(String.valueOf(CubeService.getInstance().s()));
            return true;
        }

        Cube cube = CubeService.getInstance().getCube(box, GameService.getInstance().getRealmByUUID(player.getUniqueId()));

        player.sendMessage(box.toString() + "\n" + cube.getRed() + "\n" + cube.getBlue());
        cube.giveEffect(player);

        return true;
    }
}
