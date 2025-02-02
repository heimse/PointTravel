package com.example.pointtravel.commands;

import com.example.pointtravel.PointTravelPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class GoToCommand implements CommandExecutor {
    private final PointTravelPlugin plugin;

    public GoToCommand(PointTravelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только игрок может использовать эту команду.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Использование: /goto <имя>");
            return true;
        }
        Player player = (Player) sender;
        String pointName = args[0];

        Map<String, Location> points = plugin.getTravelPoints().get(player.getUniqueId());
        if (points == null || !points.containsKey(pointName)) {
            player.sendMessage(ChatColor.RED + "Точка '" + pointName + "' не найдена. Установите её командой /setpoint.");
            return true;
        }
        Location target = points.get(pointName);
        player.teleport(target);
        player.sendMessage(ChatColor.AQUA + "Телепортация к точке '" + pointName + "' выполнена!");
        return true;
    }
}
