package eu.mineoase.tntrun.items;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class C4Item implements Listener {
    public static int c4Price = 1000;
    public static boolean c4On = false;
    public static Location c4Location;
    public static ItemStack c4(){
        ItemStack c4 = new ItemStack(Material.REDSTONE_TORCH);
        ItemMeta c4Meta = c4.getItemMeta();
        c4Meta.setDisplayName("C4");
        c4.setItemMeta(c4Meta);

        return c4;
    }
    public static ItemStack lever(){
        ItemStack lever = new ItemStack(Material.LEVER);
        ItemMeta leverMeta = lever.getItemMeta();
        leverMeta.setDisplayName("Fernzünder");
        lever.setItemMeta(leverMeta);

        return lever;
    }
    @EventHandler
    public void c4BlockEvent(BlockPlaceEvent e1){
        if(e1.getBlock().getType() == Material.REDSTONE_TORCH){
            Location loc1 = e1.getBlock().getLocation();
            c4Location = loc1;
        }
    }
    @EventHandler
    public void c4PlaceEvent(PlayerInteractEvent e){
        Player p = (Player) e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(p.getInventory().getItemInMainHand().getType() == Material.REDSTONE_TORCH || p.getInventory().getItemInMainHand() == c4()){
                c4On = true;
            }
        }
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
            if(p.getInventory().getItemInMainHand().getType() == Material.LEVER || p.getInventory().getItemInMainHand() == lever()){
                if(c4On){
                    c4Location.getBlock();
                    for(int x = -1; x < 1; x++){
                        for(int z = -1; z < 1; z++){
                            e.getClickedBlock().getLocation().subtract(x, 0, z).getBlock().setType(Material.AIR);
                            e.getClickedBlock().getLocation().subtract(x, 1, z).getBlock().setType(Material.AIR);
                            e.getClickedBlock().getLocation().subtract(x, 2, z).getBlock().setType(Material.AIR);
                            e.getPlayer().getWorld().createExplosion(e.getClickedBlock().getLocation(), 0, false);
                        }
                    }
                }
            }else{
                    p.sendMessage(ChatColor.RED + "Du hast noch kein C4 plaziert");
            }
        }


    }

}