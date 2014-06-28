package by.demiteli;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Varabei
 */
public interface DAO {

    void connectToDB(String dbName)throws UnknownHostException;

    Set<String> getNamesOfCollections();

    void createNewCollection(String collName);

    DBCollection getCollection(String collName);

    void insertDocument(String collectionName, BasicDBObject document);

    void removeDocument(String collectionName, BasicDBObject markForDeleting);

    DBObject findFirstDocument(String collName);

    void printAllDocuments(String collName);

    List<DBObject> findDocuments(String collName, BasicDBObject query);

    void dropDataBase();
}
