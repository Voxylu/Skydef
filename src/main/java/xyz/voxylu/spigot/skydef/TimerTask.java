package xyz.voxylu.spigot.skydef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable {
  private int timer = 0;
  private App app;

  public TimerTask(App app) {
    this.app = app;
  }

  private String formatTime() {
    int minutes = timer % 60;
    int seconds = timer % 3600;
    int hours = timer / 3600;
    
    return String.format("%d:%d:%d", hours, minutes, seconds);
  }

  @Override
  public void run() {
    timer++;

    if (timer == 3600) {
      app.board.setPhaseCombat();
      app.data.phaseType = 2;
    } else if (timer == 7200) {
      Bukkit.broadcastMessage(ChatColor.RED + "Fin !");
      app.data.phaseType = 3;
      cancel();
    }

    app.board.setTimer(formatTime());

  }
}
