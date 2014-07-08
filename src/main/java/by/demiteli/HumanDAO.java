package by.demiteli;

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
    public void save(Human human);
    public boolean checkUnique(Human human);
    public void dropDB();
    void createNewCollection();
}