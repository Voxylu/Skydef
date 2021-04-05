package xyz.voxylu.spigot.skydef.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import xyz.voxylu.spigot.skydef.App;
import xyz.voxylu.spigot.skydef.Data;

public class Rtp implements CommandExecutor {
  private App app;
  private Scoreboard scoreboard;

  public Rtp(App app) {
    this.app = app;
    this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (app.data.attackerTeamSize == 0 || app.data.attackerTeamSize == 0) {
      sender.sendMessage("Pas de taille d'équipe définie.");
      return true;
    } else if (app.data.defPosition == null) {
      sender.sendMessage("Pas de point de spawn des défenseurs.");
      return true;
    }

    // Clean toutes les teams
    scoreboard.getTeams().forEach((team) -> team.unregister());

    ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());

    Collections.shuffle(players);

    int nb_defenders = 0;
    int i_attackers = 0;
    int i_nb_attackers = 0;

    Team defTeam = scoreboard.registerNewTeam(Data.defTeamName);
    defTeam.setColor(ChatColor.AQUA);
    defTeam.setAllowFriendlyFire(false);
    defTeam.setPrefix(String.format("%s[%s]", ChatColor.AQUA, Data.defTeamName));

  }
}