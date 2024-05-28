package ua.dokat.colorcontrol.cubes;

import org.bukkit.util.BoundingBox;
import ua.dokat.colorcontrol.services.Team;

public interface Cube {

    void setTeamDigging(Team team);

    void addDugRed();

    void removeDugRed();

    void addDugBlue();

    void removeDugBlue();

    boolean isDub(Team team);

    Team getTeamByDug();

    BoundingBox getBox();
}
