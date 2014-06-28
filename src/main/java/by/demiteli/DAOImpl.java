package by.demiteli;

import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Varabei
 */
public class DAOImpl implements DAO {

    DB db = null;
    DBCursor cursor= null;

    @Override
    public void connectToDB(String dbName) throws UnknownHostException {
        try{
        MongoClient client = new MongoClient("localhost", 27017);
        this.db = client.getDB(dbName);
        } catch (UnknownHostException e){
            System.out.println("Cannot to connect to database!");
        e.printStackTrace();}
    }

    @Override
    public Set<String> getNamesOfCollection(){

    Set<String> colls = db.getCollectionNames();

        return colls;
    }

    @Override
     public void creteCollection(String collName, BasicDBObject document){

        db.createCollection(collName, document);
    }

    @Override
     public DBCollection getCollection(String collName){

         return db.getCollection(collName);
    }

    @Override
    public void insertDocument(String collectionName, BasicDBObject document){

        getCollection(collectionName).insert(document);
    }

    @Override
    public void removeElement(String collectionName, BasicDBObject document){

        getCollection(collectionName).remove(document);
    }

    @Override
    public DBObject findFirstDocument(String collName){

        return getCollection(collName).findOne();
    }

    public List<DBObject> getAllDocuments(String collName){

        List<DBObject> Documents = new ArrayList<DBObject>();
        DBCollection coll = getCollection(collName);
        cursor = coll.find();
        try{
        while (cursor.hasNext())
            Documents.add(cursor.next());
        } finally{
            cursor.close();
            }

        return Documents;
        }

    public List<DBObject> findDocuments(String collName, BasicDBObject query){
    List<DBObject> documents = new ArrayList<DBObject>();
    cursor = getCollection(collName).find(query);
        try{
            while (cursor.hasNext())
                documents.add(cursor.next());
        } finally {
            cursor.close();
        }

        return documents;
    }

}
