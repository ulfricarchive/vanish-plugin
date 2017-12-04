package com.ulfric.plugin.vanish.command;

import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.vanish.VanishService;

@Name("vanish")
@Alias("v")
@Permission("vanish.vanish")
public class VanishCommand extends Command {

	@Override
	public void run() {
		VanishService vanish = VanishService.get();
		UUID uniqueId = uniqueId();

		if (vanish.isVanished(uniqueId)) {
			tell("vanish-already-vanished");
			return;
		}

		vanish.vanish(uniqueId());

		tell(vanish.isVanished(uniqueId) ? "vanish" : "vanish-failed");
	}

}