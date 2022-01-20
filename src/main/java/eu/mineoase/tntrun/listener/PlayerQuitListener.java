package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.TNTRun;
import eu.mineoase.tntrun.shop.SpawnShop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class PlayerQuitListener implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    private static ArrayList<Player> players;
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        players = main.getPlayers();
        players.remove(p);
        /*
        if(TNTRunListener.startRound != true){
            LobbyPhaseListener.playerCount--;
        }

         */
    }
}
