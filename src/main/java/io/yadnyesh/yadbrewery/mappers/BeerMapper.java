package io.yadnyesh.yadbrewery.mappers;

import io.yadnyesh.yadbrewery.model.Beer;
import io.yadnyesh.yadbrewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
	BeerDto beerToBeerDto(Beer beer);
	Beer beerDtoToBeer(BeerDto dto);
	
	BeerDto beerToBeerDtoWithInventory(Beer beer);
}
