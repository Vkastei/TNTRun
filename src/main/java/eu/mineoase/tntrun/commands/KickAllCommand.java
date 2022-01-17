package eu.mineoase.tntrun.commands;

import eu.mineoase.tntrun.util.PlayerConnector;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        for(Player current : Bukkit.getOnlinePlayers()) {
            PlayerConnector.connect(current, "hub");
            current.sendMessage("weg mit dir!");
        }

        return false;
    }
}
