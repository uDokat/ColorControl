package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.util.BoundingBox;
import ua.dokat.colorcontrol.commands.test.TestYmlParser;
import ua.dokat.colorcontrol.newrealms.entity.Cube;
import ua.dokat.colorcontrol.newrealms.entity.Realm;
import ua.dokat.colorcontrol.newrealms.entity.Team;
import ua.dokat.colorcontrol.newrealms.repositories.CubeRepository;

import java.util.Map;

public class CubeService {

    private static CubeService instance;
    private final CubeRepository repository = new CubeRepository();

    public Map<BoundingBox, Cube> fill(){
        return TestYmlParser.getInstance().fill(repository.getBoxes());
    }

    public Cube getCube(BoundingBox box, Realm realm){
        return repository.findCubeByBox(repository.findBox(box), realm);
    }

    public boolean isDug(Cube cube, Realm realm, Team team){
        Team foundTeam = getTeamWhichDugCube(cube, realm);
        if (foundTeam == null) return false;
        return foundTeam.equals(team);
    }

    public Team getTeamWhichDugCube(Cube cube, Realm realm){
        if (cube.getRed() == 9) return realm.getRed();
        if (cube.getBlue() == 9) return realm.getBlue();

        return null;
    }

    public BoundingBox testGetCube(BoundingBox box){
        return repository.findBox(box);
    }

    public int s(){
        return repository.getBoxes().size();
    }

    public static CubeService getInstance(){
        if (instance == null) return instance = new CubeService();
        return instance;
    }
}
