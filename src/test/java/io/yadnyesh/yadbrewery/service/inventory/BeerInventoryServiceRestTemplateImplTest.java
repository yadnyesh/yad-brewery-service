package io.yadnyesh.yadbrewery.service.inventory;

import io.yadnyesh.yadbrewery.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled // utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {
	
	@Autowired
	BeerInventoryService beerInventoryService;
	
	@BeforeEach
	void setUp() {
	
	}
	
	@Test
	void getOnhandInventory() {
//		Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UPC);
//		System.out.println(qoh);
		
	}
}