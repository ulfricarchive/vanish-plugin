package com.ulfric.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.embargo.entity.User;
import com.ulfric.embargo.limit.Limit;
import com.ulfric.servix.services.vanish.VanishService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TieredVanish implements VanishService { // TODO thread safety, should we take players out of vanish when the container is disabled?

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

				online.showPlayer(vanisher); // TODO handle vanishing from other plugins
			}
		}
	}

	@Override
	public boolean isVanished(UUID uniqueId) {
		return vanished.contains(uniqueId);
	}

}