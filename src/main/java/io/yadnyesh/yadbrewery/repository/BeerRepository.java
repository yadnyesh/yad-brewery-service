package io.yadnyesh.yadbrewery.repository;

import io.yadnyesh.yadbrewery.model.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
