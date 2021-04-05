package xyz.voxylu.spigot.skydef.commands;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.voxylu.spigot.skydef.Data;

public class Createteams implements CommandExecutor {
  private Logger logger;
  private Data data;

  public Createteams(Logger logger, Data data) {
    this.logger = logger;
    this.data = data;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length != 2) {
        return false;
      }

      int defenderSize = Integer.parseInt(args[0]);
      int attackerSize = Integer.parseInt(args[1]);

      player.sendMessage(
          String.format("Taille équipe de défense: %d, taille équipe d'attaque: %d.", defenderSize, attackerSize));

      this.data.attackerTeamSize = attackerSize;
      this.data.defenderTeamSize = defenderSize;

      return true;
    } else {
      sender.sendMessage("Only player can use this command.");
      return false;
    }
  }
}
