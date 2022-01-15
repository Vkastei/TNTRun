package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.items.JumpBoostPot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import eu.mineoase.tntrun.TNTRun;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static eu.mineoase.tntrun.TNTRun.TNTBool;


public class TNTRunListener implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    public static int i = TNTRun.i;

    @EventHandler
    public void test(PlayerMoveEvent e){
        Player p = e.getPlayer();

        Location blockBelow = p.getLocation().subtract(0, 1, 0);

        if(TNTBool){
            new BukkitRunnable()
            {
                @Override
                public void run() {
                    if(blockBelow.getBlock().getType() == Material.GRAVEL || blockBelow.getBlock().getType() == Material.SAND) {
                        Location tnt = blockBelow.getBlock().getLocation().subtract(0, 1, 0);
                        blockBelow.getBlock().setType(Material.AIR);
                        tnt.getBlock().setType(Material.AIR);

                    }if(blockBelow.getBlock().getType() == Material.TNT) {
                        Location tnt = blockBelow.getBlock().getLocation().subtract(0, 1, 0);
                        tnt.getBlock().setType(Material.AIR);

                    }
                }

            }.runTaskLater(main, TNTRun.delay);

        }



    }

}