package io.yadnyesh.yadbrewery.service.inventory;

import java.util.UUID;

public interface BeerInventoryService {
	Integer getOnhandInventory(UUID beerId);
}
