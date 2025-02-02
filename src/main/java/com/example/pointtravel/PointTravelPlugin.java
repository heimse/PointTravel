package com.example.pointtravel;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PointTravelPlugin extends JavaPlugin {
    // Хранение точек: для каждого игрока (UUID) сопоставляем имя точки с локацией
    private final Map<UUID, Map<String, Location>> travelPoints = new HashMap<>();

    @Override
    public void onEnable() {
        // Регистрируем команды с передачей ссылки на плагин
        getCommand("setpoint").setExecutor(new SetPointCommand(this));
        getCommand("goto").setExecutor(new GoToCommand(this));
        getCommand("listpoints").setExecutor(new ListPointsCommand(this));

        getLogger().info("PointTravel загружен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PointTravel выгружен.");
    }

    public Map<UUID, Map<String, Location>> getTravelPoints() {
        return travelPoints;
    }
}
