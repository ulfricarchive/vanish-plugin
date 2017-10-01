package com.ulfric.plugin.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.permissions.entity.User;
import com.ulfric.commons.permissions.limit.Limit;
import com.ulfric.plugin.services.ServiceApplication;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TieredVanish extends ServiceApplication implements VanishService { // TODO thread safety, should we take players out of vanish when the container is disabled?

	private final Set<UUID> vanished = new HashSet<>(); // TODO make vanish persistent

	@Override
	public Class<VanishService> getService() {
		return VanishService.class;
	}

	@Override
	public void vanish(UUID uniqueId) {
		vanished.add(uniqueId);

		Player vanisher = Bukkit.getPlayer(uniqueId);
		User vanishingUser = User.getUser(uniqueId);
		Limit vanishLevel = vanishingUser.getLimit("vanish");
		for (Player online : Bukkit.getOnlinePlayers()) {
			if (online == vanisher) {
				continue;
			}

			User onlineUser = User.getUser(online.getUniqueId());
			Limit visionLevel = onlineUser.getLimit("vanish");

			if (vanishLevel == visionLevel) {
				continue;
			}

			if (visionLevel.isWithinBounds(vanishLevel)) {
				continue;
			}

			online.hidePlayer(vanisher);
		}
	}

	@Override
	public void unvanish(UUID uniqueId) {
		if (vanished.remove(uniqueId)) {
			Player vanisher = Bukkit.getPlayer(uniqueId);

			if (vanisher == null) {
				return;
			}

			for (Player online : Bukkit.getOnlinePlayers()) {
				if (online == vanisher) {
					continue;
				}

				online.showPlayer(vanisher);
			}
		}
	}

	@Override
	public boolean isVanished(UUID uniqueId) {
		return vanished.contains(uniqueId);
	}

}