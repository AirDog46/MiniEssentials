package xyz.airdog46.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.airdog46.miniessentials.Main;

public class FeedCommand implements CommandExecutor {
    private FileConfiguration config = Main.config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        if (!sender.hasPermission("miniess.feed")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }

        switch (args.length) {
            case 0:
                Player pSender = (Player) sender;
                pSender.setSaturation(20);
                sender.sendMessage("Successfully fed yourself.");
                return true;
            default:
                int argamnt = args.length - 1;
                for (int a = 0; a <= argamnt; a++) {
                    Player player = Bukkit.getPlayer(args[argamnt]);
                    try {
                        player.setSaturation(20);
                    } catch (NullPointerException e) {
                        if (config.getBoolean("stacktrace")) e.printStackTrace();
                        sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                        return true;
                    }
                }
                sender.sendMessage("Successfully fed " + argamnt++ + " players.");
        }
        return true;
    }
}
