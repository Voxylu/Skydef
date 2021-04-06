package xyz.voxylu.spigot.skydef.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.voxylu.spigot.skydef.App;

public class Setdef implements CommandExecutor {
  private App app;

  public Setdef(App app) {
    this.app = app;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length != 1) {
        return false;
      }

      String arg = args[0].toLowerCase();

      app.logger.info(String.format("[%s]", arg));

      if (arg.equals("spawn")) {
        Location location = player.getLocation();

        player.sendMessage(String.format("Point de spawn des défenseurs en %d %d %d.", location.getBlockX(),
            location.getBlockY(), location.getBlockZ()));

        app.data.defPosition = location;

        return true;
      } else if (arg.equals("chest")) {

        app.logger.info("chest");

        Block block = player.getTargetBlock(null, 5);

        if (block.getType() != Material.CHEST) {
          player.sendMessage(String.format("%sLe block sélectionné n'est pas un coffre."));
          return true;
        }

        Location location = block.getLocation();

        app.data.defChest = location;

        player.sendMessage(String.format("Coffre des défenseurs en %d %d %d.", location.getBlockX(),
            location.getBlockY(), location.getBlockZ()));

        return true;
      } else {
        return false;
      }

    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }
  }
}
