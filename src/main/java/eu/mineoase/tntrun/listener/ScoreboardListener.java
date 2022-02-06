package eu.mineoase.tntrun.listener;

import eu.mineoase.tntrun.TNTRun;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class ScoreboardListener implements Listener {
    public static void setBoard(Player p) {



        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("test", "dummy", "asdfasdf");



        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "• MineOase.eu •");
        Score placeholder = objective.getScore("§0");
        placeholder.setScore(10);

        Score placeholder1 = objective.getScore("§1");
        placeholder1.setScore(8);

        Score score2 = objective.getScore(ChatColor.AQUA + "• Name:");
        score2.setScore(7);

        Score score3 = objective.getScore(" " + ChatColor.GREEN + p.getName());
        score3.setScore(6);
        Score placeholder2 = objective.getScore("§2");
        placeholder2.setScore(5);

        Score score4 = objective.getScore(ChatColor.AQUA + "• Credits:");
        score4.setScore(4);

        Team moneyCounter = board.registerNewTeam("moneyCounter");
        moneyCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        moneyCounter.setPrefix(ChatColor.GREEN + "" + TNTRun.getEconomy().getBalance(p.getName()));
        objective.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(3);
        p.setScoreboard(board);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(TNTRun.getInstance(), new Runnable() {
            @Override
            public void run() {
                updateScoreBoard(p);

            }
        }, 0L, 20L);



    }

    public static void updateScoreBoard(Player player) {

        Scoreboard board = player.getScoreboard();
        board.getTeam("moneyCounter").setPrefix(ChatColor.GREEN + "" + TNTRun.getEconomy().getBalance(player.getName()));
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        setBoard(e.getPlayer());
    }
}
