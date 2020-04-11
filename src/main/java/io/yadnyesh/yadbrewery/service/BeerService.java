package io.yadnyesh.yadbrewery.service;

import io.yadnyesh.yadbrewery.model.BeerDto;
import io.yadnyesh.yadbrewery.model.BeerPagedList;
import io.yadnyesh.yadbrewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);
	
	BeerDto getById(UUID beerId);
	
	BeerDto saveNewBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}