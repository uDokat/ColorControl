package ua.dokat.colorcontrol.newrealms.repositories;

import org.bukkit.util.BoundingBox;
import ua.dokat.colorcontrol.newrealms.entity.Cube;
import ua.dokat.colorcontrol.newrealms.entity.Realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubeRepository {

//    private final Map<BoundingBox, Cube> cubes = new HashMap<>();
    private final List<BoundingBox> boxes = new ArrayList<>();

    public BoundingBox findBox(BoundingBox box){
        for (BoundingBox b : boxes){
            if (b.overlaps(box)) return b;
        }

        return null;
    }

    public Cube findCubeByBox(BoundingBox box, Realm realm){
        return realm.getCubes().get(box);
    }

//    public Map<BoundingBox, Cube> getCubes() {
//        return cubes;
//    }

    public List<BoundingBox> getBoxes() {
        return boxes;
    }
}
