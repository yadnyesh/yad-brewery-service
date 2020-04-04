package io.yadnyesh.yadbrewery.service;

import io.yadnyesh.yadbrewery.model.BeerDto;

import java.util.UUID;

public interface BeerService {
	BeerDto getById(UUID beerId);
	
	BeerDto saveNewBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}