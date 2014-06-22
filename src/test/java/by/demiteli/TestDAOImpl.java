package by.demiteli;

import com.mongodb.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Varabei
 */
public class TestDAOImpl {

    static DAO daoImpl = new DAOImpl();
    final Logger logger = Logger.getLogger(TestDAOImpl.class.getName());

    @BeforeClass
    public static void connectToDB() throws UnknownHostException {

        daoImpl.connectToDB("TestingMongo");
        daoImpl.dropDataBase();
    }

    @Test
    public void testDAOImpl(){

        boolean newCollection = false;

        daoImpl.createNewCollection("testPets");
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test"));

        /*Testing of creation of colletion*/
        Set<String> coll = daoImpl.getNamesOfCollections();
        Iterator iterator = coll.iterator();
        while (iterator.hasNext())
            if (iterator.next().equals("testPets")) newCollection = true;
        Assert.assertTrue("true", newCollection);

        /*Checking of inserting of document*/
        List<DBObject> documents = daoImpl.findDocuments("testPets", new BasicDBObject("name", "test"));

        Assert.assertTrue("true", documents.size()>=1);
    }

    @Test
    public void testGetNamesOfCollections(){

        daoImpl.dropDataBase();

        daoImpl.insertDocument("testPets1", new BasicDBObject("name", "test1"));
        daoImpl.insertDocument("testPets2", new BasicDBObject("name", "test2"));

        Set<String> collections = daoImpl.getNamesOfCollections();

        Assert.assertTrue("true", collections.size()==3);
    }

    @Test
    public void testGetCollection(){

        daoImpl.dropDataBase();
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test"));

        Assert.assertEquals("com.mongodb.DBCollectionImpl", (String)daoImpl.getCollection("testPets").getClass().getName());
    }

    @Test
    public void testRemoveDocument(){

        daoImpl.dropDataBase();
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test1"));
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test2"));
        Assert.assertTrue(daoImpl.getCollection("testPets").count() == 2);

        daoImpl.removeDocument("testPets", new BasicDBObject("name", "test1"));
        Assert.assertTrue(daoImpl.getCollection("testPets").count() == 1);
    }

    @Test
    public void testFindFirsDocument(){

        daoImpl.dropDataBase();
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test1"));
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test2"));

        String document = daoImpl.findFirstDocument("testPets").toString();
        String requiredPart = "\"name\" : \"test1\"";
        boolean foundIt = false;
        for (int i = 0;
             i <= (document.length() - requiredPart.length());
             i++) {
            if (document.regionMatches(i, requiredPart, 0, requiredPart.length()))
                foundIt = true;
        }
        Assert.assertTrue("true", foundIt);
    }

    @Test
    public void findDocuments(){

        daoImpl.dropDataBase();
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test1").append("count", 1));
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test2").append("count", 2));
        daoImpl.insertDocument("testPets", new BasicDBObject("name", "test2").append("count", 3));

        List<DBObject> documents = daoImpl.findDocuments("testPets", new BasicDBObject("count", new BasicDBObject("$gt", 1)));

        Assert.assertTrue(documents.size()==2);
    }


}
