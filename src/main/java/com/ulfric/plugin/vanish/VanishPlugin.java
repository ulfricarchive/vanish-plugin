package com.ulfric.plugin.vanish;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.vanish.command.UnvanishCommand;
import com.ulfric.plugin.vanish.command.VanishCommand;
import com.ulfric.plugin.vanish.widget.VanishScoreboardListener;

public class VanishPlugin extends Plugin {

	public VanishPlugin() {
		install(TieredVanish.class);

		install(VanishListener.class);
		install(VanishScoreboardListener.class);

		install(VanishCommand.class);
		install(UnvanishCommand.class);
	}

}