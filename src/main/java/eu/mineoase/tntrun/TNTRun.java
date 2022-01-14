package eu.mineoase.tntrun;

import eu.mineoase.tntrun.items.JumpBoostPot;
import eu.mineoase.tntrun.items.MineField;
import eu.mineoase.tntrun.items.SpleefShovel;
import eu.mineoase.tntrun.items.NoHit;
import eu.mineoase.tntrun.listener.TNTRunListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TNTRun extends JavaPlugin {

    public static boolean TNTBool = false;
    public static TNTRun instance;
    public static int i = 10;
    public static int delay;
    @Override
    public void onEnable() {
        delay = getConfig().getInt("delay");
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new TNTRunListener(), this);
        Bukkit.getPluginManager().registerEvents(new JumpBoostPot(), this);
        Bukkit.getPluginManager().registerEvents(new MineField(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefShovel(), this);
        Bukkit.getPluginManager().registerEvents(new NoHit(), this);
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
        if(command.getName().equalsIgnoreCase("tntrun")){

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(i >= 0){
                        p.sendMessage("Runde beginnt in: " + ChatColor.GREEN + i);
                        i--;
                    }else{
                        this.cancel();
                        p.sendMessage("Die Runde beginnt jetzt...");

                        i = 0;
                        TNTBool = true;
                    }
                }
            }.runTaskTimer(this, 0, 20);
        }
        if(command.getName().equalsIgnoreCase("jumpboost")){
            p.getInventory().addItem(JumpBoostPot.potion());
        }if(command.getName().equalsIgnoreCase("minefield")){
            p.getInventory().addItem(MineField.mine());
        }if(command.getName().equalsIgnoreCase("spleef")){
            p.getInventory().addItem(SpleefShovel.shovel());
        }if(command.getName().equalsIgnoreCase("nohit")){
            p.getInventory().addItem(NoHit.noHit());
        }
        return true;

    }
    public static TNTRun getInstance() {
        return instance;
    }
}