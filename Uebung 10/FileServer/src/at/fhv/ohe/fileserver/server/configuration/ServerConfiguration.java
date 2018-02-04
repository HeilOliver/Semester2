package at.fhv.ohe.fileserver.server.configuration;

import at.fhv.ohe.fileserver.server.user.User;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by OliverHeil on 12.06.2017.
 */
public class ServerConfiguration {
    private int _timeout;
    private File _sharedDirectory;
    private LinkedList<User> _knownUsers;
    private int _port;
    private int _connectionsAllowed;
    private int _transferWindowSize;



    public boolean isComplete() {
        if ( _timeout ==  0) { return false; }
        if ( _port ==  0) { return false; }
        if ( _connectionsAllowed ==  0) { return false; }
        if ( _transferWindowSize ==  0) { return false; }
        if ( _sharedDirectory ==  null) { return false; }
        if ( _knownUsers ==  null) { return false; }
        return true;
    }

    public int getTimeout() {
        return _timeout;
    }

    public File getSharedDirectory() {
        return _sharedDirectory;
    }

    public LinkedList<User> getKnownUsers() {
        return _knownUsers;
    }

    public int getPort() {
        return _port;
    }

    public int getConnectionsAllowed() {
        return _connectionsAllowed;
    }

    public int getTransferWindowSize() {
        return _transferWindowSize;
    }

    public void setTimeout(int timeout) {
        _timeout = timeout;
    }

    public void setSharedDirectory(File sharedDirectory) {
        _sharedDirectory = sharedDirectory;
    }

    public void setKnownUsers(LinkedList<User> knownUsers) {
        _knownUsers = knownUsers;
    }

    public void setPort(int port) {
        _port = port;
    }

    public void setConnectionsAllowed(int connectionsAllowed) {
        _connectionsAllowed = connectionsAllowed;
    }

    public void setTransferWindowSize(int transferWindowSize) {
        _transferWindowSize = transferWindowSize;
    }
}
