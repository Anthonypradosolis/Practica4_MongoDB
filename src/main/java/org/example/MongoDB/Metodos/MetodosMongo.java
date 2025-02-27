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

    public void actualizarXML(MongoCollection<Document> collection, Document filtro, Document nuevosDatos) {
        try {
            collection.updateOne(filtro, new Document("$set", nuevosDatos));
            System.out.println("Documento actualizado correctamente");
        } catch (Exception e) {
            System.err.println("Error al actualizar el documento: " + e.getMessage());
        }
    }
}
