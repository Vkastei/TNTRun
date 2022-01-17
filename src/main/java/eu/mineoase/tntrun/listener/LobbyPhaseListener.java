package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LobbyPhaseListener implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    public static boolean TNTBool = false;
    private static ArrayList<Player> players;
    public static boolean start = true;
    public static int playerCount = 0;
    public int maxPlayer = 1;
    public static int i = 10;
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        p.setGameMode(GameMode.ADVENTURE);
        players = main.getPlayers();
        players.add(p);
        Location lobby = new Location(Bukkit.getWorld("world"), 226, 4, 48.5);
        p.teleport(lobby);
        SpectatorModeListener.start = true;
        playerCount++;


        Bukkit.broadcastMessage(ChatColor.GREEN + e.getPlayer().getName() + ChatColor.WHITE + " ist der Lobby beigetreten" + ChatColor.GREEN + "(" + playerCount + "/" + maxPlayer + ")");


        if(playerCount == maxPlayer){
            start = false;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(i >= 1){
                        Bukkit.broadcastMessage("Runde beginnt in: " + ChatColor.GREEN + i);
                        i--;
                    }else{
                        this.cancel();
                        Bukkit.broadcastMessage("Die Runde beginnt jetzt...");


                        Location startMode = new Location(Bukkit.getWorld("world"), 1.5, 85, 0.6);

                        for(Player p2 : players){

                            TNTBool = true;
                            TNTRun.lobbyStart = false;
                            playerCount = 0;
                            i = 10;
                            p2.teleport(startMode);

                        }

                    }
                }
            }.runTaskTimer(main, 0, 20);
        }
        if(playerCount > maxPlayer){
            playerCount = 0;
        }


    }
}
