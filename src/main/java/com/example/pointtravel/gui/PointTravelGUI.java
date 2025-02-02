package com.example.pointtravel.gui;

import com.example.pointtravel.PointTravelPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PointTravelGUI {
    private final PointTravelPlugin plugin;

    public PointTravelGUI(PointTravelPlugin plugin) {
        this.plugin = plugin;
    }

    public Inventory createPointsMenu(Player player) {
        Map<String, Location> points = plugin.getTravelPoints().get(player.getUniqueId());
        int size = 9;
        if (points != null && points.size() > 9) {
            size = ((points.size() - 1) / 9 + 1) * 9;
        }
        Inventory inv = Bukkit.createInventory(null, size, ChatColor.GREEN + "Ваши точки");

        if (points != null) {
            for (Map.Entry<String, Location> entry : points.entrySet()) {
                String pointName = entry.getKey();
                ItemStack item = new ItemStack(Material.COMPASS);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.YELLOW + pointName);
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Нажмите, чтобы телепортироваться");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.addItem(item);
            }
        }
        return inv;
    }
}
