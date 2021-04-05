package xyz.voxylu.spigot.skydef;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RandomTp {
  private Location center;
  private int size;
  private Random rand = new Random();

  public RandomTp(Location center) {
    this.center = center;
  }

  private int getRandomCoord(int coord) {
    int max = coord + size;
    int min = coord - size;

    return rand.nextInt((max - min) + 1) + min;
  }

  public void setCenter(Location center) {
    this.center = center;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Location getLocation(World world) {
    int x = getRandomCoord(this.center.getBlockX());
    int y = 120;
    int z = getRandomCoord(this.center.getBlockZ());

    Location location = new Location(world, x, y, z);
    Biome biome = location.getBlock().getBiome();

    if (biome.toString().toLowerCase().contains("ocean")) {
      return getLocation(world);
    }

    return location;
  }

  public void teleportOnePlayer(Player player) {
    World world = player.getWorld();
    Location location = getLocation(world);

    teleportToLocation(player, location);
  }

  public void teleportMultiplePlayers(ArrayList<Player> players) {
    World world = center.getWorld();
    Location location = getLocation(world);

    for (Player player : players) {
      teleportToLocation(player, location);
    }
  }

  private void teleportToLocation(Player player, Location location) {
    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 30, 5));
    player.teleport(location);
  }
}
