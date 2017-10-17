package com.ulfric.plugin.vanish;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.ulfric.commons.permissions.limit.Limit;
import com.ulfric.dragoon.extension.inject.Inject;

public class VanishListener implements Listener {

	@Inject
	private UserLookup lookup;

	@Inject
	private VanishService vanish;

	@EventHandler
	public void on(AsyncPlayerPreLoginEvent event) {
		if (event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
			return;
		}

		pretouchLimit(event.getUniqueId());
	}

	private void pretouchLimit(UUID uniqueId) {
		lookupLimit(uniqueId);
	}

	@VanishSLA
	@EventHandler
	public void on(PlayerLoginEvent event) {
		if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
			return;
		}

		Player loggingIn = event.getPlayer();
		UUID uniqueId = loggingIn.getUniqueId();
		if (vanish.isVanished(uniqueId)) {
			vanish.vanish(uniqueId);
		}

		Limit visionLevel = lookupLimit(uniqueId);
		for (Player online : Bukkit.getOnlinePlayers()) {
			UUID onlineUniqueId = online.getUniqueId();
			if (vanish.isVanished(onlineUniqueId)) {
				Limit vanishLevel = lookupLimit(onlineUniqueId);

				if (visionLevel.isWithinBounds(vanishLevel)) {
					continue;
				}

				online.hidePlayer(loggingIn);
			}
		}
	}

	private Limit lookupLimit(UUID uniqueId) {
		return lookup.lookupUser(uniqueId).getLimit("vanish");
	}

}