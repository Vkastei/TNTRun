package eu.mineoase.tntrun.items;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class SpleefShovel implements Listener {
    public static int  potionPrice = 400;
    private static TNTRun main = TNTRun.getInstance();
    public static ItemStack shovel(){
        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta shovelMeta = shovel.getItemMeta();
        shovelMeta.setDisplayName("Spleef Shovel");
        shovel.setItemMeta(shovelMeta);

        return shovel;
    }
    @EventHandler
    public void SpleefClick(PlayerInteractEvent e){
        if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getInventory().getItemInMainHand().equals(shovel()) || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_SHOVEL){
            if(e.getClickedBlock().getType() == Material.GRAVEL || e.getClickedBlock().getType() == Material.SAND){
                e.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
                e.getClickedBlock().getLocation().subtract(-1, 1, 0).getBlock().setType(Material.AIR);
                e.getClickedBlock().getLocation().subtract(1, 1, 0).getBlock().setType(Material.AIR);
                e.getClickedBlock().getLocation().subtract(0, 1, -1).getBlock().setType(Material.AIR);
                e.getClickedBlock().getLocation().subtract(0, 1, 1).getBlock().setType(Material.AIR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getClickedBlock().getLocation().subtract(0, 0, 0).getBlock().setType(Material.AIR);
                        e.getClickedBlock().getLocation().subtract(-1, 0, 0).getBlock().setType(Material.AIR);
                        e.getClickedBlock().getLocation().subtract(1, 0, 0).getBlock().setType(Material.AIR);
                        e.getClickedBlock().getLocation().subtract(0, 0, -1).getBlock().setType(Material.AIR);
                        e.getClickedBlock().getLocation().subtract(0, 0, 1).getBlock().setType(Material.AIR);
                    }
                }.runTaskLater(main, 3);
            }

        }
    }
}