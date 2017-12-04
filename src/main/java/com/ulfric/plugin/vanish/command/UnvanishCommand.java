package com.ulfric.plugin.vanish.command;

import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.vanish.VanishService;

@Name("unvanish")
@Alias("unv")
@Permission("vanish.unvanish")
public class UnvanishCommand extends Command {

	@Override
	public void run() {
		VanishService vanish = VanishService.get();
		UUID uniqueId = uniqueId();

		if (!vanish.isVanished(uniqueId)) {
			tell("unvanish-not-vanished");
			return;
		}

		vanish.unvanish(uniqueId());

		tell(vanish.isVanished(uniqueId) ? "unvanish-failed" : "unvanish");
	}

}