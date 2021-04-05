package xyz.voxylu.spigot.skydef.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import xyz.voxylu.spigot.skydef.App;
import xyz.voxylu.spigot.skydef.Data;
import xyz.voxylu.spigot.skydef.RandomTp;
import xyz.voxylu.spigot.skydef.Stuff;

public class Start implements CommandExecutor {
  private final App app;
  private Scoreboard scoreboard;
  private Stuff stuff;

  public Start(App app) {
    this.app = app;
    this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    this.stuff = new Stuff(this.app);
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

    
    ArrayList<Player> defenders = new ArrayList<Player>(app.data.defenderTeamSize);
    ArrayList<ArrayList<Player>> attackers = new ArrayList<ArrayList<Player>>(Data.attackersNames.length);

    Team defTeam = scoreboard.registerNewTeam(Data.defTeamName);
    defTeam.setColor(ChatColor.AQUA);
    defTeam.setAllowFriendlyFire(false);
    defTeam.setPrefix(String.format("%s[%s]", ChatColor.AQUA, Data.defTeamName));
    
    for (int i = 0; i < Data.attackersNames.length; i++) {
      String name = Data.attackersNames[i];
      ChatColor color = Data.attackersColors[i];
      Team team = scoreboard.registerNewTeam(name);
      team.setColor(color);
      team.setAllowFriendlyFire(false);
      team.setPrefix(String.format("%s[%s]", color, name));
      attackers.set(i, new ArrayList<Player>(app.data.attackerTeamSize));
    }

    ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());

    Collections.shuffle(players);

    int nbDefenders = 0;
    int iAttackers = 0;
    int iNbAttackers = 0;


    for (Player player : players) {
      player.setGameMode(GameMode.SURVIVAL);
      player.getInventory().clear();
      player.setHealth(20);
      player.setFoodLevel(20);

      if (nbDefenders < app.data.defenderTeamSize) {
        defTeam.addEntry(player.getName());
        Bukkit.broadcastMessage(String.format(
          "%s%s%s à rejoin l'équipe des %sdéfenseurs %s!",
          ChatColor.AQUA, player.getName(), ChatColor.RESET, ChatColor.AQUA, ChatColor.RESET
        ));
        nbDefenders++;
        defenders.add(player);
      } else {
        String teamName = Data.attackersNames[iAttackers];
        ChatColor teamColor = Data.attackersColors[iAttackers];
        Team team = scoreboard.getTeam(teamName);

        team.addEntry(player.getName());
        Bukkit.broadcastMessage(String.format(
          "%s%s%s à rejoin l'équipe %s%s %s!",
          teamColor, player.getName(), ChatColor.RESET, teamColor, teamName, ChatColor.RESET
        ));
        iNbAttackers++;

        ArrayList<Player> thisTeamAttackers = attackers.get(iAttackers);
        thisTeamAttackers.set(iNbAttackers, player);

        if (iNbAttackers == app.data.attackerTeamSize) {
          iAttackers++;
          iNbAttackers = 0;
        }
      }
    }

    stuff.setDefStuff(defenders);
    for (Player defender : defenders) {
      defender.teleport(app.data.defPosition);
      defender.setGameMode(GameMode.SURVIVAL);
    }

    RandomTp randomTp = new RandomTp(app.data.defPosition);

    for (ArrayList<Player> attackerTeam : attackers) {
      stuff.setAttStuff(attackerTeam);
      randomTp.teleportMultiplePlayers(attackerTeam);

      for (Player attacker : attackerTeam) {
        attacker.setGameMode(GameMode.SURVIVAL);
      }
    }

    World world = app.data.defPosition.getWorld();

    world.setPVP(true);
    world.setTime(0);
    world.setClearWeatherDuration(1000000);
    world.setDifficulty(Difficulty.NORMAL);

    app.data.inGame = true;
    app.board.setPhasePrep();
    app.data.phaseType = 1;
    app.timerTask.runTaskTimer(app, 0, 20);

    return true;
  }
}