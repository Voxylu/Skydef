package xyz.voxylu.spigot.skydef.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

import xyz.voxylu.spigot.skydef.App;

public class Setlobby implements CommandExecutor {
  private App app;

  public Setlobby(App app) {
    this.app = app;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      Location location = player.getLocation();
      World world = player.getWorld();

      player.sendMessage(
          String.format("Lobby set en %d %d %d", location.getBlockX(), location.getBlockY(), location.getBlockZ()));

      world.setPVP(false);
      world.setSpawnLocation(location);

      app.data.lobbyPosition = location;

      return true;
    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }

  }

}
