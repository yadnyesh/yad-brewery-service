package io.yadnyesh.yadbrewery.controller;

import io.yadnyesh.yadbrewery.model.BeerDto;
import io.yadnyesh.yadbrewery.model.BeerPagedList;
import io.yadnyesh.yadbrewery.model.BeerStyleEnum;
import io.yadnyesh.yadbrewery.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;
	
	
	private final BeerService beerService;
	
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
												   @RequestParam(value = "pageSize", required = false) Integer pageSize,
												   @RequestParam(value = "beerName", required = false) String beerName,
												   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle){
		if(pageNumber == null || pageNumber < 0){
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		
		if(pageSize == null || pageSize < 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
		return new ResponseEntity<>(beerList, HttpStatus.OK);
		
	}
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
	}
}
