package com.ulfric.vanish.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Command;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.andrew.Sync;
import com.ulfric.commons.naming.Name;

@Sync
@Name("vanish")
@Alias("v")
@Permission("vanish.vanish")
public class VanishCommand implements Command {

	@Override
	public void run(Context context) {
		// TODO
	}

}