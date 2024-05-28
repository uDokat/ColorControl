package ua.dokat.colorcontrol.newrealms.entity;

import org.bukkit.entity.Player;

public class ExpCube extends Cube{

    private final int exp;

    public ExpCube(int exp, int time) {
        super(time);
        this.exp = exp;
    }

    @Override
    public void giveEffect(Player player) {
        player.giveExp(exp);
        super.giveEffect(player);
    }
}
