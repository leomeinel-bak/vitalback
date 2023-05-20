/*
 * File: PlayerDeath.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.listeners;

import dev.meinel.leo.vitalback.VitalBack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDeath implements Listener {

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
        main.getSpawnStorage().saveBack(player);
    }
}
