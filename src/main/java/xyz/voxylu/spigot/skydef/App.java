package xyz.voxylu.spigot.skydef;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    public Data data;

    public App() {
        this.data = new Data();
    }

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}