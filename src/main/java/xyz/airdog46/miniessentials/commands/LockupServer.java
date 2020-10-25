package xyz.airdog46.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LockupServer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("miniess.lockupserver")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }
        Bukkit.broadcastMessage("The server has been locked up. Awaiting restart");
        while (true) {

        }
    }
}
