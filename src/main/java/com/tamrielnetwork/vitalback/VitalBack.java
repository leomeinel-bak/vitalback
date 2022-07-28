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

package com.tamrielnetwork.vitalback;

import com.tamrielnetwork.vitalback.commands.VitalBackCmd;
import com.tamrielnetwork.vitalback.files.Messages;
import com.tamrielnetwork.vitalback.listeners.PlayerDeath;
import com.tamrielnetwork.vitalback.listeners.PlayerTeleport;
import com.tamrielnetwork.vitalback.storage.BackStorage;
import com.tamrielnetwork.vitalback.storage.BackStorageSql;
import com.tamrielnetwork.vitalback.storage.BackStorageYaml;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VitalBack
		extends JavaPlugin {

	private BackStorage backStorage;
	private Messages messages;

	@Override
	public void onEnable() {
		registerListeners();
		Objects.requireNonNull(getCommand("back"))
		       .setExecutor(new VitalBackCmd());
		saveDefaultConfig();
		setupStorage();
		messages = new Messages();
		Bukkit.getLogger()
		      .info("VitalBack v" + this.getDescription()
		                                .getVersion() + " enabled");
		Bukkit.getLogger()
		      .info("Copyright (C) 2022 Leopold Meinel");
		Bukkit.getLogger()
		      .info("This program comes with ABSOLUTELY NO WARRANTY!");
		Bukkit.getLogger()
		      .info("This is free software, and you are welcome to redistribute it under certain conditions.");
		Bukkit.getLogger()
		      .info("See https://github.com/LeoMeinel/VitalBack/blob/main/LICENSE for more details.");
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger()
		      .info("VitalBack v" + this.getDescription()
		                                .getVersion() + " disabled");
	}

	private void setupStorage() {
		String storageSystem = getConfig().getString("storage-system");
		if (Objects.requireNonNull(storageSystem)
		           .equalsIgnoreCase("mysql")) {
			this.backStorage = new BackStorageSql();
		}
		else {
			this.backStorage = new BackStorageYaml();
		}
	}

	private void registerListeners() {
		getServer().getPluginManager()
		           .registerEvents(new PlayerDeath(), this);
		getServer().getPluginManager()
		           .registerEvents(new PlayerTeleport(), this);
	}

	public Messages getMessages() {
		return messages;
	}

	public BackStorage getSpawnStorage() {
		return backStorage;
	}
}

