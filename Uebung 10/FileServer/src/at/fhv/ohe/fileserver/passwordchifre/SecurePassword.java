package at.fhv.ohe.fileserver.passwordchifre;

/**
 *
 * Created by OliverHeil on 12.06.2017.
 */
public class SecurePassword {
    private String _salt;
    private String _passwordHash;

    public SecurePassword(String clearPassword) {
        PChifre pChifre = new PChifre(clearPassword);
        _passwordHash = pChifre.getPasswordHash();
        _salt = pChifre.getPasswordSalt();
    }

    public boolean checkAgainst(String password) {
        String hash = new PChifre(password, _salt).getPasswordHash();
        return hash.equals(_passwordHash);
    }

    public SecurePassword(String passwordHash, String salt) {
        _salt = salt;
        _passwordHash = passwordHash;
    }

    public String getSalt() {
        return _salt;
    }

    public String getPasswordHash() {
        return _passwordHash;
    }
}
