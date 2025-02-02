package com.example.pointtravel;

import com.example.pointtravel.commands.GoToCommand;
import com.example.pointtravel.commands.ListPointsCommand;
import com.example.pointtravel.commands.PointsMenuCommand;
import com.example.pointtravel.commands.RenamePointCommand;
import com.example.pointtravel.commands.SetPointCommand;
import com.example.pointtravel.listeners.GUIListener;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PointTravelPlugin extends JavaPlugin {
    // Хранение точек: для каждого игрока (UUID) сопоставляем имя точки с Location
    private final Map<UUID, Map<String, Location>> travelPoints = new HashMap<>();
    private File pointsFile;
    private FileConfiguration pointsConfig;

    @Override
    public void onEnable() {
        // Загрузка точек и регистрация команд (как было)
        loadPoints();

        getCommand("setpoint").setExecutor(new SetPointCommand(this));
        getCommand("goto").setExecutor(new GoToCommand(this));
        getCommand("listpoints").setExecutor(new ListPointsCommand(this));
        getCommand("renamepoint").setExecutor(new RenamePointCommand(this));
        getCommand("pointsmenu").setExecutor(new com.example.pointtravel.commands.PointsMenuCommand(this));

        // Регистрируем слушатель для GUI (который обрабатывает клики в инвентаре)
        getServer().getPluginManager().registerEvents(new com.example.pointtravel.listeners.GUIListener(this), this);

        getLogger().info("PointTravel загружен!");
    }


    @Override
    public void onDisable() {
        // Сохраняем точки перед отключением плагина
        savePoints();
        getLogger().info("PointTravel выгружен.");
    }

    public Map<UUID, Map<String, Location>> getTravelPoints() {
        return travelPoints;
    }

    private void loadPoints() {
        pointsFile = new File(getDataFolder(), "points.yml");
        if (!pointsFile.exists()) {
            try {
                pointsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pointsConfig = YamlConfiguration.loadConfiguration(pointsFile);
        if (pointsConfig.contains("points")) {
            for (String uuidStr : pointsConfig.getConfigurationSection("points").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                Map<String, Location> playerPoints = new HashMap<>();
                for (String pointName : pointsConfig.getConfigurationSection("points." + uuidStr).getKeys(false)) {
                    Location loc = pointsConfig.getLocation("points." + uuidStr + "." + pointName);
                    if (loc != null) {
                        playerPoints.put(pointName, loc);
                    }
                }
                travelPoints.put(uuid, playerPoints);
            }
        }
    }

    public void savePoints() {
        if (pointsConfig == null) {
            pointsConfig = new YamlConfiguration();
        }
        // Очищаем предыдущие данные
        pointsConfig.set("points", null);
        for (Map.Entry<UUID, Map<String, Location>> entry : travelPoints.entrySet()) {
            String uuidStr = entry.getKey().toString();
            for (Map.Entry<String, Location> pointEntry : entry.getValue().entrySet()) {
                String pointName = pointEntry.getKey();
                Location loc = pointEntry.getValue();
                pointsConfig.set("points." + uuidStr + "." + pointName, loc);
            }
        }
        try {
            pointsConfig.save(pointsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
