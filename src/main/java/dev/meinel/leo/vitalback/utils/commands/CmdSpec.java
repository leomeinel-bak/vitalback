/*
 * File: CmdSpec.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.utils.commands;

import dev.meinel.leo.vitalback.VitalBack;
import dev.meinel.leo.vitalback.utils.Chat;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CmdSpec {

    private static final VitalBack main = JavaPlugin.getPlugin(VitalBack.class);
    private static final List<UUID> onActiveDelay = new ArrayList<>();

    private CmdSpec() {
        throw new IllegalStateException("Utility class");
    }

    public static void doDelay(CommandSender sender, Location location) {
        Player senderPlayer = (Player) sender;
        if (!sender.hasPermission("vitalspawn.delay.bypass")) {
            if (onActiveDelay.contains(senderPlayer.getUniqueId())) {
                Chat.sendMessage(sender, "active-delay");
                return;
            }
            onActiveDelay.add(senderPlayer.getUniqueId());
            String timeRemaining = String.valueOf(main.getConfig()
                    .getLong("delay.time"));
            Chat.sendMessage(sender, Map.of("%countdown%", timeRemaining), "countdown");
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (Cmd.isInvalidPlayer(senderPlayer)) {
                        onActiveDelay.remove(senderPlayer.getUniqueId());
                        return;
                    }
                    senderPlayer.teleport(location);
                    onActiveDelay.remove(senderPlayer.getUniqueId());
                }
            }.runTaskLater(main, (main.getConfig()
                    .getLong("delay.time") * 20L));
        } else {
            senderPlayer.teleport(location);
        }
    }

    public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {
        return Cmd.isInvalidSender(sender) || Cmd.isNotPermitted(sender, perm);
    }
}
