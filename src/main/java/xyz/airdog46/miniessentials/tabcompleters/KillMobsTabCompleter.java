package xyz.airdog46.miniessentials.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class KillMobsTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("miniess.killmobs")) {
            List<String> list = new ArrayList<>();
            Bukkit.getWorlds().forEach(world -> list.add(world.getName()));
            return list;
        } else {
            List<String> emptylist = new ArrayList<>();
            return emptylist;
        }
    }
}
