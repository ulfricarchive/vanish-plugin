package com.ulfric.plugin.vanish;

import com.ulfric.plugin.services.Service;

import java.util.UUID;

public interface VanishService extends Service<VanishService> {

	static VanishService get() {
		return Service.get(VanishService.class);
	}

	void vanish(UUID uniqueId);

	void unvanish(UUID uniqueId);

	boolean isVanished(UUID uniqueId);

}