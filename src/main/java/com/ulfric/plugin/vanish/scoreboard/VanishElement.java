package com.ulfric.plugin.vanish.scoreboard;

import org.bukkit.entity.Player;

import com.ulfric.fancymessage.Message;
import com.ulfric.monty.element.Element;
import com.ulfric.monty.text.Text;
import com.ulfric.plugin.locale.LocaleService;
import com.ulfric.plugin.vanish.VanishService;

import java.util.Collections;

public class VanishElement extends Element {

	@Override
	public Text apply(Player player) {
		VanishService vanish = VanishService.get();

		if (vanish == null) {
			return null;
		}

		if (!vanish.isVanished(player.getUniqueId())) {
			return null;
		}

		String message = Message.toLegacy(LocaleService.getMessage(player, "vanish-scoreboard"));
		Text text = new Text();
		text.setBody(Collections.singletonList(message));
		return text;
	}

}
