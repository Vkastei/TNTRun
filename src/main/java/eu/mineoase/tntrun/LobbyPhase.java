package eu.mineoase.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;


public class LobbyPhase implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    public static boolean TNTBool = false;
    public ArrayList<Player> PlayerList = new ArrayList<>(Bukkit.getOnlinePlayers());
    public static boolean start = true;
    public static int playerCount = 0;
    public int maxPlayer = 2;
    public static int i = 10;
    @EventHandler
    public void PlayerJoin(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        playerCount++;
        if(TNTRun.lobbyStart && start){

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


                            Location startMode = new Location(Bukkit.getWorld("game"), 1.5, 85, 0.6);

                            for(Player p2 : PlayerList){
                                if(p2.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.EMERALD_BLOCK){
                                    p2.teleport(startMode);
                                    TNTRun.lobbyStart = false;
                                    TNTBool = true;
                                }

                            }



                        }
                    }
                }.runTaskTimer(main, 0, 20);
            }
        }

    }
}
