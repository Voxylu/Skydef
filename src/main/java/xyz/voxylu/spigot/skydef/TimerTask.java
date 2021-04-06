package xyz.voxylu.spigot.skydef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable {
  public int timer = 0;
  private App app;

  public TimerTask(App app) {
    this.app = app;
  }

  private String formatTime() {
    int h = timer / 3600;
    int m = timer % 3600 / 60;
    int s = timer % 3600 % 60;

    return String.format("%02d:%02d:%02d", h, m, s);
  }

  @Override
  public void run() {
    timer++;

    if (timer == 3600) {
      app.board.setPhaseCombat();
      app.data.phaseType = 2;
      Bukkit.broadcastMessage(ChatColor.DARK_RED + "Fin de la phase de préparation! Début de la phase des combats!");
    } else if (timer == 7200) {
      Bukkit.broadcastMessage(ChatColor.RED + "Fin !");
      app.data.phaseType = 3;
      cancel();
    }

    app.board.setTimer(formatTime());

  }
}
