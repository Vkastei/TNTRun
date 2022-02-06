package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class PhaseMOTDListener implements Listener {
    private static BukkitTask taskID;
    private static TNTRun main = TNTRun.getInstance();
    private static int i = 10;
    @EventHandler
    public void countDown(final ServerListPingEvent event){
        if(LobbyPhaseListener.TNTBool){
            event.setMotd("Spiel läuft");
        }else{
            event.setMotd("Lobby");
        }
    }
}
