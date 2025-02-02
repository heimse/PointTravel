package com.example.pointtravel.commands;

import com.example.pointtravel.PointTravelPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetPointCommand implements CommandExecutor {
    private final PointTravelPlugin plugin;

    public SetPointCommand(PointTravelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только игрок может использовать эту команду.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Использование: /setpoint <имя>");
            return true;
        }
        Player player = (Player) sender;
        String pointName = args[0];

        Map<String, org.bukkit.Location> points = plugin.getTravelPoints()
                .computeIfAbsent(player.getUniqueId(), k -> new HashMap<>());
        points.put(pointName, player.getLocation());

        player.sendMessage(ChatColor.GREEN + "Точка '" + pointName + "' успешно установлена!");
        return true;
    }
}
