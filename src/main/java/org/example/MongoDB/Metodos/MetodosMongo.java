package org.example.MongoDB.Metodos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.fasterxml.jackson.dataformat.xml.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MetodosMongo {

    public void importarXML(String filePath, MongoCollection<Document> collection) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            List<Map<String, Object>> xmlData = xmlMapper.readValue(new File(filePath), List.class);

            for (Map<String, Object> doc : xmlData) {
                collection.insertOne(new Document(doc));
            }
            System.out.println("Datos importados correctamente desde XML");
        } catch (IOException e) {
            System.out.println("Error al importar los documentos: " + e.getMessage());
        }
    }


    public void leerDatos(MongoCollection<Document> collection){

        try(MongoCursor<Document> cursor = collection.find().iterator()){
            System.out.println("Documentos en la colección:");
            while(cursor.hasNext()){
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }catch (Exception e){
            System.err.println("Error al leer los documentos: " + e.getMessage());
        }
    }

    public void actualizarXML(String filePath, MongoCollection<Document> collection) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            // Leer los datos del XML actualizado
            List<Map<String, Object>> xmlData = xmlMapper.readValue(new File(filePath), List.class);

            // Eliminar todos los documentos actuales de la colección
            collection.deleteMany(new Document());
            System.out.println("Todos los documentos antiguos han sido eliminados.");

            // Insertar los nuevos documentos del XML actualizado
            for (Map<String, Object> docData : xmlData) {
                collection.insertOne(new Document(docData));
            }
            System.out.println("Datos reemplazados correctamente desde XML.");

        } catch (IOException e) {
            System.out.println("Error al actualizar los documentos: " + e.getMessage());
        }
    }

}
