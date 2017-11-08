package com.ulfric.plugin.vanish.widget;

import org.bukkit.entity.Player;

import com.ulfric.fancymessage.Message;
import com.ulfric.plugin.locale.LocaleService;
import com.ulfric.plugin.vanish.VanishService;
import com.ulfric.plugin.widgets.StandardDashboardType;
import com.ulfric.plugin.widgets.Widget;
import com.ulfric.plugin.widgets.text.Text;

import java.util.Collections;

public class VanishWidget extends Widget {

	public VanishWidget() {
		super(StandardDashboardType.SCOREBOARD);
	}

	@Override
	public Text apply(Player player) {
		VanishService vanish = VanishService.get();

		if (vanish == null) {
			return null;
		}

		if (!vanish.isVanished(player.getUniqueId())) {
			return null;
		}

		String message = Message.toLegacy(LocaleService.getMessage(player, "vanish-widget"));
		Text text = new Text();
		text.setBody(Collections.singletonList(message));
		return text;
	}

}
