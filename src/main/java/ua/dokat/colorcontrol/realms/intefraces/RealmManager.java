package ua.dokat.colorcontrol.realms.intefraces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.realms.impl.RealmImpl;

import java.util.ArrayList;

public abstract class RealmManager {

    private final ArrayList<Realm> realms = new ArrayList<>();

    public ArrayList<Realm> createRealms(String name, int count) {
        for (int x = 0; x < count; x++){
            Realm realm = new RealmImpl(name + x);
            realm.create();
            realms.add(realm);
        }

        return realms;
    }

    public Realm findFreeRealm() {
        for (Realm realm : realms){
            if (realm.getPlayerCount() < 10) return realm;
        }

        return null;
    }

    public void closeRealm(Realm realm){
        realm.close();
        realms.remove(realm);
    }

    public int getRealmCount() {
        return realms.size();
    }

    public int getPlayersOnlineCount(){
        int count = 0;

        for (Realm r : realms){
            count = count + r.getPlayerCount();
        }

        return count;
    }

    public Realm getRealmByName(String name){
        for (Realm r : realms){
            if (r.getWorldName().equalsIgnoreCase(name)){
                return r;
            }
        }

        return null;
    }

    public Realm getRealmByWorld(World world){
        for (Realm r : realms){
            if (r.getWorld().equals(world)) return r;
        }

        return null;
    }

    public Realm getRealmByPlayer(Player player){
        for (Realm r : realms){
            if (r.getPlayers().contains(player)) return r;
        }

        return null;
    }

    // мб создать утилиту для подобных провроек, дабы не чекать все реалмы
    public boolean isPlayerOnRealm(Player player){
        for (Realm r : realms){
            return r.getPlayers().contains(player);
        }

        return false;
    }

    public void removeFromList(Realm realm){
        realms.remove(realm);
    }
}
