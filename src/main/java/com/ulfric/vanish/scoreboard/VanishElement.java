package com.ulfric.vanish.scoreboard;

import org.bukkit.entity.Player;

import com.ulfric.monty.Element;
import com.ulfric.monty.Text;
import com.ulfric.servix.services.locale.LocaleService;
import com.ulfric.servix.services.vanish.VanishService;

import java.util.Collections;

public class VanishElement extends Element {

	@Override
	public Text apply(Player player) {
		VanishService vanish = VanishService.get();

		if (vanish == null) {
			return null;
		}

		String message = LocaleService.getMessage(player, "vanish-scoreboard");
		if (message == null) {
			return null;
		}

		Text text = new Text();
		text.setBody(Collections.singletonList(message));
		return text;
	}

}
