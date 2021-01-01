package xyz.airdog46.miniessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetZombieEntityCommand implements CommandExecutor {
    boolean zombie = false;

    public void setZombie(boolean zombie) {
        this.zombie = zombie;
    }

    public boolean isZombieModeEnabled() {
        return zombie;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("miniess.setzombie")) {
            sender.sendMessage(ChatColor.RED + "No permission");
            return true;
        }

        switch (args[1]) {
            case "true":
            case "yes":
            case "1":
            case "enable":
                setZombie(true);
                break;
            case "false":
            case "no":
            case "0":
            case "disable":
                setZombie(false);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Please specify a boolean.");
                return true;
        }
        sender.sendMessage(ChatColor.GREEN + "You have " + (zombie ? " enabled " : ChatColor.RED + " disabled ") + ChatColor.GREEN + " zombie mode.");
        return true;
    }
}
