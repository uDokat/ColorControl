package ua.dokat.colorcontrol.newrealms.entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BlockCube extends Cube{

    private final Material blockMaterial;
    private final Vector vector;

    public BlockCube(Material blockMaterial, Vector vector, int time) {
        super(time);
        this.blockMaterial = blockMaterial;
        this.vector = vector;
    }

    @Override
    public void giveEffect(Player player) {
        Location location = vector.toLocation(player.getWorld());
        if (location.getBlock().getType() != blockMaterial) location.getBlock().setType(blockMaterial);
        super.giveEffect(player);
    }
}
