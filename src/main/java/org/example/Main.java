package org.example;

import com.mongodb.client.MongoCollection;
import org.example.MongoDB.BaseDatos.MongoDBConnection;
import org.example.MongoDB.Metodos.MetodosMongo;

public class Main {
    public static void main(String[] args) {

        MongoDBConnection mongoDBConnection = new MongoDBConnection();

        mongoDBConnection.connect();

        MongoCollection collection = mongoDBConnection.getCollection("Habitats");

        MongoCollection collection1 = mongoDBConnection.getCollection("Pinguinos");

        mongoDBConnection.getCollection("Habitats");
        mongoDBConnection.getCollection("Pinguinos");

        MetodosMongo metodosMongo = new MetodosMongo();

        metodosMongo.importarXML("/home/accesodatos/IdeaProjects/Practica4_MongoDB/src/XMLS_DESACTUALIZADO/Habitats.xml",collection);
        metodosMongo.importarXML("/home/accesodatos/IdeaProjects/Practica4_MongoDB/src/XMLS_DESACTUALIZADO/Pinguinos.xml",collection1);


        metodosMongo.actualizarXML("/home/accesodatos/IdeaProjects/Practica4_MongoDB/src/XMLS_ACTUALIZADOS/Habitats.xml",collection);
        metodosMongo.actualizarXML("/home/accesodatos/IdeaProjects/Practica4_MongoDB/src/XMLS_ACTUALIZADOS/Pinguinos.xml",collection1);



    }
}