package com.ulfric.vanish;

import com.ulfric.platform.Plugin;
import com.ulfric.vanish.command.UnvanishCommand;
import com.ulfric.vanish.command.VanishCommand;
import com.ulfric.vanish.scoreboard.VanishScoreboardListener;

public class Vanish extends Plugin {

	public Vanish() {
		install(TieredVanish.class);

		install(VanishListener.class);
		install(VanishScoreboardListener.class);

		install(VanishCommand.class);
		install(UnvanishCommand.class);
	}

}