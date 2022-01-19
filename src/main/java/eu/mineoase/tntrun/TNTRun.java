
package eu.mineoase.tntrun;

import eu.mineoase.tntrun.commands.KickAllCommand;
import eu.mineoase.tntrun.items.*;
import eu.mineoase.tntrun.listener.LobbyPhaseListener;
import eu.mineoase.tntrun.listener.PlayerQuitListener;
import eu.mineoase.tntrun.listener.SpectatorModeListener;
import eu.mineoase.tntrun.listener.TNTRunListener;
import eu.mineoase.tntrun.shop.ShopGui;
import eu.mineoase.tntrun.shop.SpawnShop;
import eu.mineoase.tntrun.util.WorldReset;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class TNTRun extends JavaPlugin {

    private ArrayList<Player> PlayerList;


    public static TNTRun instance;
    public static SpawnShop spawnShop;
    public static boolean lobbyStart = false;
    public static int i = 10;
    private ArrayList<Player> players = new ArrayList<>();
    public static int delay;
    public static int maxPlayers;
    public static int minPlayers;
    public static String gameWorld;
    private ArrayList<Location> playerLocation;
    private ArrayList<Block> placed ;
    private HashMap<Block, Material> broken;
    public static int joinX;
    public static int joinY;
    public static int joinZ;
    @Override
    public void onEnable() {
        World world = Bukkit.getWorld("world");
        world.setAutoSave(true);


        placed = new ArrayList<>();
        broken = new HashMap<>();
        playerLocation = new ArrayList<>();

        minPlayers = getConfig().getInt("minPlayers");
        maxPlayers = getConfig().getInt("maxPlayers");
        delay = getConfig().getInt("delay");
        gameWorld = getConfig().getString("gameWorld");
        joinX = getConfig().getInt("JoinLocation.joinX");
        joinY = getConfig().getInt("JoinLocation.joinY");
        joinZ = getConfig().getInt("JoinLocation.joinZ");

        instance = this;



        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new TNTRunListener(), this);
        Bukkit.getPluginManager().registerEvents(new JumpBoostPot(), this);
        Bukkit.getPluginManager().registerEvents(new MineField(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefShovel(), this);
        Bukkit.getPluginManager().registerEvents(new NoHit(), this);
        Bukkit.getPluginManager().registerEvents(new C4Item(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyPhaseListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpectatorModeListener(), this);
        Bukkit.getPluginManager().registerEvents(new WorldReset(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ShopGui(), this);
        Bukkit.getPluginManager().registerEvents(new SpawnShop(), this);
        LobbyPhaseListener.playerCount = 0;

        getCommand("kickall").setExecutor(new KickAllCommand());

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    }

    @Override
    public void onDisable() {
        for(Entity v : Bukkit.getWorld(gameWorld).getEntities()){
            if(v.getType() == EntityType.VILLAGER){
                v.remove();
            }
        }

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
            p.sendMessage(String.valueOf(playerLocation));
            p.sendMessage(String.valueOf(players));
        }if(command.getName().equalsIgnoreCase("countdown")){
            LobbyPhaseListener.seconds = 1;
        }
        return true;

    }
    public static TNTRun getInstance() {
        return instance;
    }

    public ArrayList<Player> getPlayerList() {
        return PlayerList;
    }

    public HashMap<Block, Material> getBroken() {
        return broken;
    }
    public ArrayList<Block> getPlaced() {
        return placed;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Location> getPlayerLocation() {
        return playerLocation;
    }
}
