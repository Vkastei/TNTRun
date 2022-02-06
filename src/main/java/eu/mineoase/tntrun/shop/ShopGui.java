package eu.mineoase.tntrun.shop;

import eu.mineoase.tntrun.TNTRun;
import eu.mineoase.tntrun.items.*;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopGui implements Listener {
    public static Inventory inv;
    private int c4Price = 800;
    private int jumpPrice = 300;
    private int minePrice = 300;
    private int noHitPrice = 500;
    private int poisonPrice = 500;
    private int spleefPrice = 400;
    private Economy eco;
    public ShopGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 54, "Item Shop");

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



    public void openInventory(Player p) {
        p.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        eco = TNTRun.getEconomy();
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().equals(inv)){

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("c4") && eco.getBalance(p.getName()) >= c4Price){
                e.setCancelled(true);
                EconomyResponse r = eco.withdrawPlayer(p, c4Price);
                p.getInventory().addItem(C4Item.c4());
                if(!(p.getInventory().contains(C4Item.lever()))){
                    p.getInventory().addItem(C4Item.lever());
                }


            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Jump Boost Potion") && eco.getBalance(p.getName()) >= c4Price){

                e.setCancelled(true);
                EconomyResponse r = eco.withdrawPlayer(p, jumpPrice);
                p.getInventory().addItem(JumpBoostPot.potion());
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("mine") && eco.getBalance(p.getName()) >= minePrice){
                e.setCancelled(true);
                EconomyResponse r = eco.withdrawPlayer(p, minePrice);
                p.getInventory().addItem(MineField.mine());
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("no Hit") && eco.getBalance(p.getName()) >= noHitPrice){
                e.setCancelled(true);
                EconomyResponse r = eco.withdrawPlayer(p, noHitPrice);
                p.getInventory().addItem(NoHit.noHit());
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Übelkeits Wurftrank") && eco.getBalance(p.getName()) >= poisonPrice){
                e.setCancelled(true);
                EconomyResponse r = eco.withdrawPlayer(p, poisonPrice);
                p.getInventory().addItem(PoisonThrowPot.poison());
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Spleef Shovel") && eco.getBalance(p.getName()) >= spleefPrice){
                e.setCancelled(true);
                EconomyResponse r = eco.withdrawPlayer(p, spleefPrice);
                p.getInventory().addItem(SpleefShovel.shovel());
            }

        }
    }


    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
}
