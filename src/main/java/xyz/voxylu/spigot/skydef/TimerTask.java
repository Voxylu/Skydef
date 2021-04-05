package xyz.voxylu.spigot.skydef;

import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable {
  private int timer = 0;
  private Board board;

  public TimerTask(Board board) {
    this.board = board;
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
    board.setTimer(formatTime());
  }
}
