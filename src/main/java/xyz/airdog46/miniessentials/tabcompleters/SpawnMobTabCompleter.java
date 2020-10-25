package xyz.airdog46.miniessentials.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class SpawnMobTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("miniess.spawnmob")) {
            return null;
        }
        switch (args.length) {
            case 1:
                List<String> list2 = new ArrayList<>();
                for (EntityType entityType : EnumSet.allOf(EntityType.class)) {
                    list2.add(entityType.name());
                }
                return list2;
            case 2:
                List<String> list3 = new ArrayList<>();
                list3.add("1");
                list3.add("10");
                list3.add("100");
                list3.add("150");
                return list3;
            default:
                return null;
        }
    }
}
