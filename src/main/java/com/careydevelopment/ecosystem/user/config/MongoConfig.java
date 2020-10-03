package com.careydevelopment.ecosystem.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages= {"com.careydevelopment.ecosystem.user.repository"})
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongo.connection.string}")
    private String mongoConnection;

    
    @Value("${mongo.db.name}")
    private String mongoDatabaseName;
    
    
    @Override
    protected String getDatabaseName() {
        return mongoDatabaseName;
    }
  
    
    @Override
    @Bean
    public MongoClient mongoClient() {
        String fullConnectionString = mongoConnection + "/" + mongoDatabaseName;
        
    	MongoClient client = MongoClients.create(fullConnectionString);
        return client;
    }
}
