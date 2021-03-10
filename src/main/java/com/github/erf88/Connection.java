package com.github.erf88;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;

public class Connection {

	public MongoDatabase getConnection() {
		ConnectionString connection = new ConnectionString("mongodb://localhost:27017/test");
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connection).retryWrites(true).build();
		MongoClient client = MongoClients.create(settings);
		MongoDatabase database = client.getDatabase("test");
		return database;
	}

}
