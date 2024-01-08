package com.fomacion.bosonit.block13mongodb;

import com.mongodb.client.MongoClient;

public class Connection {
    public static void main(String[] args) {

        String connectionString = "mongodb+srv://wtrioja:Betagwe44@cluster0.nhqph3a.mongodb.net/?retryWrites=true&w=majority";

        MongoClient mongoClient = MongoDBSingleton.getMongoClient(connectionString);

        mongoClient.listDatabaseNames().forEach(databaseName -> System.out.println("Database: " + databaseName));


    }

}
