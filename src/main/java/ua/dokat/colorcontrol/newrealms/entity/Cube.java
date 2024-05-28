package ua.dokat.colorcontrol.newrealms.entity;

import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public abstract class Cube {

    private int red = 0;
    private int blue = 0;

    private final int time;

    public Cube(int time) {
        this.time = time;
    }

    public void giveEffect(Player player){

    }

    public int addRed(){
        return red++;
    }

    public int addBlue(){
        return blue++;
    }

    public int removeRed(){
        return red--;
    }

    public int removeBlue(){
        return blue--;
    }
}
