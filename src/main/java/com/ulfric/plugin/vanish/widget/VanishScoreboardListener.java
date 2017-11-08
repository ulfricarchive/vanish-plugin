package com.ulfric.plugin.vanish.widget;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ulfric.plugin.vanish.PlayerUnvanishEvent;
import com.ulfric.plugin.vanish.PlayerVanishEvent;
import com.ulfric.plugin.widgets.scoreboard.Scoreboard;

public class VanishScoreboardListener implements Listener { // TODO support scoreboard/tab agnostic dashboards

	@EventHandler
	public void on(PlayerJoinEvent event) {
		Scoreboard.getExistingScoreboard(event.getPlayer())
			.ifPresent(scoreboard -> scoreboard.add(new VanishWidget()));
	}

	@EventHandler
	public void on(PlayerVanishEvent event) {
		Scoreboard.getExistingScoreboard(event.getPlayer())
			.ifPresent(scoreboard -> scoreboard.queueUpdate(VanishWidget.class)); // TODO handle scenario where vanish element is not present
	}

	@EventHandler
	public void on(PlayerUnvanishEvent event) {
		Scoreboard.getExistingScoreboard(event.getPlayer())
			.ifPresent(scoreboard -> scoreboard.queueUpdate(VanishWidget.class));
	}

}