package com.careydevelopment.ecosystem.user.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.careydevelopment.ecosystem.user.model.State;

public interface StateRepository extends MongoRepository<State, String> {
	
	@Cacheable("states")
	List<State> findByOrderByNameAsc();
}
