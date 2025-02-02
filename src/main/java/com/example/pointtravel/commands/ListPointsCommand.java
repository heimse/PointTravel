package com.example.pointtravel.commands;

import com.example.pointtravel.PointTravelPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;

public class ListPointsCommand implements CommandExecutor {
    private final PointTravelPlugin plugin;

    public ListPointsCommand(PointTravelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только игрок может использовать эту команду.");
            return true;
        }
        Player player = (Player) sender;
        Map<String, org.bukkit.Location> points = plugin.getTravelPoints().get(player.getUniqueId());
        if (points == null || points.isEmpty()) {
            player.sendMessage(ChatColor.YELLOW + "У вас не установлено ни одной точки.");
        } else {
            Set<String> pointNames = points.keySet();
            player.sendMessage(ChatColor.YELLOW + "Ваши точки: " + String.join(", ", pointNames));
        }
        return true;
    }
}
