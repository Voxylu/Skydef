package xyz.voxylu.spigot.skydef;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.voxylu.spigot.skydef.commands.Createteams;
import xyz.voxylu.spigot.skydef.commands.Def;
import xyz.voxylu.spigot.skydef.commands.Rtp;
import xyz.voxylu.spigot.skydef.commands.Setdef;
import xyz.voxylu.spigot.skydef.commands.Setlobby;
import xyz.voxylu.spigot.skydef.commands.Start;

public class App extends JavaPlugin {
    public Data data;
    public Board board;
    public TimerTask timerTask;

    public App() {
        this.data = new Data();
        this.board = new Board();
        this.timerTask = new TimerTask(this.board);

    }
    
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        getCommand("createteams").setExecutor(new Createteams(this));
        getCommand("def").setExecutor(new Def(this));
        getCommand("rtp").setExecutor(new Rtp(this));
        getCommand("setdef").setExecutor(new Setdef(this));
        getCommand("setlobby").setExecutor(new Setlobby(this));
        getCommand("start").setExecutor(new Start(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}