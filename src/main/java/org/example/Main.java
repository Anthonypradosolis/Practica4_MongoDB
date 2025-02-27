package org.example;

import com.mongodb.client.MongoCollection;
import org.example.MongoDB.BaseDatos.MongoDBConnection;
import org.example.MongoDB.Metodos.MetodosMongo;

public class Main {
    public static void main(String[] args) {

        MongoDBConnection mongoDBConnection = new MongoDBConnection();

        mongoDBConnection.connect();

        MongoCollection collection = mongoDBConnection.getCollection("Habitats");

        mongoDBConnection.getCollection("Habitats");
        mongoDBConnection.getCollection("Pinguinos");

        MetodosMongo metodosMongo = new MetodosMongo();

        metodosMongo.importarXML("habitats.xml",collection);



    }
}