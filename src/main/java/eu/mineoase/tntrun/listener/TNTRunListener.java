package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.LobbyPhase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import eu.mineoase.tntrun.TNTRun;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;



public class TNTRunListener implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    public static boolean startRound = false;
    public static boolean isRunning = false;

    public static int i = TNTRun.i;
    @EventHandler
    public void PlayerJoin(PlayerTeleportEvent e){
        if(LobbyPhase.TNTBool){
            new BukkitRunnable()
            {
                @Override
                public void run() {
                    startRound = true;


                }

            }.runTaskLater(main, 60);
        }
    }
    @EventHandler
    public void moveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();

        Location blockBelow = p.getLocation().subtract(0, 1, 0);

        if(LobbyPhase.TNTBool && startRound){
            isRunning = true;
            new BukkitRunnable(){
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