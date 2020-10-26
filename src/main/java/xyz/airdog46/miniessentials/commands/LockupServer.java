package xyz.airdog46.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.airdog46.miniessentials.Main;

public class LockupServer implements CommandExecutor {
    private FileConfiguration config = Main.config;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("miniess.lockupserver")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }
        if (args.length < 1)
            return false;

        try {
            long ms = Long.parseLong(args[0]);
            int time = (int) (ms / 1000);
            if (Bukkit.spigot().getConfig().getInt("settings.timeout-time") <= time)
                sender.sendMessage("The time you entered is longer than the timeout time. The server will crash.");
            Bukkit.broadcastMessage("The server has been locked up for " + time + " seconds.");
            Thread.sleep(ms);
        } catch (IllegalArgumentException | InterruptedException e) {
            if (config.getBoolean("stacktrace")) e.printStackTrace();
            sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
        }
        return true;
    }
}
