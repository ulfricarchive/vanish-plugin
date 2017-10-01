package com.ulfric.plugin.vanish.scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ulfric.monty.Scoreboard;
import com.ulfric.plugin.vanish.PlayerUnvanishEvent;
import com.ulfric.plugin.vanish.PlayerVanishEvent;

public class VanishScoreboardListener implements Listener {

	@EventHandler
	public void on(PlayerJoinEvent event) {
		Scoreboard.getScoreboard(event.getPlayer())
			.ifPresent(scoreboard -> scoreboard.add(new VanishElement()));
	}

	@EventHandler
	public void on(PlayerVanishEvent event) {
		Scoreboard.getScoreboard(event.getPlayer())
			.ifPresent(scoreboard -> scoreboard.queueUpdate(VanishElement.class)); // TODO handle scenario where vanish element is not present
	}

	@EventHandler
	public void on(PlayerUnvanishEvent event) {
		Scoreboard.getScoreboard(event.getPlayer())
			.ifPresent(scoreboard -> scoreboard.queueUpdate(VanishElement.class));
	}

}