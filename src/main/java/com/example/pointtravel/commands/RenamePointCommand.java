package com.example.pointtravel.commands;

import com.example.pointtravel.PointTravelPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class RenamePointCommand implements CommandExecutor {
    private final PointTravelPlugin plugin;

    public RenamePointCommand(PointTravelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только игрок может использовать эту команду.");
            return true;
        }
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Использование: /renamepoint <старое имя> <новое имя>");
            return true;
        }
        Player player = (Player) sender;
        String oldName = args[0];
        String newName = args[1];

        Map<String, org.bukkit.Location> points = plugin.getTravelPoints().get(player.getUniqueId());
        if (points == null || !points.containsKey(oldName)) {
            player.sendMessage(ChatColor.RED + "Точка с именем '" + oldName + "' не найдена.");
            return true;
        }
        if (points.containsKey(newName)) {
            player.sendMessage(ChatColor.RED + "Точка с именем '" + newName + "' уже существует.");
            return true;
        }
        org.bukkit.Location loc = points.remove(oldName);
        points.put(newName, loc);
        player.sendMessage(ChatColor.GREEN + "Точка '" + oldName + "' успешно переименована в '" + newName + "'.");
        return true;
    }
}
