package com.ulfric.vanish.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.commons.naming.Name;
import com.ulfric.is.IsCommand;
import com.ulfric.servix.services.vanish.VanishService;

import java.util.Map;

@Name("vanished")
@Alias({"vanish", "van", "v"})
@Permission("vanish.is")
public class IsVanishedCommand extends IsCommand {

	@Override
	public void run(Context context) {
		Map<String, String> details = details();
		details.put("vanished", Boolean.toString(VanishService.get().isVanished(target.getUniqueId()))); // TODO handle service missing
		context.getSender().sendMessage("vanish-is", details);
	}

}