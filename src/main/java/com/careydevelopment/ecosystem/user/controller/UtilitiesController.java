package com.careydevelopment.ecosystem.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.ecosystem.user.model.Country;
import com.careydevelopment.ecosystem.user.model.State;
import com.careydevelopment.ecosystem.user.repository.CountryRepository;
import com.careydevelopment.ecosystem.user.repository.StateRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utilities")
public class UtilitiesController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilitiesController.class);

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryRepository countryRepository;
	
	
	@GetMapping("/states")
	public List<State> fetchAllStates() {
		LOG.debug("Fetching all states");
		return stateRepository.findByOrderByNameAsc();
	}
	
	
	@GetMapping("/countries")
	public List<Country> fetchAllCountries() {
		LOG.debug("Fetching all countries");
		return countryRepository.findByOrderByNameAsc();
	}
}
