package eu.mineoase.tntrun.items;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class C4Item implements Listener {
    public static boolean c4Bool = false;

    public static int c4Count = 0;

    public static int amount = 1;
    public static Location c4loc;
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
    public void onC4Place(BlockPlaceEvent e){
        if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("C4")){
            if(c4Count < 1){
                c4Count = c4Count + 1;
                c4Bool = true;
                c4loc = e.getBlock().getLocation();
            }else{
                e.getPlayer().sendMessage("Du hast bereits C4 platziert ");
                e.setCancelled(true);
            }

        }
        if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Fernzünder")){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void leverClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){

            if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Fernzünder")){
                if(c4Bool){
                    c4loc.getBlock().setType(Material.AIR);
                    c4Count = 0;
                    c4Bool = false;
                    p.getWorld().createExplosion(c4loc,0, false);
                    c4Explode();

                }else{
                    p.sendMessage("Du hast noch kein C4 platziert");
                }
            }
        }
    }

    @EventHandler
    public void PlayerTouchC4(PlayerMoveEvent e){
        Player p = (Player) e.getPlayer();
        if(c4loc != null && c4Bool){
            if(p.getLocation().distanceSquared(c4loc) < 1){
                p.getWorld().createExplosion(c4loc, 0, false);
                c4Explode();
                c4loc.getBlock().setType(Material.AIR);
                c4Count = 0;
                c4Bool = false;
            }

        }
    }

    @EventHandler
    public void torchDrop(BlockBreakEvent e){
        e.getBlock().getDrops().clear();
    }
    public void c4Explode(){
        c4loc.getBlock().getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(-1, 1, 0).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(1, 1, 0).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(0, 1, -1).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(0, 1, 1).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(0, 2, 0).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(-1, 2, 0).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(1, 2, 0).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(0, 2, -1).getBlock().setType(Material.AIR);
        c4loc.getBlock().getLocation().subtract(0, 2, 1).getBlock().setType(Material.AIR);

    }

}