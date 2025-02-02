package com.example.pointtravel.listeners;

import com.example.pointtravel.PointTravelPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {
    private final PointTravelPlugin plugin;

    public GUIListener(PointTravelPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        // Используем заголовок из InventoryView
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Ваши точки")) {
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || !clicked.hasItemMeta()) return;
            String pointName = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
            if (plugin.getTravelPoints().get(player.getUniqueId()) != null &&
                    plugin.getTravelPoints().get(player.getUniqueId()).containsKey(pointName)) {
                Location loc = plugin.getTravelPoints().get(player.getUniqueId()).get(pointName);
                player.teleport(loc);
                player.sendMessage(ChatColor.AQUA + "Телепортация к точке '" + pointName + "' выполнена!");
                player.closeInventory();
            }
        }
    }
}
