package eu.mineoase.tntrun.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PermissionListener implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        if (e.getPlayer().isOp() == false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
}
