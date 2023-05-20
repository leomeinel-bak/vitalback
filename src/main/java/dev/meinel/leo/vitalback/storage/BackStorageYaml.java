/*
 * File: BackStorageYaml.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class BackStorageYaml extends BackStorage {

    private static final String IOEXCEPTION =
            "VitalBack encountered an IOException while executing task";
    private static final String BACK = "back.";
    private static final String WORLD = ".world";
    private final File backFile;
    private final FileConfiguration backConf;

    public BackStorageYaml() {
        backFile = new File(main.getDataFolder(), "back.yml");
        backConf = YamlConfiguration.loadConfiguration(backFile);
        save();
    }

    @Override
    public Location loadBack(@NotNull Player player) {
        String playerUUID = player.getUniqueId().toString();
        if (backConf.getString(BACK + playerUUID + WORLD) == null) {
            return null;
        }
        World world = Bukkit
                .getWorld(Objects.requireNonNull(backConf.getString(BACK + playerUUID + WORLD)));
        int x = backConf.getInt(BACK + playerUUID + ".x");
        int y = backConf.getInt(BACK + playerUUID + ".y");
        int z = backConf.getInt(BACK + playerUUID + ".z");
        int yaw = backConf.getInt(BACK + playerUUID + ".yaw");
        int pitch = backConf.getInt(BACK + playerUUID + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public void saveBack(@NotNull Player player) {
        String playerUUID = player.getUniqueId().toString();
        Location location = player.getLocation();
        clear(playerUUID);
        backConf.set(BACK + playerUUID + WORLD, location.getWorld().getName());
        backConf.set(BACK + playerUUID + ".x", (int) location.getX());
        backConf.set(BACK + playerUUID + ".y", (int) location.getY());
        backConf.set(BACK + playerUUID + ".z", (int) location.getZ());
        backConf.set(BACK + playerUUID + ".yaw", (int) location.getYaw());
        backConf.set(BACK + playerUUID + ".pitch", (int) location.getPitch());
        save();
    }

    @Override
    protected void clear(@NotNull String playerUUID) {
        if (backConf.getConfigurationSection("back") == null) {
            return;
        }
        for (String key : Objects.requireNonNull(backConf.getConfigurationSection("back"))
                .getKeys(false)) {
            if (Objects.equals(key, playerUUID)) {
                backConf.set(BACK + key, null);
            }
        }
    }

    private void save() {
        try {
            backConf.save(backFile);
        } catch (IOException ignored) {
            Bukkit.getLogger().info(IOEXCEPTION);
        }
    }
}
