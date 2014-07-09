package by.demiteli;

import by.demiteli.exceptions.InvalidHumanName;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Dzmitry Varabei
 */
public class TestHumanUtil {

    static HumanUtil humanUtil = new HumanUtil();
    static Human human1, human2, human3;
    final Logger logger = Logger.getLogger(TestDAOImpl.class.getName());

    @BeforeClass
    public static void connectToDB() throws UnknownHostException {

        humanUtil.connectToDB();
        humanUtil.dropDB();
        humanUtil.createNewCollection();

        human1 = new Human();
            human1.setName("Piter");
            human1.setAge(22);
            human1.setCity("Paris");
            human1.setJob("Engineer");
            human1.setMobile("+37533265487");
            human1.setSex("male");

        human2 = new Human();
            human2.setName("Piter");
            human2.setAge(25);
            human2.setCity("Paris");
            human2.setJob("Photografer");
            human2.setMobile("+37533265486");
            human2.setSex("male");

        human3 = new Human();
            human3.setName("Sara");
            human3.setAge(25);
            human3.setCity("London");
            human3.setJob("Gournalist");
            human3.setMobile("+375294561264");
            human3.setSex("female");
    }

    @Before
    public void recreateCollection() throws InvalidHumanName, UnknownHostException {
        humanUtil.dropCollection();
        humanUtil.save(human1);
        humanUtil.save(human3);

    }

    @Test
    public void testSave() throws InvalidHumanName, UnknownHostException {

       Assert.assertEquals(2, humanUtil.countHumans());
    }

    @Test
    public void testRead(){

        Assert.assertEquals(human1.getCity(), humanUtil.read(human1.getName()).getCity());
    }

    @Test
    public void testReadAll(){

        List<Human> listHums = humanUtil.readAll();
        Assert.assertEquals(2, listHums.size());
    }

    @Test
    public void testRemove(){

        humanUtil.remove("Sara");
        int countAfterRemoving = humanUtil.countHumans();
        Assert.assertEquals(1, countAfterRemoving);
    }

    @Test(expected = InvalidHumanName.class)
    public void testSaveException() throws InvalidHumanName, UnknownHostException {

         humanUtil.save(human2);

    }
}
