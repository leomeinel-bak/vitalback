/*
 * VitalBack is a Spigot Plugin that gives players the ability to teleport back to their last location.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalBack/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalback.storage;

import com.tamrielnetwork.vitalback.storage.mysql.SqlManager;
import com.tamrielnetwork.vitalback.utils.sql.Sql;
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

	public BackStorageSql() {

		new SqlManager();
	}

	@Override
	public Location loadBack(@NotNull Player player) {

		String playerUUID = player.getUniqueId().toString();

		World world = null;
		int x = 0, y = 0, z = 0, yaw = 0, pitch = 0;

		try (PreparedStatement selectStatement = SqlManager.getConnection().prepareStatement("SELECT * FROM " + Sql.getPrefix() + "Back")) {
			try (ResultSet rs = selectStatement.executeQuery()) {
				while (rs.next()) {
					if (!Objects.equals(rs.getString(1), playerUUID)) {
						continue;
					}
					if (rs.getString(1) == null) {
						continue;
					}
					if (rs.getString(2) == null) {
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
		} catch (SQLException throwables) {

			throwables.printStackTrace();
			return null;
		}
		return new Location(world, x, y, z, yaw, pitch);
	}

	@Override
	public void saveBack(@NotNull Player player) {

		String playerUUID = player.getUniqueId().toString();
		Location location = player.getLocation();

		clear(playerUUID);

		try (PreparedStatement insertStatement = SqlManager.getConnection().prepareStatement("INSERT INTO " + Sql.getPrefix() + "Back (`UUID`, `World`, `X`, `Y`, `Z`, `Yaw`, `Pitch`) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			insertStatement.setString(1, playerUUID);
			insertStatement.setString(2, location.getWorld().getName());
			insertStatement.setInt(3, (int) location.getX());
			insertStatement.setInt(4, (int) location.getY());
			insertStatement.setInt(5, (int) location.getZ());
			insertStatement.setInt(6, (int) location.getYaw());
			insertStatement.setInt(7, (int) location.getPitch());
			insertStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void clear(@NotNull String playerUUID) {

		try (PreparedStatement deleteStatement = SqlManager.getConnection().prepareStatement("DELETE FROM " + Sql.getPrefix() + "Back WHERE `UUID`=" + "'" + playerUUID + "'")) {
			deleteStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}
