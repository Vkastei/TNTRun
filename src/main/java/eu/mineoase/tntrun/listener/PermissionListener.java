package eu.mineoase.tntrun.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PermissionListener implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        e.setCancelled(true);
    }@EventHandler
    public void breakBlock(BlockPlaceEvent e) {
        if(TNTRunListener.startRound){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void playerInteract(PlayerInteractEvent e){
        if(TNTRunListener.startRound){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void itemEvent(PlayerItemConsumeEvent e){
        if(TNTRunListener.startRound){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void playerInteract(PlayerDropItemEvent e){
        e.setCancelled(true);
    }
}
