package by.demiteli;

import by.demiteli.exceptions.InvalidHumanName;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dzmitry Varabei
 */
public class HumanUtilManualTest {

    public static void main(String [] args) throws UnknownHostException, InvalidHumanName {

    HumanUtil humanUtil = new HumanUtil();
    final Logger logger = Logger.getLogger(HumanUtilManualTest.class.getName());

        humanUtil.connectToDB();
        humanUtil.dropDB();
        humanUtil.createNewCollection();

        //Testing save method

    Human human1 = new Human();
        human1.setName("Piter");
        human1.setAge(22);
        human1.setCity("Paris");
        human1.setJob("Engineer");
        human1.setMobile("+37533265487");
        human1.setSex("male");

    Human human2 = new Human();
        human2.setName("Piter1");
        human2.setAge(22);
        human2.setCity("Paris");
        human2.setJob("Engineer");
        human2.setMobile("+37533265487");
        human2.setSex("male");

    Human human3 = new Human();
        human3.setName("Sara");
        human3.setAge(25);
        human3.setCity("London");
        human3.setJob("Gournalist");
        human3.setMobile("+375294561264");
        human3.setSex("female");

    try{
        humanUtil.save(human1);
        humanUtil.save(human2);
        humanUtil.save(human3);
    } catch (InvalidHumanName e){
        e.getMessage(); e.printStackTrace();
    }
        //Testing read method

     Human hum = humanUtil.read("Sara");
             logger.info("Name: "+hum.getName() + ", Age: "+ hum.getAge() + ", City: " + hum.getCity() + ", Job: " + hum.getJob() +
                        ", Mobile: " + hum.getMobile() + ", Sex: " + hum.getSex() );
             logger.info("");

        //Testing readAll method

        List<Human> humList = humanUtil.readAll();
            if (humList.size()==3) logger.info("All humans was read");
            logger.info("");

        //Testing readAll method

        int unitilRemoving = humList.size();
        humanUtil.remove("Piter1");
        int afterRemoving = humanUtil.readAll().size();
        if (unitilRemoving!=afterRemoving) logger.info("Removing sucsess");
    }
}
