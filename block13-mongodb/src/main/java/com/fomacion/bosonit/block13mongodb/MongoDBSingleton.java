package com.fomacion.bosonit.block13mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBSingleton {
    private static MongoClient mongoClient;

    private MongoDBSingleton() {
        // Constructor privado para evitar instanciación externa
    }

    public static synchronized MongoClient getMongoClient(String connectionString) {
        if (mongoClient == null) {
            // Configura cadena de conexión y cliente
            ConnectionString connString = new ConnectionString(connectionString);

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();

            mongoClient = MongoClients.create(settings);
        }

        return mongoClient;
    }
}

