/*
 * File: VitalBack.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback;

import dev.meinel.leo.vitalback.commands.VitalBackCmd;
import dev.meinel.leo.vitalback.files.Messages;
import dev.meinel.leo.vitalback.listeners.PlayerDeath;
import dev.meinel.leo.vitalback.listeners.PlayerTeleport;
import dev.meinel.leo.vitalback.storage.BackStorage;
import dev.meinel.leo.vitalback.storage.BackStorageSql;
import dev.meinel.leo.vitalback.storage.BackStorageYaml;
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
		} else {
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
