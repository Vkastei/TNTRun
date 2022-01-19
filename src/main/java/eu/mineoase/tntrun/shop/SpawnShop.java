package eu.mineoase.tntrun.shop;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpawnShop implements Listener {
    private Villager villager;

    public void spawn(){

        Location shopLocation = new Location(Bukkit.getWorld(TNTRun.gameWorld), TNTRun.joinX + 7, TNTRun.joinY, TNTRun.joinZ + 7);

        villager = shopLocation.getWorld().spawn(shopLocation, Villager.class);
        villager.setAI(false);
        villager.setCustomName("Item Shop");
        villager.setSilent(true);
    }
    public void remove(Villager villager){
        villager.remove();
    }

    @EventHandler
    public void ShopClick(PlayerInteractEntityEvent e){
        if(e.getRightClicked().getName().equals("Item Shop")){

            new ShopGui().openInventory();
            //e.setCancelled(true);
        }
    }

    public Villager getVillager() {
        return villager;
    }
}
