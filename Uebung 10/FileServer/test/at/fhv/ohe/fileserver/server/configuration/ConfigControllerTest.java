package at.fhv.ohe.fileserver.server.configuration;

import at.fhv.ohe.fileserver.passwordchifre.SecurePassword;
import at.fhv.ohe.fileserver.server.configuration.exceptions.ConfigurationIsNotCompletedException;
import at.fhv.ohe.fileserver.server.configuration.exceptions.ConfigurationVersionException;
import at.fhv.ohe.fileserver.server.user.AccessLevel;
import at.fhv.ohe.fileserver.server.user.User;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by OliverHeil on 14.06.2017.
 */
class ConfigControllerTest {


    @Test
    void testConfigController() throws JSONException, IOException,
            ConfigurationIsNotCompletedException, ConfigurationVersionException {

        ServerConfiguration saveConf = new ServerConfiguration();
        saveConf.setTimeout(1000);
        saveConf.setConnectionsAllowed(4);
        LinkedList<User> list = new LinkedList<>();
        list.add(new User(new SecurePassword("admin"),"0", AccessLevel.ADMIN));
        list.add(new User(new SecurePassword("admin"),"1", AccessLevel.RUSER));
        list.add(new User(new SecurePassword("admin"),"2", AccessLevel.RWUSER));
        saveConf.setKnownUsers(list);
        saveConf.setPort(4242);
        saveConf.setSharedDirectory(new File(new File("").getAbsolutePath()));
        saveConf.setTransferWindowSize(5);
        ConfigController.writeConfiguration(new File("test.conf"),saveConf);

        ServerConfiguration loadConf = ConfigController.getConfiguration(new File("test.conf"));

        assertTrue(saveConf.getConnectionsAllowed() == loadConf.getConnectionsAllowed());
        assertTrue(saveConf.getPort() == loadConf.getPort());
        assertTrue(saveConf.getTransferWindowSize() == loadConf.getTransferWindowSize());
        assertTrue(saveConf.getTimeout() == loadConf.getTimeout());
        assertTrue(saveConf.getSharedDirectory().equals(loadConf.getSharedDirectory()));

        int i = 0;
        for (User user : loadConf.getKnownUsers()) {
            for (User user1 : list) {
                if (user.getName().equals(user1.getName())){
                    assertTrue(user.getAccessLevel().equals(user1.getAccessLevel()));
                    SecurePassword loadPW = user.getPassword();
                    SecurePassword savePW = user1.getPassword();

                    assertTrue(loadPW.getPasswordHash().equals(savePW.getPasswordHash()));
                    assertTrue(loadPW.getSalt().equals(savePW.getSalt()));
                    i++;
                }
            }
        }
        assertTrue(list.size() == i);


        //new File("test.conf").delete();
    }

}