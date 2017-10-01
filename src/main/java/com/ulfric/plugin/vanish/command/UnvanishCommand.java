package com.ulfric.plugin.vanish.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.Permission;
import com.ulfric.plugin.commands.Sync;

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