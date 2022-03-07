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

package com.tamrielnetwork.vitalback.listeners;

import com.tamrielnetwork.vitalback.VitalBack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerTeleport implements Listener {

	private final VitalBack main = JavaPlugin.getPlugin(VitalBack.class);

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {

		Player player = event.getPlayer();

		if (!player.hasPermission("vitalback.back")) {
			return;
		}

		if (event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
			return;
		}

		main.getSpawnStorage().saveBack(player);

	}

}
