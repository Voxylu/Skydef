package xyz.voxylu.spigot.skydef.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.voxylu.spigot.skydef.App;

public class Timer implements CommandExecutor {
  public App app;

  public Timer(App app) {
    this.app = app;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      app.logger.info(args.toString());

      if (args.length == 2) {
        String subCommand = args[0];
        String arg = args[1];

        app.logger.info(String.format("%s, %s", subCommand, arg));

        if (subCommand.equals("phase")) {
          app.logger.info("in phase");
          try {
            int phase = Integer.parseInt(arg);
            app.logger.info(String.format("%d", phase));
            if (phase >= 0 && phase <= 3) {
              app.data.phaseType = phase;
              app.board.setPhase(phase);
              return true;
            } else {
              return false;
            }
          } catch (Exception e) {
            return false;
          }
        } else if (subCommand.equals("timer")) {
          app.logger.info("in timer");
          try {
            int timer = Integer.parseInt(arg);
            app.logger.info(String.format("%d", timer));
            app.timerTask.timer = timer;
            return true;
          } catch (Exception e) {
            return false;
          }
        } else {
          return false;
        }
      } else {
        return false;
      }

    } else {
      sender.sendMessage("Only player can use this command.");
      return true;
    }
  }
}
