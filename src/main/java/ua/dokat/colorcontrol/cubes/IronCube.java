package ua.dokat.colorcontrol.cubes;

import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import ua.dokat.colorcontrol.services.BlueTeam;
import ua.dokat.colorcontrol.services.RedTeam;
import ua.dokat.colorcontrol.services.Team;

public class IronCube implements Cube{

    private final BoundingBox box = BoundingBox.of(new Vector(-177, 41, 108), new Vector(-174, 40, 111));

    private int red = 0;
    private int blue = 0;

    private Team teamDug = null;

    @Override
    public void setTeamDigging(Team team) {
        teamDug = team;
    }

    @Override
    public void addDugRed() {
        red++;
    }

    @Override
    public void removeDugRed() {
        if (red != 0) red--;
    }

    @Override
    public void addDugBlue() {
        blue++;
    }

    @Override
    public void removeDugBlue() {
        if (blue != 0) blue--;
    }

    @Override
    public boolean isDub(Team team) {
        if (team instanceof RedTeam) return red == 9;
        if (team instanceof BlueTeam) return blue == 9;
        return false;
    }

    @Override
    public Team getTeamByDug() {
        return teamDug;
    }

    @Override
    public BoundingBox getBox() {
        return box;
    }
}
