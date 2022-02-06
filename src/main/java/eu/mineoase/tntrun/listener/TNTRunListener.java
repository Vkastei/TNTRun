package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.util.PlayerConnector;
import eu.mineoase.tntrun.util.PlayerLocation;
import eu.mineoase.tntrun.util.WorldReset;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import eu.mineoase.tntrun.TNTRun;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.NumberConversions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class TNTRunListener implements Listener {
    private static TNTRun main = TNTRun.getInstance();
    public static boolean startRound = false;
    public static boolean isRunning = false;
    private double x;
    private int y;
    private double z;
    private HashMap<Location, Material> changedBlocks;
    private HashMap<Block, Material> broken;
    private ArrayList<Player> players;
    private ArrayList<Location> playerLocation;

    public static int i = TNTRun.i;
    @EventHandler
    public void PlayerJoin(PlayerTeleportEvent e){

        if(LobbyPhaseListener.TNTBool){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.setGameMode(GameMode.SURVIVAL);
            }
            for(Entity v : Bukkit.getWorld("world").getEntities()){
                if(v.getType() == EntityType.VILLAGER){
                    v.remove();
                }
            }
            Bukkit.getServer().getWorld("world").getWorldBorder().setSize(70);


            new BukkitRunnable()
            {
                @Override
                public void run() {

                    PlayerMove();
                    startRound = true;
                }

            }.runTaskLater(main, 60);
        }
    }

    public void PlayerMove(){

        broken = main.getBroken();
        players = main.getPlayers();
        playerLocation = main.getPlayerLocation();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                if(startRound){
                    for(Player p : new ArrayList<>(players)){

                        if(p.getGameMode() != GameMode.SPECTATOR){
                            isRunning = true;

                            Location plloc = p.getLocation();
                            Location plufloc = plloc.clone().add(0, -1, 0);

                            int y = plufloc.getBlockY() + 1;
                            Block b1 = getBlockUnderPlayer(y - 1, plufloc);
                            Block tnt = getBlockUnderPlayer(y - 2, plufloc);


                            if (b1 != null && b1.getType() == Material.GRAVEL) {
                                broken.put(b1, Material.GRAVEL);
                                broken.put(tnt, Material.TNT);
                                destroyBlock(b1, tnt);

                            }
                            if (b1 != null && b1.getType() == Material.SAND) {
                                broken.put(b1, Material.SAND);
                                broken.put(tnt, Material.TNT);
                                destroyBlock(b1, tnt);

                            }
                        }
                    }
                }

            }
        }, 0, 1L);


    }
    public void destroyBlock(Block b1, Block tnt){
        new BukkitRunnable() {
            @Override
            public void run() {

                b1.setType(Material.AIR);
                tnt.setType(Material.AIR);
            }
        }.runTaskLater(main, TNTRun.delay);
    }

    private static double PLAYER_BOUNDINGBOX_ADD = 0.3;

    private Block getBlockUnderPlayer(int y, Location location) {
        PlayerPosition loc = new PlayerPosition(location.getX(), y, location.getZ());
        Block b11 = loc.getBlock(location.getWorld(), +PLAYER_BOUNDINGBOX_ADD, -PLAYER_BOUNDINGBOX_ADD);
        if (b11.getType() != Material.AIR) {
            return b11;
        }
        Block b12 = loc.getBlock(location.getWorld(), -PLAYER_BOUNDINGBOX_ADD, +PLAYER_BOUNDINGBOX_ADD);
        if (b12.getType() != Material.AIR) {
            return b12;
        }
        Block b21 = loc.getBlock(location.getWorld(), +PLAYER_BOUNDINGBOX_ADD, +PLAYER_BOUNDINGBOX_ADD);
        if (b21.getType() != Material.AIR) {
            return b21;
        }
        Block b22 = loc.getBlock(location.getWorld(), -PLAYER_BOUNDINGBOX_ADD, -PLAYER_BOUNDINGBOX_ADD);
        if (b22.getType() != Material.AIR) {
            return b22;
        }
        return null;
    }
    private static class PlayerPosition {

        private double x;
        private int y;
        private double z;

        public PlayerPosition(double x, int y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Block getBlock(World world, double addx, double addz) {
            return world.getBlockAt(NumberConversions.floor(x + addx), y, NumberConversions.floor(z + addz));
        }
    }


}
