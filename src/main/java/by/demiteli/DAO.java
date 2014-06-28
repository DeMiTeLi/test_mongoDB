package by.demiteli;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Varabei
 */
public interface DAO {

    void connectToDB(String dbName)throws UnknownHostException;

    Set<String> getNamesOfCollection();

    void creteCollection(String collName, BasicDBObject document);

    DBCollection getCollection(String collName);

    void insertDocument(String collectionName, BasicDBObject document);

    void removeElement(String collectionName, BasicDBObject document);

    DBObject findFirstDocument(String collName);

    List<DBObject> getAllDocuments(String collName);

    List<DBObject> findDocuments(String collName, BasicDBObject query);
}
