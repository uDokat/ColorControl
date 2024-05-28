package ua.dokat.colorcontrol.newrealms.entity;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectCube extends Cube{

    private final PotionEffectType effect;

    public EffectCube(PotionEffectType effect, int time) {
        super(time);
        this.effect = effect;
    }

    @Override
    public void giveEffect(Player player) {
        player.addPotionEffect(new PotionEffect(effect, 100, 1));
        super.giveEffect(player);
    }
}
