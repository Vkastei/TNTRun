package eu.mineoase.tntrun.items;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class C4Item implements Listener {
    public static int c4Price = 1000;

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

    public void c4PlaceEvent(PlayerInteractEvent e1);
}