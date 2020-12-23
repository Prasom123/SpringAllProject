package com.heraizen.cj.ipl.util;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.security.acl.Group;
import java.util.Arrays;

import org.bson.BsonNull;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.heraizen.cj.ipl.domain.Team;
import com.heraizen.cj.ipl.dto.TeamDTO;
import com.heraizen.cj.ipl.dto.TeamLabelsDTO;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Projections;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;

import lombok.Cleanup;

public class IplServiceDatabaseConnection {
	
	private static IplServiceDatabaseConnection iplServiceDatabaseConnection=null;
	private IplServiceDatabaseConnection() {

	}
    public static IplServiceDatabaseConnection getInstance() {
    	if(iplServiceDatabaseConnection==null) {
    		synchronized (IplServiceDatabaseConnection.class) {
				if(iplServiceDatabaseConnection==null) {
					iplServiceDatabaseConnection=new IplServiceDatabaseConnection();
				}
			}
    	}
    	return iplServiceDatabaseConnection;
    }
	public  MongoClient getMongoClient() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry)
				.applyConnectionString(new ConnectionString(
						"mongodb://localhost:27017"))
				.build();
		MongoClient client = MongoClients.create(settings);
		return client;
	}
  
	
}
