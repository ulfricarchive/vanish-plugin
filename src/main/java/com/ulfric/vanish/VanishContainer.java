package com.ulfric.vanish;

import com.ulfric.dragoon.application.Container;
import com.ulfric.vanish.command.UnvanishCommand;
import com.ulfric.vanish.command.VanishCommand;
import com.ulfric.vanish.scoreboard.VanishScoreboardListener;

public class VanishContainer extends Container {

	public VanishContainer() {
		install(TieredVanish.class);

		install(VanishListener.class);
		install(VanishScoreboardListener.class);

		install(VanishCommand.class);
		install(UnvanishCommand.class);
	}

}