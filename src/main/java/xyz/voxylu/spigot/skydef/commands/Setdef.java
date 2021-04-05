package xyz.voxylu.spigot.skydef.commands;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.voxylu.spigot.skydef.Data;

public class Setdef implements CommandExecutor {
  private Logger logger;
  private Data data;

  public Setdef(Logger logger, Data data) {
    this.logger = logger;
    this.data = data;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      Location location = player.getLocation();

      player.sendMessage(String.format("Point de spawn des défenseurs en %d %d %d.", location.getX(),
          location.getBlockY(), location.getBlockZ()));

      this.data.defPosition = location;

      return true;
    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }
  }
}
