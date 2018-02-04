package at.fhv.ohe.fileserver.server.user;

import at.fhv.ohe.fileserver.passwordchifre.SecurePassword;

/**
 * Created by OliverHeil on 11.06.2017.
 */
public class User {
    private SecurePassword _passwordHash;
    private String _name;
    private AccessLevel _accessLevel;

    public User(SecurePassword passwordHash, String name, AccessLevel accessLevel) {
        _passwordHash = passwordHash;
        _name = name;
        _accessLevel = accessLevel;
    }

    public String getName() {
        return _name;
    }

    public SecurePassword getPassword() {
        return _passwordHash;
    }

    public AccessLevel getAccessLevel() {
        return _accessLevel;
    }
}
