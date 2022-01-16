package eu.mineoase.tntrun.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Collection;

public class SpectatorMode implements Listener {
    Collection<? extends Player> player = Bukkit.getOnlinePlayers();
    @EventHandler
    public void PlayerDeath(EntityDamageEvent e){


        if(TNTRunListener.startRound){


            if(e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                e.getEntity().teleport(new Location(Bukkit.getWorld("game"), 1.5, 85, 0.6));
                for(Player p1 : player){

                    p1.setGameMode(GameMode.SPECTATOR);
                    //player.remove(p1);
                }

                e.setCancelled(true);


            }

            if(player.size() == 1){
                Bukkit.broadcastMessage(player.toString() + " hat gewonnen!");
            }
        }

    }
}
