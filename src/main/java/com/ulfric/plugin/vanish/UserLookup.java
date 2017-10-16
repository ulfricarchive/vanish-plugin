package com.ulfric.plugin.vanish;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.permissions.PermissionsService;
import com.ulfric.plugin.permissions.User;

public class UserLookup {

	@Inject
	private PermissionsService permissions;

	public User lookupUser(UUID uniqueId) {
		return FutureHelper.getUnchecked(permissions.getUserByUniqueId(uniqueId), 5, TimeUnit.MILLISECONDS);
	}

}
