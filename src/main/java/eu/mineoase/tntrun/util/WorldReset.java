package eu.mineoase.tntrun.util;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.*;
import java.util.*;

public class WorldReset implements Listener {

    private ArrayList<Block> placed;
    private HashMap<Block, Material> broken;
    private static TNTRun main = TNTRun.getInstance();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        placed = main.getPlaced();
        placed.add(e.getBlock());
        Bukkit.broadcastMessage("added Block");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        broken = main.getBroken();
        broken.put(e.getBlock(), e.getBlock().getType());
        Bukkit.broadcastMessage("breaked Block");
    }


    /*
    public void BlockPlaceEvent(BlockPlaceEvent e){
        changedBlocks = main.getChangedBlocks();

        changedBlocks.add(e.getBlock());

    }

     */



}
