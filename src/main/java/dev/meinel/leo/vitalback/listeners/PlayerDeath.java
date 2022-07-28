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
 * along with this program. If not, see https://github.com/LeoMeinel/VitalBack/blob/main/LICENSE
 */

package dev.meinel.leo.vitalback.listeners;

import dev.meinel.leo.vitalback.VitalBack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDeath
		implements Listener {

	private final VitalBack main = JavaPlugin.getPlugin(VitalBack.class);

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("vitalback.back")) {
			return;
		}
		if (!player.hasPermission("vitalback.back.ondeath")) {
			return;
		}
		main.getSpawnStorage()
		    .saveBack(player);
	}
}
