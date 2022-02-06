package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.TNTRun;
import eu.mineoase.tntrun.shop.ShopGui;
import eu.mineoase.tntrun.shop.SpawnShop;
import eu.mineoase.tntrun.util.PlayerLocation;
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
    public int maxPlayer = TNTRun.maxPlayers;
    public int minPlayer = TNTRun.minPlayers;
    public static int joinX = TNTRun.joinX;
    public static int joinY = TNTRun.joinY;
    public static int joinZ = TNTRun.joinZ;
    public static int seconds = 90;
    public static int i = 10;
    public static int taskID;
    public static boolean startLobby = false;
    public static boolean motdBool = false;
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.setGameMode(GameMode.ADVENTURE);
        new ShopGui();
        new SpawnShop().spawn();
        players = main.getPlayers();
        players.add(p);
        Location lobby = new Location(Bukkit.getWorld(TNTRun.gameWorld), joinX, joinY, joinZ);
        p.teleport(lobby);
        SpectatorModeListener.start = true;
        playerCount++;

        if(!TNTBool){
            Bukkit.broadcastMessage(ChatColor.GREEN + e.getPlayer().getName() + ChatColor.WHITE + " ist der Lobby beigetreten" + ChatColor.GREEN + "(" + playerCount + "/" + maxPlayer + ")");

        }
        if(TNTBool){
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(Bukkit.getWorld("world"), 1.5, 85, 0.6));
        }
        if(startLobby && TNTBool != true){
            startLobby = false;
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
                @Override
                public void run() {
                    if(playerCount >= minPlayer){
                        seconds = seconds - 1;
                        if(seconds >= 0){
                            for(Player p : players){
                                p.setLevel(seconds);
                            }
                        }if(seconds == 0 && playerCount >= minPlayer){
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if(i >= 1){
                                        motdBool = true;
                                        Bukkit.broadcastMessage("Runde beginnt in: " + ChatColor.GREEN + i);
                                        i--;
                                    }else{
                                        this.cancel();
                                        Bukkit.broadcastMessage("Die Runde beginnt jetzt...");
                                        start = false;
                                        startRound();
                                        Bukkit.getScheduler().cancelTask(taskID);
                                    }
                                }
                            }.runTaskTimer(main, 0, 20);
                        }if(playerCount == maxPlayer){
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

                                        startRound();
                                        Bukkit.getScheduler().cancelTask(taskID);
                                    }
                                }
                            }.runTaskTimer(main, 0, 20);
                        }
                        if(playerCount > maxPlayer){
                            playerCount = 0;
                        }
                    }

                }
            }, 0, 20);

        }



    }
    public void startRound(){
        players = main.getPlayers();
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
