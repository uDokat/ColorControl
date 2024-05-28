package ua.dokat.colorcontrol.newrealms.entity;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCube extends Cube{

    private final ItemStack item;
    private int red = 0;
    private int blue = 0;

    public ItemCube(ItemStack item, int time, int red, int blue) {
        super(time);
        this.item = item;
        this.red = red;
        this.blue = blue;
    }

    @Override
    public void giveEffect(Player player) {
        if (item != null) player.getInventory().addItem(item);
        super.giveEffect(player);
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public int addRed() {
        red++;
        return super.addRed();
    }

    @Override
    public int removeRed() {
        red--;
        return super.removeRed();
    }

    @Override
    public int addBlue() {
        blue++;
        return super.addBlue();
    }

    @Override
    public int removeBlue() {
        blue--;
        return super.removeBlue();
    }
}
