/*
 * File: BackStorage.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalback.storage;

import dev.meinel.leo.vitalback.VitalBack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class BackStorage {

    protected final VitalBack main = JavaPlugin.getPlugin(VitalBack.class);

    public abstract Location loadBack(@NotNull Player player);

    public abstract void saveBack(@NotNull Player player);

    protected abstract void clear(@NotNull String playerUUID);
}
