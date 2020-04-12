package io.yadnyesh.yadbrewery.service;

import io.yadnyesh.yadbrewery.controller.NotFoundException;
import io.yadnyesh.yadbrewery.mappers.BeerMapper;
import io.yadnyesh.yadbrewery.model.Beer;
import io.yadnyesh.yadbrewery.model.BeerDto;
import io.yadnyesh.yadbrewery.model.BeerPagedList;
import io.yadnyesh.yadbrewery.model.BeerStyleEnum;
import io.yadnyesh.yadbrewery.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-06-06.
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	
	@Override
	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
		
		BeerPagedList beerPagedList;
		Page<Beer> beerPage;
		
		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}
		
		if(showInventoryOnHand) {
			beerPagedList = new BeerPagedList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDtoWithInventory)
					.collect(Collectors.toList()),
					PageRequest
							.of(beerPage.getPageable().getPageNumber(),
									beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
			
			return beerPagedList;
		} else {
			beerPagedList = new BeerPagedList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDto)
					.collect(Collectors.toList()),
					PageRequest
							.of(beerPage.getPageable().getPageNumber(),
									beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
			
			return beerPagedList;
		}
	}
	
	@Override
	@Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
		if(showInventoryOnHand) {
			return beerMapper.beerToBeerDtoWithInventory(
					beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
			);
		} else {
			return beerMapper.beerToBeerDto(
					beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
			);
		}

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
