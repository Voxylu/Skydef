package xyz.voxylu.spigot.skydef.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.voxylu.spigot.skydef.App;
import xyz.voxylu.spigot.skydef.RandomTp;

public class Rtp implements CommandExecutor {
  private App app;
  private RandomTp randomTp;

  public Rtp(App app) {
    this.app = app;
    this.randomTp = new RandomTp(this.app.data.defPosition);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      this.randomTp.teleportOnePlayer(player);

      return true;
    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }
  }
}
