package at.fhv.ohe.fileserver.server.server;



import at.fhv.ohe.fileserver.server.configuration.ServerConfiguration;
import at.fhv.ohe.fileserver.server.user.User;


import java.io.File;
import java.util.LinkedList;

/**
 * Created by OliverHeil on 12.06.2017.
 */
public class Server {
    private File _sharedDirectory;
    private ServerConfiguration _configuration;
    private LinkedList<User> _connectedUser;
    private LinkedList<User> _knownUsers;


    public Server(ServerConfiguration configuration) {
        _knownUsers = configuration.getKnownUsers();
        _connectedUser = new LinkedList<>();
        _configuration = configuration;
        _sharedDirectory = configuration.getSharedDirectory();
    }


}
