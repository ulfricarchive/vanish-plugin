package com.ulfric.plugin.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.ulfric.commons.permissions.entity.User;
import com.ulfric.commons.permissions.limit.Limit;

import java.util.UUID;

public class VanishListener implements Listener {

	@EventHandler
	public void on(PlayerLoginEvent event) {
		if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
			return;
		}

		VanishService service = VanishService.get();
		if (service == null) {
			return;
		}

		Player loggingIn = event.getPlayer();
		UUID uniqueId = loggingIn.getUniqueId();
		if (service.isVanished(uniqueId)) {
			service.vanish(uniqueId);
		}

		Limit visionLevel = User.getUser(uniqueId).getLimit("vanish");
		for (Player online : Bukkit.getOnlinePlayers()) {
			if (service.isVanished(online.getUniqueId())) {
				Limit vanishLevel = User.getUser(online.getUniqueId()).getLimit("vanish");

				if (visionLevel.isWithinBounds(vanishLevel)) {
					continue;
				}

				online.hidePlayer(loggingIn);
			}
		}
	}

}