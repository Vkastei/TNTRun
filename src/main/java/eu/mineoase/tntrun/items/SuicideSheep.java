package eu.mineoase.tntrun.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SuicideSheep implements Listener {
    public static int sheepPrice = 1000;

    public static ItemStack sheep() {
        ItemStack sheep = new ItemStack(Material.SHEEP_SPAWN_EGG);
        ItemMeta sheepMeta = sheep.getItemMeta();
        sheepMeta.setDisplayName("Suicide Sheep");
        sheep.setItemMeta(sheepMeta);

        return sheep;
    }

    @EventHandler
    public void sheepClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.SHEEP_SPAWN_EGG || e.getPlayer().getInventory().getItemInMainHand() == sheep()) {
                Location loc1 = e.getClickedBlock().getLocation();
                Sheep sheep = (Sheep) e.getPlayer().getWorld().spawnEntity(loc1, EntityType.SHEEP);
                e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
            }
        }
    }

    @EventHandler
    public void onSheepSpawn(EntitySpawnEvent e1) {
        if (e1.getEntity().getType() == EntityType.SHEEP) {
            Entity closestEntity = null;
            double closestDistance = 0.0;

            for (Entity entity : e1.getEntity().getNearbyEntities(20, 20, 20)) {
                double distance = entity.getLocation().distanceSquared(entity.getLocation());
                if (closestEntity == null || distance < closestDistance || e1.getEntity() instanceof Player) {
                    closestDistance = distance;
                    closestEntity = entity;
                    e1.getEntity().teleport(closestEntity);
                }
            }
        }

    }
}