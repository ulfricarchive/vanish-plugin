package com.ulfric.plugin.vanish.widget;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ulfric.plugin.vanish.PlayerUnvanishEvent;
import com.ulfric.plugin.vanish.PlayerVanishEvent;
import com.ulfric.plugin.widgets.Dashboards;

public class VanishDashboardListener implements Listener {

	@EventHandler
	public void on(PlayerJoinEvent event) {
		Dashboards.getDashboards(event.getPlayer())
			.addWidget(new VanishWidget());
	}

	@EventHandler
	public void on(PlayerVanishEvent event) {
		update(event.getPlayer());
	}

	@EventHandler
	public void on(PlayerUnvanishEvent event) {
		update(event.getPlayer());
	}

	private void update(Player player) {
		Dashboards.getDashboards(player)
			.queueUpdate(VanishWidget.class);
	}

}