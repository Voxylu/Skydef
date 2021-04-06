package xyz.voxylu.spigot.skydef;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.voxylu.spigot.skydef.commands.Createteams;
import xyz.voxylu.spigot.skydef.commands.Def;
import xyz.voxylu.spigot.skydef.commands.Rtp;
import xyz.voxylu.spigot.skydef.commands.Setdef;
import xyz.voxylu.spigot.skydef.commands.Setlobby;
import xyz.voxylu.spigot.skydef.commands.Start;
import xyz.voxylu.spigot.skydef.commands.Timer;

public class App extends JavaPlugin {
    public Data data;
    public Board board;
    public TimerTask timerTask;
    public Logger logger;

    public App() {
        this.data = new Data();
        this.board = new Board();
        this.timerTask = new TimerTask(this);
        this.logger = getLogger();
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
        getCommand("timer").setExecutor(new Timer(this));

        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}