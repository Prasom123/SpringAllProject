package com.heraizen.util;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {
	private static MongoDBConnection mongoDBConnection=null;
	private MongoDBConnection() {

	}
    public static MongoDBConnection getInstance() {
    	if(mongoDBConnection==null) {
    		synchronized (MongoDBConnection.class) {
				if(mongoDBConnection==null) {
					mongoDBConnection=new MongoDBConnection();
				}
			}
    	}
    	return mongoDBConnection;
    }
    public static  MongoClient getMongoClient() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry)
				.applyConnectionString(new ConnectionString(
						"mongodb://localhost:27017"))
				.build();
		MongoClient client = MongoClients.create(settings);
		System.out.println(client);
		return client;
	}
}
