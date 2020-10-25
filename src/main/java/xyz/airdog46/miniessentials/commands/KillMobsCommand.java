package xyz.airdog46.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import xyz.airdog46.miniessentials.Main;

import java.util.concurrent.atomic.AtomicInteger;

public class KillMobsCommand implements CommandExecutor {
    private FileConfiguration config = Main.config;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int argamnt = args.length;
        if (argamnt < 1) {
            return false;
        }

        if (!sender.hasPermission("miniess.killmobs")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }

        AtomicInteger entities = new AtomicInteger();
        for (int a = 1; a <= argamnt; a++) {
            int a2 = argamnt - 1;
            try {
                Bukkit.getWorld(args[a2]).getEntities().forEach(entity -> {
                    if (entity.getType() != EntityType.PLAYER) {
                        entities.getAndIncrement();
                        entity.remove();
                    };
                });
            } catch (NullPointerException e) {
                if (config.getBoolean("stacktrace")) e.printStackTrace();
                sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                return true;
            }
        }
        sender.sendMessage("Removed " + entities + " entities in " + argamnt + " worlds.");

        return true;
    }
}
