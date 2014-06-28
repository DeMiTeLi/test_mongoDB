package by.demiteli;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Dzmitry Varabei
 */
public class TestDriveMongoDB extends DAOImpl {

    public static void main(String [] args) throws UnknownHostException {

    DAO mongoDBTest = new DAOImpl();

    mongoDBTest.connectToDB("testDriveMongo");

    mongoDBTest.dropDataBase();

    mongoDBTest.createNewCollection("pets");

    mongoDBTest.insertDocument("pets", new BasicDBObject("type", "cat").append("name", "Glory").append("color", "black")
            .append("weight", 3));
    mongoDBTest.insertDocument("pets", new BasicDBObject("type", "dog").append("name", "Rex").append("color", "broun")
            .append("weight", 9));
    mongoDBTest.insertDocument("pets", new BasicDBObject("type", "rabbit").append("name", "Fasty").append("color", "white")
            .append("weight", 1));

    mongoDBTest.createNewCollection("wildAnimals");
    mongoDBTest.insertDocument("wildAnimals",new BasicDBObject("name","Tiger").append("weight", 77)
                                .append("liveArial", "Jungle"));
    mongoDBTest.insertDocument("wildAnimals", new BasicDBObject("name", "Aliphant").append("color", "gray")
                                .append("weight", 2555));

    System.out.println("Data base has following collections:");
    Set<String> allClasses = mongoDBTest.getNamesOfCollections();
        Iterator iterator = allClasses.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
            System.out.println();

    mongoDBTest.removeDocument("pets", new BasicDBObject("name", "Rex"));

    System.out.println("Collection pets has following documets:");
    mongoDBTest.printAllDocuments("pets");
    System.out.println();

    System.out.println("The first document of collection pets is:");
    System.out.println(mongoDBTest.findFirstDocument("pets"));
    System.out.println();

    System.out.println("Seeking document is:");
    System.out.println(mongoDBTest.findDocuments("wildAnimals", new BasicDBObject("weight", new BasicDBObject("$gt", 1000))));

    }
}
