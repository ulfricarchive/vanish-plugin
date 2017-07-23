package com.ulfric.vanish;

import com.ulfric.dragoon.application.Container;
import com.ulfric.vanish.command.UnvanishCommand;
import com.ulfric.vanish.command.VanishCommand;

public class VanishContainer extends Container {

	public VanishContainer() {
		install(TieredVanish.class);

		install(VanishListener.class);

		install(VanishCommand.class);
		install(UnvanishCommand.class);
	}

}