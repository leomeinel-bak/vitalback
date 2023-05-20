/*
 * File: BackStorageSql.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.storage;

import dev.meinel.leo.vitalback.storage.mysql.SqlManager;
import dev.meinel.leo.vitalback.utils.sql.Sql;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class BackStorageSql extends BackStorage {

    private static final String SQLEXCEPTION =
            "VitalBack encountered an SQLException while executing task";

    public BackStorageSql() {
        new SqlManager();
    }

    @Override
    public Location loadBack(@NotNull Player player) {
        String playerUUID = player.getUniqueId().toString();
        World world = null;
        int x = 0;
        int y = 0;
        int z = 0;
        int yaw = 0;
        int pitch = 0;
        try (PreparedStatement selectStatement = SqlManager.getConnection()
                .prepareStatement("SELECT * FROM " + Sql.getPrefix() + "Back")) {
            try (ResultSet rs = selectStatement.executeQuery()) {
                while (rs.next()) {
                    if (!Objects.equals(rs.getString(1), playerUUID) || rs.getString(1) == null
                            || rs.getString(2) == null) {
                        continue;
                    }
                    world = Bukkit.getWorld(Objects.requireNonNull(rs.getString(2)));
                    x = rs.getInt(3);
                    y = rs.getInt(4);
                    z = rs.getInt(5);
                    yaw = rs.getInt(6);
                    pitch = rs.getInt(7);
                }
            }
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
            return null;
        }
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public void saveBack(@NotNull Player player) {
        String playerUUID = player.getUniqueId().toString();
        Location location = player.getLocation();
        clear(playerUUID);
        try (PreparedStatement insertStatement =
                SqlManager.getConnection().prepareStatement("INSERT INTO " + Sql.getPrefix()
                        + "Back (`UUID`, `World`, `X`, `Y`, `Z`, `Yaw`, `Pitch`) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            insertStatement.setString(1, playerUUID);
            insertStatement.setString(2, location.getWorld().getName());
            insertStatement.setInt(3, (int) location.getX());
            insertStatement.setInt(4, (int) location.getY());
            insertStatement.setInt(5, (int) location.getZ());
            insertStatement.setInt(6, (int) location.getYaw());
            insertStatement.setInt(7, (int) location.getPitch());
            insertStatement.executeUpdate();
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
        }
    }

    @Override
    protected void clear(@NotNull String playerUUID) {
        try (PreparedStatement deleteStatement = SqlManager.getConnection()
                .prepareStatement("DELETE FROM " + Sql.getPrefix() + "Back WHERE `UUID`=?")) {
            deleteStatement.setString(1, playerUUID);
            deleteStatement.executeUpdate();
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
        }
    }
}
