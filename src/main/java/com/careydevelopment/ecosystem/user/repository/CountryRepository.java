package com.careydevelopment.ecosystem.user.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.careydevelopment.ecosystem.user.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

	@Cacheable("countries")
	List<Country> findByOrderByNameAsc();
}
