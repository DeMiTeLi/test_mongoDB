package by.demiteli;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Varabei
 */
public class HumanUtil implements HumanDAO {

    DB db = null;
    DBCursor cursor= null;
    DBCollection humans = null;
    Human human = null;

    @Override
    public void connectToDB() throws UnknownHostException {
        try{
            MongoClient client = new MongoClient("localhost", 27017);
            this.db = client.getDB("ImageStorageDB");
        } catch (UnknownHostException e){
            System.out.println("Cannot to connect to database!");
            e.printStackTrace();}

      /* humans = db.getCollection("humans");
        humans.insert(new BasicDBObject("test","test"));  */

    }

    @Override
    public void createNewCollection(){

        db.createCollection("humans", null);
        db.getCollection("humans").insert(new BasicDBObject("test", "test"));
    }

    @Override
    public Human read(String id) {

            BasicDBObject query = new BasicDBObject("_id", "id");


            cursor = humans.find(query);
            try{
                DBObject dbo = cursor.next();
                human = (new Gson()).fromJson(dbo.toString(), Human.class);
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
            while (cursor.hasNext()){
                DBObject dbo = cursor.next();
                human = (new Gson()).fromJson(dbo.toString(), Human.class);
                hums.add(human);
            }
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

       HumanUtil hu = new HumanUtil();
        boolean check = hu.checkUnique(human);

        if (check){
            Gson gson = new Gson();
            BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(human));

            humans.insert(obj);
        }
    }

    @Override
    public boolean checkUnique(Human insertingHuman){

        humans = db.getCollection("humans");
        boolean unique = true;
        int counter = 0;

        BasicDBObject query = new BasicDBObject("name", insertingHuman.getName());

        cursor = humans.find(query);
        try{
            while (cursor.hasNext())
                counter++;
                if (counter>0) unique = false;
        } finally {
            cursor.close();
        }

        return unique;
    }

    @Override
    public void dropDB(){

        db.dropDatabase();

    }

}