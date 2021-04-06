package xyz.voxylu.spigot.skydef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Board {
  public void setScoreBoard(Player player) {
    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective obj = board.registerNewObjective("title", "dummy", "SkyDef");
    obj.setDisplaySlot(DisplaySlot.SIDEBAR);

    Team phaseTeam = board.registerNewTeam("phaseTeam");
    String phaseEntry = ChatColor.RED + "" + ChatColor.WHITE;
    phaseTeam.addEntry(phaseEntry);
    phaseTeam.setPrefix(String.format("%sPhase: %sEn attente", ChatColor.BLUE, ChatColor.GRAY));
    obj.getScore(phaseEntry).setScore(15);

    Team timerTeam = board.registerNewTeam("timerTeam");
    String teamEntry = ChatColor.AQUA + "" + ChatColor.WHITE;
    timerTeam.addEntry(teamEntry);
    timerTeam.setPrefix(String.format("%sTemps: %s00:00:00", ChatColor.BLUE, ChatColor.WHITE));
    obj.getScore(teamEntry).setScore(13);

    player.setScoreboard(board);
  }

  public void setPhase(int phase) {
    if (phase == 0) {
      setPhaseAttente();
    } else if (phase == 1) {
      setPhasePrep();
    } else if (phase == 2) {
      setPhaseCombat();
    } else {
      setPhaseFin();
    }
  }

  public void setPhaseFin() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("phaseTeam");
      phaseTeam.setPrefix(String.format("%sPhase: %Fin", ChatColor.BLUE, ChatColor.RED));
    }
  }

  public void setPhasePrep() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("phaseTeam");
      phaseTeam.setPrefix(String.format("%sPhase: %sPrépartion", ChatColor.BLUE, ChatColor.AQUA));
    }
  }

  public void setPhaseCombat() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("phaseTeam");
      phaseTeam.setPrefix(String.format("%sPhase: %sCombat", ChatColor.BLUE, ChatColor.RED));
    }
  }

  public void setPhaseAttente() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Scoreboard board = player.getScoreboard();
      Team phaseTeam = board.getTeam("phaseTeam");
      phaseTeam.setPrefix(String.format("%sPhase: %sEn attente", ChatColor.BLUE, ChatColor.GRAY));
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
