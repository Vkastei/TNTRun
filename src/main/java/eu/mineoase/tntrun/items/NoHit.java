package eu.mineoase.tntrun.items;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NoHit implements Listener {
    public static int  noHit = 500;
    private static TNTRun main = TNTRun.getInstance();
    public static boolean noHitBool = false;
    public static ItemStack noHit(){
        ItemStack noHit = new ItemStack(Material.BARRIER);
        ItemMeta noHitMeta = noHit.getItemMeta();
        noHitMeta.setDisplayName("no Hit");
        noHit.setItemMeta(noHitMeta);

        return noHit;
    }
    @EventHandler
    public void noHitRightClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getInventory().getItemInMainHand().equals("no Hit") || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.BARRIER){
                noHitBool = true;
            }
        }
    }
    @EventHandler
    public void PlayerDamage(EntityDamageEvent e1){
        Player p = (Player) e1.getEntity();
        if(noHitBool){
            e1.setCancelled(true);
        }else{
            e1.setCancelled(false);
        }
    }
    @EventHandler
    public void PlayerKnockback(PlayerVelocityEvent e2){
        if(noHitBool){
            e2.setCancelled(true);
        }else{
            e2.setCancelled(false);
        }
    }
}