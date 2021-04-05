package xyz.voxylu.spigot.skydef;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Data {
  public Location lobbyPosition;
  public Location defPosition;
  public Location defChest;

  public int attackerTeamSize = 0;
  public int defenderTeamSize = 0;

  public boolean inGame = false;
  public int phaseType = 0;
  public int time = 0;

  public static final String defTeamName = "Défenseur";
  public static final String[] attackersNames = {
    "Lapis-Lazuli", "Redstone", "Or", "Creeper", "Skelette", "Enderman"
  };
  public static final ChatColor[] attackersColors = {
    ChatColor.BLUE, ChatColor.RED, ChatColor.GOLD, ChatColor.GREEN, ChatColor.GRAY, ChatColor.LIGHT_PURPLE
  };
}
