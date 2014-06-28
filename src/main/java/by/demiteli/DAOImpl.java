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
    public Set<String> getNamesOfCollections(){

    Set<String> colls = db.getCollectionNames();

        return colls;
    }

    @Override
     public void createNewCollection(String collName){

        db.createCollection(collName, null);
    }

    @Override
     public DBCollection getCollection(String collName){

         return db.getCollection(collName);
    }

    @Override
    public void insertDocument(String collectionName, BasicDBObject documentContent){

        getCollection(collectionName).insert(documentContent);
    }

    @Override
    public void removeDocument(String collectionName, BasicDBObject markForDeleting){

        getCollection(collectionName).remove(markForDeleting);
    }

    @Override
    public DBObject findFirstDocument(String collName){

        return getCollection(collName).findOne();
    }

    public void printAllDocuments(String collName){

        DBCollection coll = getCollection(collName);
        cursor = coll.find();
        try{
        while (cursor.hasNext())
            System.out.println(cursor.next());
        } finally{
            cursor.close();
            }

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

    public void dropDataBase(){
     db.dropDatabase();
    }

}
