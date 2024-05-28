package ua.dokat.colorcontrol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.newrealms.entity.*;
import ua.dokat.colorcontrol.newrealms.services.CubeService;
import ua.dokat.colorcontrol.newrealms.services.GameService;
import ua.dokat.colorcontrol.newrealms.services.TeamService;

import java.util.UUID;

public class BlockBreakListener implements Listener {

    public BlockBreakListener(){
        Bukkit.getPluginManager().registerEvents(this, ColorControl.getInstance());
    }

    private final Vector vecRed1 = new Vector(-175, 41, 97);
    private final Vector vecRed2 = new Vector(-146, 36, 92);

    private final BoundingBox boxRed = BoundingBox.of(vecRed1, vecRed2);

    private final Vector vecBlue1 = new Vector(-146, 41, 184);
    private final Vector vecBlue2 = new Vector(-175, 36, 189);

    private final BoundingBox boxBlue = BoundingBox.of(vecBlue1, vecBlue2);

    @EventHandler
    public void breakLobby(BlockBreakEvent event){
        Block block = event.getBlock();

        if (event.getBlock().getWorld().getName().equalsIgnoreCase("lobby")){
            event.setCancelled(true);
        } else if (block.getType() == Material.WHITE_CONCRETE ||
                block.getType() == Material.RED_CONCRETE ||
                block.getType() == Material.BLUE_CONCRETE ||
                block.getType() == Material.BLACK_CONCRETE){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void breakGame(BlockBreakEvent event){
        if (!event.getBlock().getWorld().getName().equalsIgnoreCase("lobby")) {

            if (event.getBlock().getBoundingBox().overlaps(boxRed) ||
                event.getBlock().getBoundingBox().overlaps(boxBlue)) return;

            UUID uuid = event.getPlayer().getUniqueId();
            Block block = event.getBlock();
            if (block.getType() == Material.WHITE_CONCRETE ||
                block.getType() == Material.RED_CONCRETE ||
                block.getType() == Material.BLUE_CONCRETE){

                if (block.getLocation().getY() <= 39) return;

                Realm realm = GameService.getInstance().getRealmByUUID(uuid);
                TeamService service = TeamService.getInstance();
                Team team = service.findTeamByUUID(uuid);

                if (team instanceof RedTeam && !(block.getType() == Material.RED_CONCRETE)){
                    Cube cube = CubeService.getInstance().getCube(block.getBoundingBox(), realm);

                    if (cube != null){
                        cube.addRed();
                        if (block.getType() == Material.BLUE_CONCRETE) cube.removeBlue();
                    }

                    block.setType(Material.RED_CONCRETE);
                    return;
                }

                if (team instanceof BlueTeam && !(block.getType() == Material.BLUE_CONCRETE)){
                    Cube cube = CubeService.getInstance().getCube(block.getBoundingBox(), realm);

                    if (cube != null){
                        cube.addBlue();
                        if (block.getType() == Material.RED_CONCRETE) cube.removeRed();
                    }

                    block.setType(Material.BLUE_CONCRETE);
                }
            }
        }
    }
}
