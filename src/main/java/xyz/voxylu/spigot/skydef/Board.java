package xyz.voxylu.spigot.skydef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Board {
  public void setScoreBoard(Player player) {
    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective obj = board.registerNewObjective("title", "dummy", "SkyDef");
    obj.setDisplaySlot(DisplaySlot.SIDEBAR);

    Team phaseTeam = board.registerNewTeam("phaseTeam");
    phaseTeam.addEntry("phase");
    phaseTeam.setPrefix(String.format("%sPhase: %sEn attente", ChatColor.WHITE, ChatColor.GRAY));
    obj.getScore("phase").setScore(15);


    Team timerTeam = board.registerNewTeam("timerTeam");
    timerTeam.addEntry("timer");
    timerTeam.setPrefix(String.format("%sTemps: %s00:00:00", ChatColor.BLUE, ChatColor.WHITE));
    obj.getScore("timer").setScore(13);

    player.setScoreboard(board);
  }

  public void setPhasePrep() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("phaseTeam");
      phaseTeam.setPrefix(String.format("%sPhase: %sPrépartion", ChatColor.WHITE, ChatColor.AQUA));
    }
  }

  public void setPhaseCombat() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("phaseTeam");
      phaseTeam.setPrefix(String.format("%sPhase: %sCombat", ChatColor.WHITE, ChatColor.RED));
    }
  }

  public void setTimer(String time) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("timerTeam");
      phaseTeam.setPrefix(String.format("%sTemps: %s%s", ChatColor.BLUE, ChatColor.WHITE, time));
    }
  }
}
