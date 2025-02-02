package com.example.pointtravel.commands;

import com.example.pointtravel.PointTravelPlugin;
import com.example.pointtravel.gui.PointTravelGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PointsMenuCommand implements CommandExecutor {
    private final PointTravelPlugin plugin;
    private final PointTravelGUI gui;

    public PointsMenuCommand(PointTravelPlugin plugin) {
        this.plugin = plugin;
        this.gui = new PointTravelGUI(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только игрок может использовать эту команду.");
            return true;
        }
        Player player = (Player) sender;
        Inventory menu = gui.createPointsMenu(player);
        player.openInventory(menu);
        return true;
    }
}
