package by.demiteli;

import by.demiteli.exceptions.InvalidHumanName;

import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Dzmitry Varabei
 */
public interface HumanDAO {

    public void connectToDB() throws UnknownHostException;
    Human read(String id);
    List<Human> readAll();
    public void remove(String id);
    public void save(Human human) throws UnknownHostException, InvalidHumanName;
    public void dropDB();
    public void dropCollection();
    void createNewCollection();
    int countHumans();
}