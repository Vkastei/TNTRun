package eu.mineoase.tntrun.shop;

import eu.mineoase.tntrun.items.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopGui implements Listener {
    private Inventory inv;

    public ShopGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 9, "Example");

        // Put the items into the inventory
        initializeItems();
    }

    public void initializeItems() {

        inv.addItem(C4Item.c4());
        inv.addItem(JumpBoostPot.potion());
        inv.addItem(MineField.mine());
        inv.addItem(NoHit.noHit());
        inv.addItem(PoisonThrowPot.poison());
        inv.addItem(SpleefShovel.shovel());
    }



    public void openInventory() {
        for(Player p : Bukkit.getOnlinePlayers()){
            p.openInventory(inv);
        }

    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();


        p.sendMessage("You clicked at slot " + e.getRawSlot());
    }


    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
}
