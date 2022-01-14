package eu.mineoase.tntrun.items;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonThrowPot {
    public static int posionPrice = 400;
    private static TNTRun main = TNTRun.getInstance();
    public static boolean noHitBool = false;
    public static ItemStack poison(){
        ItemStack poison = new ItemStack(Material.SPLASH_POTION);

        PotionMeta poisonMeta = (PotionMeta) poison.getItemMeta();
        poisonMeta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 10), true);

        poisonMeta.setDisplayName("no Hit");
        poison.setItemMeta(poisonMeta);

        return poison;
    }
}