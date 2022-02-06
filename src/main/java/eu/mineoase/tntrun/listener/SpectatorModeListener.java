package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.TNTRun;

import eu.mineoase.tntrun.util.PlayerConnector;
import eu.mineoase.tntrun.util.WorldReset;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SpectatorModeListener implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    public static boolean start = true;
    public int seconds = 11;

    private ArrayList<Block> placed;
    private HashMap<Block, Material> broken;
    private ArrayList<Player> players;

    public void onPlayerFall(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerDeath(EntityDamageEvent e){
        placed = main.getPlaced();
        broken = main.getBroken();
        players = main.getPlayers();
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            e.setCancelled(true);
        }

        if(TNTRunListener.startRound){


            if(e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                e.setCancelled(true);
                e.getEntity().teleport(new Location(Bukkit.getWorld("world"), 1.5, 85, 0.6));
                Player p = (Player) e.getEntity(); //player

                p.setGameMode(GameMode.SPECTATOR);
                players.remove(p); //remove

            }



            if(players.size() == 1){
                Player winner = players.get(0);


                int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable(){

                                    @Override
                                    public void run() {
                                        seconds--;
                                        switch (seconds) {
                                            case 10:

                                                for(Player current : Bukkit.getOnlinePlayers()) {
                                                    current.sendTitle(ChatColor.RED + winner.getDisplayName(), ChatColor.WHITE + " hat das Spiel gewonnen!",
                                                        10, 70, 20);
                                                }
                                                start = false;
                                                LobbyPhaseListener.start = true;
                                                LobbyPhaseListener.playerCount = 0;
                                                break;
                                            case 5: case 4: case 3: case 2:
                                                Bukkit.broadcastMessage(ChatColor.WHITE + "Der Server stoppt in " + ChatColor.GREEN + seconds + ChatColor.WHITE + " Sekunden");
                                                break;
                                            case 1:
                                                Bukkit.broadcastMessage(ChatColor.WHITE + "Der Server stoppt in " + ChatColor.GREEN + seconds + ChatColor.WHITE + " Sekunde");
                                                break;
                                            case 0:

                                                for(Player current : Bukkit.getOnlinePlayers()) {
                                                    PlayerConnector.connect(current, "hub");
                                                }
                                                for (Block b : placed) {
                                                    b.setType(Material.AIR);
                                                }
                                                for (Block b : broken.keySet()) {
                                                    b.setType(broken.get(b));
                                                }
                                                placed = new ArrayList<Block>();
                                                broken = new HashMap<Block, Material>();
                                                Bukkit.broadcastMessage("reset");
                                                Bukkit.getWorld("world").save();

                                                Bukkit.reload();

                                            default:
                                                break;
                                        }

                                    }
                                }, 0, 20);

                
            }
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        e.setDeathMessage("");
    }
}
