package by.demiteli;

import by.demiteli.exceptions.InvalidHumanName;
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

    static DB db = null;
    static DBCursor cursor= null;
    static DBCollection humans = null;
    Human human = null;

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
    public void createNewCollection(){

        db.createCollection("humans", null);
        humans = db.getCollection("humans");
    }

    @Override
    public Human read(String name) {

            BasicDBObject query = new BasicDBObject("name", name);

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
    public void remove(String name) {

        humans.remove(new BasicDBObject("name", name));
    }

    @Override
    public void save(Human human) throws UnknownHostException, InvalidHumanName {

        //HumanUtil hu = new HumanUtil();
        //hu.connectToDB();
        //humans = db.getCollection("humans");
        boolean check = HumanUtil.checkUnique(human);

        if (check){
            Gson gson = new Gson();
            BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(human));

            humans.insert(obj);
        } else { throw new InvalidHumanName("Human with name " + human.getName() + " already exists. Please insert another name.");}
    }

    static boolean checkUnique(Human insertingHuman){

        boolean unique = true;

        BasicDBObject query = new BasicDBObject("name", insertingHuman.getName());

        cursor = humans.find(query);
        try{
            if (cursor.size()>0) unique = false;
        } finally {
            cursor.close();
        }

        return unique;
    }

    @Override
    public void dropDB(){

        db.dropDatabase();
    }

    @Override
    public void dropCollection() {
        humans.drop();

    }

    @Override
    public int countHumans(){
        try{
        cursor = humans.find();
        } finally{
            cursor.close();
        }
        return cursor.size();
    }

}