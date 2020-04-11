package io.yadnyesh.yadbrewery.repository;

import io.yadnyesh.yadbrewery.model.Beer;
import io.yadnyesh.yadbrewery.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
	
	Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
	
	Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
	
	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
	
}
