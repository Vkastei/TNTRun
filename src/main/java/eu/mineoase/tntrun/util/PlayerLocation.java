package eu.mineoase.tntrun.util;

import eu.mineoase.tntrun.TNTRun;
import eu.mineoase.tntrun.listener.TNTRunListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PlayerLocation {

    private static TNTRun main = TNTRun.getInstance();
    private ArrayList<Player> players;
    private ArrayList<Location> playerLocation;
    /*
    public void getPlayerLocation(){
        players = main.getPlayers();

        playerLocation = main.getPlayerLocation();

        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                new TNTRunListener().PlayerMove();
                for(Player p : players){
                    playerLocation.add(p.getLocation());

                }
            }
        }, 0, 6);

    }

     */

 
}
