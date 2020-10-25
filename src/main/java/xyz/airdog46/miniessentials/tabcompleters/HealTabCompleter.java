package xyz.airdog46.miniessentials.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class HealTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> players.add(player.getName()));
        return players;
    }
}
