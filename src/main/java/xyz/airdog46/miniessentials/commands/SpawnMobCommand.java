package xyz.airdog46.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import xyz.airdog46.miniessentials.Main;

public class SpawnMobCommand implements CommandExecutor {
    private FileConfiguration config = Main.config;
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        if (!sender.hasPermission("miniess.spawnmob")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }

        Player pSender = (Player) sender;
        switch (args.length) {
            case 0:
                return false;
            case 1:
                try {
                    pSender.getWorld().spawnEntity(pSender.getLocation(), EntityType.valueOf(args[0]));
                    sender.sendMessage("Spawned 1 " + EntityType.valueOf(args[0]));
                } catch (IllegalArgumentException e) {
                    if (config.getBoolean("stacktrace")) e.printStackTrace();
                    pSender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                }
                break;
            case 2:
                try {
                    int amount = Integer.parseInt(args[1]);
                    for (int a = 0; a < amount; a++) {
                        try {
                            pSender.getWorld().spawnEntity(pSender.getLocation(), EntityType.valueOf(args[0]));
                        } catch (IllegalArgumentException e) {
                            if (config.getBoolean("stacktrace")) e.printStackTrace();
                            pSender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                            return true;
                        }
                    }
                    sender.sendMessage("Spawned " + amount + " " + EntityType.valueOf(args[0]));
                } catch (NumberFormatException e) {
                    if (config.getBoolean("stacktrace")) e.printStackTrace();
                    pSender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                }
                break;
            default:
                Player secondPlayer = Bukkit.getPlayer(args[2]);
                try {
                    int amount = Integer.parseInt(args[1]);
                    for (int a = 0; a < amount; a++) {
                        try {
                            secondPlayer.getWorld().spawnEntity(secondPlayer.getLocation(), EntityType.valueOf(args[0]));
                        } catch (IllegalArgumentException e) {
                            if (config.getBoolean("stacktrace")) e.printStackTrace();
                            sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                            return true;
                        }
                    }
                } catch (NumberFormatException e) {
                    if (config.getBoolean("stacktrace")) e.printStackTrace();
                    pSender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                }
                break;
        }
        return true;
    }
}
