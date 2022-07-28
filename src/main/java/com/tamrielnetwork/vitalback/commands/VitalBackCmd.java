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

package com.tamrielnetwork.vitalback.commands;

import com.tamrielnetwork.vitalback.VitalBack;
import com.tamrielnetwork.vitalback.utils.Chat;
import com.tamrielnetwork.vitalback.utils.commands.Cmd;
import com.tamrielnetwork.vitalback.utils.commands.CmdSpec;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VitalBackCmd
		implements CommandExecutor {

	private final VitalBack main = JavaPlugin.getPlugin(VitalBack.class);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	                         @NotNull String[] args) {
		if (Cmd.isArgsLengthNotEqualTo(sender, args, 0)) {
			return false;
		}
		doBack(sender);
		return true;
	}

	private void doBack(@NotNull CommandSender sender) {
		if (CmdSpec.isInvalidCmd(sender, "vitalback.back")) {
			return;
		}
		Player senderPlayer = (Player) sender;
		Location location = main.getSpawnStorage()
		                        .loadBack(senderPlayer);
		if (location == null) {
			Chat.sendMessage(sender, "no-back");
			return;
		}
		CmdSpec.doDelay(sender, location);
	}
}