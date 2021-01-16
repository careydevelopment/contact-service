package com.careydevelopment.contact.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.careydevelopment.contact.model.Contact;
import com.careydevelopment.contact.model.Source;

@Service
public class ContactService {

    private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
	
    
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public List<Contact> findContactsBySource(Source source) {
		AggregationOperation match = Aggregation.match(Criteria.where("source").is(source));
		AggregationOperation sort = Aggregation.sort(Direction.ASC, "lastName"); 
		AggregationOperation project = Aggregation.project("firstName", "lastName", "source").andExclude("_id");
		
		Aggregation aggregation = Aggregation.newAggregation(match, sort, project);
		
		List<Contact> contacts = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Contact.class), Contact.class).getMappedResults();
		
		return contacts;
	}
}
