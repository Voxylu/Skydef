package xyz.voxylu.spigot.skydef;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class Stuff {
  private App app;

  public Stuff(App app) {
    this.app = app;
  }

  public void setDefStuff(ArrayList<Player> defenders) {
    Location chestLocation = app.data.defChest;

    Block blockAtLocation = chestLocation.getBlock();

    if (blockAtLocation.getState() instanceof Chest) {
      Chest chest = (Chest) blockAtLocation.getState();

      Inventory chestInventory = chest.getInventory();

      chestInventory.clear();

      chestInventory.addItem(new ItemStack(Material.DIAMOND_PICKAXE));
      chestInventory.addItem(new ItemStack(Material.WHITE_BED));
      chestInventory.addItem(new ItemStack(Material.WATER_BUCKET));
      chestInventory.addItem(new ItemStack(Material.OAK_LOG,16));

      for (Player defender : defenders) {
        PlayerInventory defenderInventory = defender.getInventory();

        defenderInventory.clear();

        defenderInventory.addItem(new ItemStack(Material.IRON_SWORD));
        defenderInventory.addItem(new ItemStack(Material.IRON_PICKAXE));
        defenderInventory.addItem(new ItemStack(Material.BOW));
        defenderInventory.addItem(new ItemStack(Material.ARROW, 16));
        defenderInventory.addItem(new ItemStack(Material.COOKED_BEEF, 16));
        ItemStack[] armor = { new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_LEGGINGS),
          new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_HELMET), };
        defenderInventory.setArmorContents(armor);
      }
    } else {
      Bukkit.broadcastMessage(String.format("%sLe coffre des défenseur n'a pas été initiliasé correctement :(", ChatColor.RED));
    }
  }

  public void setAttStuff(ArrayList<Player> attackers) {
    for (Player attacker : attackers) {
      PlayerInventory attackerInventory = attacker.getInventory();
      
      attackerInventory.clear();
      
      attackerInventory.addItem(new ItemStack(Material.WHITE_BED));
    }
  }
}
