/*
 * File: VitalBackCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.commands;

import dev.meinel.leo.vitalback.VitalBack;
import dev.meinel.leo.vitalback.utils.Chat;
import dev.meinel.leo.vitalback.utils.commands.Cmd;
import dev.meinel.leo.vitalback.utils.commands.CmdSpec;
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