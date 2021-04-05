package xyz.voxylu.spigot.skydef.commands;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

import xyz.voxylu.spigot.skydef.Data;

public class Setlobby implements CommandExecutor {
  private Logger logger;
  private Data data;

  public Setlobby(Logger logger, Data data) {
    this.logger = logger;
    this.data = data;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      Location location = player.getLocation();
      World world = player.getWorld();

      player
          .sendMessage(String.format("Lobby set en %d %d %d", location.getX(), location.getY(), location.getBlockZ()));

      world.setPVP(false);
      world.setSpawnLocation(location);

      this.data.lobbyPosition = location;

      return true;
    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }

  }

}