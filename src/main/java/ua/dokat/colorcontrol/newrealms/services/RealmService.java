package ua.dokat.colorcontrol.newrealms.services;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ua.dokat.colorcontrol.ColorControl;
import ua.dokat.colorcontrol.Config;
import ua.dokat.colorcontrol.InventoryUtil;
import ua.dokat.colorcontrol.Utils;
import ua.dokat.colorcontrol.gui.items.BackToHub;
import ua.dokat.colorcontrol.gui.items.FindGame;
import ua.dokat.colorcontrol.gui.items.TeamSelection;
import ua.dokat.colorcontrol.builders.RealmBuilder;
import ua.dokat.colorcontrol.newrealms.entity.GameProfile;
import ua.dokat.colorcontrol.newrealms.repositories.RealmRepository;
import ua.dokat.colorcontrol.newrealms.entity.Realm;

import java.util.List;
import java.util.UUID;

public class RealmService implements Utils {

    private static RealmService instance;
    private final RealmRepository repository = new RealmRepository();

    public void create(String name, int id, int count){
        for (int x = 0; x < count; x++){
            repository.add(RealmBuilder.build(name + x, id));
            id++;
        }
    }

    public void create(Realm realm){
        repository.add(realm);
    }

    public boolean close(Realm realm){
        if (realm.isOnline()){
            if (realm.getPlayers().size() != 0) TeleportService.getInstance().teleportEveryoneToLobby(players(realm.getPlayers()));
            Bukkit.unloadWorld(realm.getWorld(), false);
            realm.setOnline(false);
            GameService.getInstance().removeGame(realm.getPlayers());
            repository.remove(realm);
            return true;
        }

        return false;
    }

    public void restart(Realm realm){
        close(realm);
        create(RealmBuilder.build(realm.getName(), realm.getId()));
    }

    public void addPlayer(Realm realm, UUID uuid){
        if (!realm.getPlayers().contains(uuid)){
            realm.getPlayers().add(uuid);
            realm.getProfiles().put(uuid, new GameProfile(uuid));
        }
    }

    public void removePlayer(Realm realm, UUID uuid){
        realm.getPlayers().remove(uuid);
    }

    public Realm joinFreeClassicRealm(Player player){
        Realm realm = repository.findFreeClassicRealm();
        return getRealm(player, realm);
    }

    public Realm joinFreeDefRealm(Player player){
        Realm realm = repository.findFreeDefRealm();
        return getRealm(player, realm);
    }

    private Realm getRealm(Player player, Realm realm) {
        if (realm == null) return null;

        GameService.getInstance().addGame(player.getUniqueId(), realm);
        addPlayer(realm, player.getUniqueId());
        InventoryUtil.giveItems(player, new TeamSelection(), new BackToHub());

        MessageService.getInstance().joinMessage(players(realm.getPlayers()), player.getName(), realm.getPlayers().size());
        player.teleport(realm.getWorld().getSpawnLocation());

        return realm;
    }

    public boolean joinById(Player player, int id){
        Realm realm = getById(id);
        if (realm == null) return false;

        GameService.getInstance().addGame(player.getUniqueId(), realm);
        addPlayer(realm, player.getUniqueId());
        InventoryUtil.giveItems(player, new TeamSelection(), new BackToHub());

        return player.teleport(realm.getWorld().getSpawnLocation());
    }

    public int classicRealmCount(){
        return repository.classicRealms().size();
    }

    public int defRealmCount(){
        return repository.defRealms().size();
    }

    public int onlinePlayers(int startId, int endId){
        return repository.onlinePlayers(repository.getByIds(startId, endId));
    }

    public Realm getById(int id){
        return repository.findById(id);
    }

    public Realm getByName(String name){
        return repository.findByName(name);
    }

    public List<Realm> getAll(){
        return repository.getAll();
    }

    public static RealmService getInstance(){
        if (instance == null){
            return instance = new RealmService();
        }else {
            return instance;
        }
    }
}