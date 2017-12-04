package com.ulfric.plugin.vanish;

import java.util.UUID;

import com.ulfric.plugin.services.Service;

public interface VanishService extends Service<VanishService> {

	static VanishService get() {
		return Service.get(VanishService.class);
	}

	void vanish(UUID uniqueId);

	void unvanish(UUID uniqueId);

	boolean isVanished(UUID uniqueId);

}