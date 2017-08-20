package com.ulfric.vanish.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.commons.naming.Name;
import com.ulfric.i18n.content.Details;
import com.ulfric.is.IsCommand;
import com.ulfric.servix.services.vanish.VanishService;

@Name("vanished")
@Alias({"vanish", "van", "v"})
@Permission("vanish.is")
public class IsVanishedCommand extends IsCommand {

	@Override
	public void run(Context context) {
		Details details = details();
		details.add("vanished", Boolean.toString(VanishService.get().isVanished(target.getUniqueId()))); // TODO handle service missing
		context.getSender().sendMessage("vanish-is", details);
	}

}