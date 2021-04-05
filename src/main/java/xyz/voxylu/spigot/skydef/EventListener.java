package xyz.voxylu.spigot.skydef;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class EventListener implements Listener {
  private App app;
  private Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

  public EventListener(App app) {
    this.app = app;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    app.board.setScoreBoard(player);

    if (this.app.data.inGame == false) {

      if (this.app.data.lobbyPosition != null) {
        player.teleport(this.app.data.lobbyPosition);
        player.setGameMode(GameMode.ADVENTURE);
      }
    } else {
      Team team = score.getEntryTeam(player.getName());

      if (team == null) {
        player.setGameMode(GameMode.SPECTATOR);
      }
    }
  }

  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();

    if (this.app.data.inGame == false && this.app.data.lobbyPosition != null) {
      event.setRespawnLocation(this.app.data.lobbyPosition);
    }

    BukkitRunnable task = new BukkitRunnable() {
      @Override
      public void run() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 5));
      }
    };

    if (this.app.data.inGame) {
      Location bedLocation = player.getBedSpawnLocation();

      if (bedLocation == null) {
        RandomTp randomTp = new RandomTp(app.data.defPosition);

        Location respawnLocation = randomTp.getLocation(app.data.defPosition.getWorld());
        event.setRespawnLocation(respawnLocation);
        task.runTaskLater(this.app, 1);
      }
    }
  }
}