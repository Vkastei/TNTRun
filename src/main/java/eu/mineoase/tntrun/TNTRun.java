package eu.mineoase.tntrun;

import eu.mineoase.tntrun.items.*;
import eu.mineoase.tntrun.listener.SpectatorMode;
import eu.mineoase.tntrun.listener.TNTRunListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class TNTRun extends JavaPlugin {


    public static TNTRun instance;
    public static boolean lobbyStart = false;
    public static int i = 10;
    private ArrayList<Player> players;
    public static int delay;
    @Override
    public void onEnable() {
        players = new ArrayList<>();
        delay = getConfig().getInt("delay");
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new TNTRunListener(), this);
        Bukkit.getPluginManager().registerEvents(new JumpBoostPot(), this);
        Bukkit.getPluginManager().registerEvents(new MineField(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefShovel(), this);
        Bukkit.getPluginManager().registerEvents(new NoHit(), this);
        Bukkit.getPluginManager().registerEvents(new C4Item(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyPhase(), this);
        Bukkit.getPluginManager().registerEvents(new SpectatorMode(), this);
        LobbyPhase.playerCount = 0;

    }

    @Override
    public void onDisable() {


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            //the sender is not a Player
            return false;
        }
        Player p = (Player)sender;

        if(command.getName().equalsIgnoreCase("start")){
            lobbyStart = true;

            Location lobby = new Location(Bukkit.getWorld("game"), 226, 4, 48.5);
            p.teleport(lobby);
        }
        if(command.getName().equalsIgnoreCase("jumpboost")){
            p.getInventory().addItem(JumpBoostPot.potion());
        }if(command.getName().equalsIgnoreCase("minefield")){
            p.getInventory().addItem(MineField.mine());
        }if(command.getName().equalsIgnoreCase("spleef")){
            p.getInventory().addItem(SpleefShovel.shovel());
        }if(command.getName().equalsIgnoreCase("nohit")){
            p.getInventory().addItem(NoHit.noHit());
        }if(command.getName().equalsIgnoreCase("c4")){
            p.getInventory().addItem(C4Item.c4());
            p.getInventory().addItem(C4Item.lever());
        }if(command.getName().equalsIgnoreCase("player")){
            p.sendMessage(String.valueOf(players));
        }
        return true;

    }
    public static TNTRun getInstance() {
        return instance;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}