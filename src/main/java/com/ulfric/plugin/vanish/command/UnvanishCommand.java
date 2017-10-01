package com.ulfric.vanish.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Command;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.andrew.Sync;
import com.ulfric.commons.naming.Name;

@Sync
@Name("unvanish")
@Alias("unv")
@Permission("vanish.unvanish")
public class UnvanishCommand implements Command {

	@Override
	public void run(Context context) {
		// TODO
	}

}