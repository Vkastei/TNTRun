package eu.mineoase.tntrun.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpBoostPot implements Listener {
    public static int  potionPrice = 300;
    public static ItemStack potion(){
        ItemStack potion = new ItemStack(Material.POTION);
        ItemMeta potionMeta = potion.getItemMeta();
        potionMeta.setDisplayName("Jump Boost Potion");
        potion.setItemMeta(potionMeta);

        return potion;
    }
    @EventHandler
    public void JumpBoostClick(PlayerInteractEvent e){
        Player p = (Player) e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(p.getInventory().getItemInMainHand().equals(potion()) || p.getInventory().getItemInMainHand().equals("Jump Boost Potion")){
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 160, 1));
            }

        }

    }

}