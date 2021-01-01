package xyz.airdog46.miniessentials;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.airdog46.miniessentials.commands.*;
import xyz.airdog46.miniessentials.listeners.ZombieMobListener;
import xyz.airdog46.miniessentials.tabcompleters.FeedTabCompleter;
import xyz.airdog46.miniessentials.tabcompleters.HealTabCompleter;
import xyz.airdog46.miniessentials.tabcompleters.KillMobsTabCompleter;
import xyz.airdog46.miniessentials.tabcompleters.SpawnMobTabCompleter;

public class Main extends JavaPlugin {
    public static FileConfiguration config;

    void registerCommands() {
        Bukkit.getPluginCommand("spawnmob").setExecutor(new SpawnMobCommand());
        Bukkit.getPluginCommand("killmob").setExecutor(new KillMobsCommand());
        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("feed").setExecutor(new FeedCommand());
        Bukkit.getPluginCommand("lockupserver").setExecutor(new LockupServer());
        Bukkit.getPluginCommand("zombiemode").setExecutor(new SetZombieEntityCommand());
    }

    void registerTabCompleters() {
        Bukkit.getPluginCommand("spawnmob").setTabCompleter(new SpawnMobTabCompleter());
        Bukkit.getPluginCommand("killmob").setTabCompleter(new KillMobsTabCompleter());
        Bukkit.getPluginCommand("heal").setTabCompleter(new HealTabCompleter());
        Bukkit.getPluginCommand("feed").setTabCompleter(new FeedTabCompleter());
    }

    void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ZombieMobListener(), this);
    }

    public void onEnable() {
        config = getConfig();
        this.saveDefaultConfig();
        registerCommands();
        registerTabCompleters();
    }

    public void onDisable() {

    }
}
