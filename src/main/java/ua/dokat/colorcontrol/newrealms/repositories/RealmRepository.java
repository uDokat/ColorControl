package ua.dokat.colorcontrol.newrealms.repositories;

import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.newrealms.entity.Realm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RealmRepository {

    private final List<Realm> realms = new ArrayList<>();

    public void add(Realm realm){
        realms.add(realm);
    }

    public boolean remove(Realm realm){
        if (!realms.contains(realm)) return false;
        realms.remove(realm);
        return true;
    }

    public Realm findByName(String name){
        for (Realm realm: realms){
            if (realm.getName().equalsIgnoreCase(name)) return realm;
        }

        return null;
    }

    public Realm findByPlayer(UUID uuid){
        for (Realm realm : realms){
            if (realm.getPlayers().contains(uuid)) return realm;
        }

        return null;
    }

    public Realm findById(int id){
        for (Realm realm : realms){
            if (realm.getId() == id) return realm;
        }

        return null;
    }

    public Realm findFreeClassicRealm(){
        for (Realm r : classicRealms()){
            if (!r.isStarted() && r.getPlayers().size() < (Integer) Config.getC("maxPlayers")) return r;
        }

        return null;
    }

    public Realm findFreeDefRealm(){
        for (Realm r : defRealms()){
            if (!r.isStarted() && r.getPlayers().size() < (Integer) Config.getD("maxPlayers")) return r;
        }

        return null;
    }

    public List<Realm> classicRealms(){
        return getByIds((Integer) Config.getC("startId"), (Integer) Config.getC("endId"));
    }

    public List<Realm> defRealms(){
        return getByIds((Integer) Config.getD("startId"), (Integer) Config.getD("endId"));
    }

    public int onlinePlayers(List<Realm> realms){
        int count = 0;

        for (Realm realm : realms){
            count += realm.getPlayers().size();
        }

        return count;
    }

    public List<Realm> getAll() {
        return realms;
    }

    public List<Realm> getByIds(int startId, int endId){
        List<Realm> list = new ArrayList<>();

        for (Realm realm : realms){
            if (realm.getId() < endId && realm.getId() >= startId) list.add(realm);
        }

        return list;
    }
}
