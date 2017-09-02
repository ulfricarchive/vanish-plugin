package com.ulfric.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.data.database.Data;
import com.ulfric.data.database.Database;
import com.ulfric.data.database.Store;
import com.ulfric.embargo.entity.User;
import com.ulfric.embargo.limit.Limit;
import com.ulfric.servix.ServiceApplication;
import com.ulfric.servix.services.vanish.VanishService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TieredVanish extends ServiceApplication implements VanishService { // TODO thread safety, should we take players out of vanish when the container is disabled?

	private final Set<UUID> vanished = new HashSet<>(); // TODO make vanish persistent

	@Database
	private Store users;

	public TieredVanish() {
		addBootHook(this::loadUserData);
	}

	private void loadUserData() {
		users.getAllData()
			.forEach(this::loadUserData);
	}

	private void loadUserData(Data data) {
		boolean vanished = data.getBoolean("vanished");

		if (vanished) {
			UUID uniqueId = data.getUniqueId("uniqueId");
			this.vanished.add(uniqueId);
		}
	}

	@Override
	public Class<VanishService> getService() {
		return VanishService.class;
	}

	@Override
	public void vanish(UUID uniqueId) {
		if (vanished.add(uniqueId)) {
			writeData(uniqueId, true);
		}

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
			writeData(uniqueId, false);
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

	private void writeData(UUID uniqueId, boolean vanished) {
		Data data = users.getData(uniqueId);
		data.set("uniqueId", uniqueId);
		data.setBoolean("vanished", true);
	}

	@Override
	public boolean isVanished(UUID uniqueId) {
		return vanished.contains(uniqueId);
	}

}