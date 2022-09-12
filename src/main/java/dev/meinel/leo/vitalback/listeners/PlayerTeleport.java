/*
 * File: PlayerTeleport.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.listeners;

import dev.meinel.leo.vitalback.VitalBack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerTeleport
		implements Listener {

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
		main.getSpawnStorage()
				.saveBack(player);
	}
}
