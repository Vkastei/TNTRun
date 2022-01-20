package eu.mineoase.tntrun.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MineField implements Listener {
    public static int  minePrice = 300;
    public static int roundUse = 0;
    public static ItemStack mine(){
        ItemStack mine = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
        ItemMeta mineMeta = mine.getItemMeta();
        mineMeta.setDisplayName("Mine");
        mine.setItemMeta(mineMeta);

        return mine;
    }
    @EventHandler
    public void MineFieldPlace(BlockPlaceEvent e){
        Player p = (Player) e.getPlayer();

        if(e.getBlockPlaced().getType() == (Material.HEAVY_WEIGHTED_PRESSURE_PLATE)) {

            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
            e.getBlockPlaced().getLocation().subtract(1, 0, 0).getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
            e.getBlockPlaced().getLocation().subtract(0, 0, 1).getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
            e.getBlockPlaced().getLocation().subtract(0, 0, -1).getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
            e.getBlockPlaced().getLocation().subtract(-1, 0, 0).getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
        }

    }
    @EventHandler
    public void MineExplosion(PlayerInteractEvent e){
        if(e.getAction() == Action.PHYSICAL){
            for(int x = -3; x < 3; x++){
                for(int z = -3; z < 3; z++){
                    if(e.getClickedBlock().getLocation().subtract(x, 0, z).getBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE){
                        e.getClickedBlock().getLocation().subtract(x, 0, z).getBlock().setType(Material.AIR);
                        e.getClickedBlock().getLocation().subtract(x, 1, z).getBlock().setType(Material.AIR);
                        e.getClickedBlock().getLocation().subtract(x, 2, z).getBlock().setType(Material.AIR);
                        e.getPlayer().getWorld().createExplosion(e.getClickedBlock().getLocation(), 0, false);
                    }
                }
            }
        }
    }
}