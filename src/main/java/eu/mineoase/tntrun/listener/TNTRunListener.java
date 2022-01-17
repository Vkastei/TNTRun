package eu.mineoase.tntrun.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import eu.mineoase.tntrun.TNTRun;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.NumberConversions;

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
    public static int i = TNTRun.i;
    @EventHandler
    public void PlayerJoin(PlayerTeleportEvent e){
        if(LobbyPhaseListener.TNTBool){
            new BukkitRunnable()
            {
                @Override
                public void run() {
                    startRound = true;

                }

            }.runTaskLater(main, 60);
        }
    }
    @EventHandler
    public void PlayerMove(PlayerMoveEvent e){


        if(startRound){


                    broken = main.getBroken();
                    players = main.getPlayers();
                    if(startRound){
                        isRunning = true;

                        Location plloc = e.getPlayer().getLocation();
                        Location plufloc = plloc.clone().add(0, -1, 0);


                        int y = plufloc.getBlockY() + 1;
                        Block b1 = getBlockUnderPlayer(y - 1, plufloc);
                        Block tnt = getBlockUnderPlayer(y - 2 , plufloc);



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
    public void destroyBlock(Block b1, Block tnt){
        new BukkitRunnable() {
            @Override
            public void run() {

                b1.setType(Material.AIR);
                tnt.setType(Material.AIR);
            }
        }.runTaskLater(main, 6);
    }

    private static double PLAYER_BOUNDINGBOX_ADD = 0.5;

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