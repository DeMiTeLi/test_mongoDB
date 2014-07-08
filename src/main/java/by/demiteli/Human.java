package by.demiteli;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Varabei
 */
public class Human implements HumanDAO {

    DB db = null;
    DBCursor cursor= null;
    DBCollection humans = null;
    Human human= null;

    @Override
    public void connectToDB() throws UnknownHostException {
        try{
            MongoClient client = new MongoClient("localhost", 27017);
            this.db = client.getDB("ImageStorageDB");
        } catch (UnknownHostException e){
            System.out.println("Cannot to connect to database!");
            e.printStackTrace();}
    }

    @Override
    public Human read(String id) {

            BasicDBObject query = new BasicDBObject("_id", "id");

            cursor = humans.find(query);
            try{
                human = (Human) cursor.next();
            } finally {
                cursor.close();
            }

            return human;
        }

    @Override
    public List<Human> readAll() {

        List<Human> hums = new ArrayList<Human>();
        cursor = humans.find();
        try{
            while (cursor.hasNext())
                hums.add((Human)cursor.next());
        } finally{
            cursor.close();
        }
        return hums;
    }


    @Override
    public void remove(String id) {
        humans.remove(new BasicDBObject("_id", id));
    }

    @Override
    public void save(Human human) {

            humans.insert((List<DBObject>) human);
    }
}
