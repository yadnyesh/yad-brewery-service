package io.yadnyesh.yadbrewery.mappers;

import io.yadnyesh.yadbrewery.model.Beer;
import io.yadnyesh.yadbrewery.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	BeerDto BeerToBeerDto(Beer beer);
	Beer BeerDtoToBeer(BeerDto dto);
}
