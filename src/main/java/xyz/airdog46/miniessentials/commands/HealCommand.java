package xyz.airdog46.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.airdog46.miniessentials.Main;

public class HealCommand implements CommandExecutor {
    private FileConfiguration config = Main.config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        if (sender.hasPermission("miniess.heal")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }

        switch (args.length) {
            case 0:
                Player pSender = (Player) sender;
                pSender.setHealth(20);
                pSender.setSaturation(20);
                pSender.getActivePotionEffects().clear();
                sender.sendMessage("Successfully healed yourself.");
                return true;
            default:
                int argamnt = args.length - 1;
                for (int a = 0; a <= argamnt; a++) {
                    Player player = Bukkit.getPlayer(args[argamnt]);
                    try {
                        player.setHealth(20);
                        player.setSaturation(20);
                        player.getActivePotionEffects().clear();
                    } catch (NullPointerException e) {
                        if (config.getBoolean("stacktrace")) e.printStackTrace();
                        sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + e.getMessage());
                        return true;
                    }
                }
                sender.sendMessage("Successfully healed " + argamnt++ + " players.");
        }
        return true;
    }
}
