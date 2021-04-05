package xyz.voxylu.spigot.skydef.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import xyz.voxylu.spigot.skydef.App;
import xyz.voxylu.spigot.skydef.Data;

public class Def implements CommandExecutor {
  private App app;

  public Def(App app) {
    this.app = app;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      final Player player = (Player) sender;
      Scoreboard scoreboard = app.getServer().getScoreboardManager().getMainScoreboard();
      Team team = scoreboard.getEntryTeam(player.getName());

      if (team == null || team.getName() != Data.defTeamName) {
        player.sendMessage(ChatColor.RED + "Seul les défenseurs peuvent utiliser cette commande.");
        return true;
      }

      if (this.app.data.defPosition == null) {
        player.sendMessage(ChatColor.RED + "Un admin à mal fait son boulot.");
      }

      // TODO: check timer

      final Location location = player.getLocation();

      player.sendMessage(ChatColor.AQUA + "Téléportation en cours, " + ChatColor.BOLD + "ne bouge pas" + ChatColor.RESET
          + ChatColor.AQUA + "pendant 5 secondes...");

      BukkitRunnable task = new BukkitRunnable() {
        @Override
        public void run() {
          Location newLocation = player.getLocation();

          if (newLocation.equals(location)) {
            player.sendMessage(ChatColor.AQUA + "Téléportation !");
            player.teleport(app.data.defPosition);
          } else {
            player.sendMessage(ChatColor.RED + "Téléportation annulée car tu as bougé...");
          }
        }
      };

      task.runTaskLater(app, 5 * 20);
      return true;
    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }
  }
}