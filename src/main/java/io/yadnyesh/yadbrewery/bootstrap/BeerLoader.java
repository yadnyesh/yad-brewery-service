package io.yadnyesh.yadbrewery.bootstrap;

import io.yadnyesh.yadbrewery.model.Beer;
import io.yadnyesh.yadbrewery.model.BeerStyleEnum;
import io.yadnyesh.yadbrewery.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {
	
	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "0083783375213";
	
	private final BeerRepository beerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(beerRepository.count() == 0 ) {
			loadBeerObjects();
		}
	}
	
	private void loadBeerObjects() {
		Beer b1 = Beer.builder()
				.beerName("Mango Bobs")
				.beerStyle(BeerStyleEnum.IPA.name())
				.minOnHand(12)
				.quantityToBrew(200)
				.price(new BigDecimal("12.95"))
				.upc(631234200036L)
				.build();
		
		Beer b2 = Beer.builder()
				.beerName("Galaxy Cat")
				.beerStyle(BeerStyleEnum.PALE_ALE.name())
				.minOnHand(12)
				.quantityToBrew(200)
				.price(new BigDecimal("12.95"))
				.upc(631234300019L)
				.build();
		
		Beer b3 = Beer.builder()
				.beerName("Pinball Porter")
				.beerStyle(BeerStyleEnum.PALE_ALE.name())
				.minOnHand(12)
				.quantityToBrew(200)
				.price(new BigDecimal("12.95"))
				.upc(83783375213L)
				.build();
		
		beerRepository.save(b1);
		beerRepository.save(b2);
		beerRepository.save(b3);
	}
}
