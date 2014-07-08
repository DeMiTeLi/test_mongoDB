package by.demiteli;

import java.net.UnknownHostException;

/**
 * @author Dzmitry Varabei
 */
public class HumanUtilManualTest {

    public static void main(String [] args) throws UnknownHostException {


    HumanUtil humanUtil = new HumanUtil();


        humanUtil.connectToDB();
        humanUtil.dropDB();
        humanUtil.createNewCollection();

    Human human1 = new Human();
        human1.setName("Piter");
        human1.setAge(22);
        human1.setCity("Paris");
        human1.setJob("Engineer");
        human1.setMobile("+37533265487");
        human1.setSex("male");

    Human human2 = new Human();
        human2.setName("Piter");
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

    humanUtil.save(human1);
    humanUtil.save(human2);
    humanUtil.save(human3);
    }
}
