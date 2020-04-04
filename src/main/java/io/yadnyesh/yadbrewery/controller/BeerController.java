package io.yadnyesh.yadbrewery.controller;

import io.yadnyesh.yadbrewery.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerID) {
		return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDTO) {
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerID, @Validated @RequestBody BeerDto beerDTO) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
