package io.yadnyesh.yadbrewery.service;

import io.yadnyesh.yadbrewery.controller.NotFoundException;
import io.yadnyesh.yadbrewery.mappers.BeerMapper;
import io.yadnyesh.yadbrewery.model.Beer;
import io.yadnyesh.yadbrewery.model.BeerDto;
import io.yadnyesh.yadbrewery.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-06-06.
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	
	@Override
	public BeerDto getById(UUID beerId) {
		return beerMapper.beerToBeerDto(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
		);
	}
	
	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}
	
	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());
		
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}
}
