package at.fhv.ohe.fileserver.passwordchifre;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * Created by OliverHeil on 12.06.2017.
 */
class PChifre {
    private String _passwordHash;
    private String _passwordSalt;

    PChifre(String clearPassword) {
        this(clearPassword, null);
    }

    PChifre(String clearPassword, String salt) {
        if (salt == null) {
            _passwordSalt = generateNewSalt();
        } else {
            _passwordSalt = salt;
        }
        generateSecurePassword(clearPassword);
    }

    private String generateNewSalt() {
        Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    protected void generateSecurePassword(String clearPassword) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String salt = _passwordSalt;
            String passWithSalt = clearPassword + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);

            StringBuilder sb = new StringBuilder();
            for (byte aPassHash : passHash) {
                sb.append(Integer.toString((aPassHash & 0xff) + 0x100, 16).substring(1));
            }
            _passwordHash = sb.toString();
            _passwordSalt = salt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getPasswordHash() {
        return _passwordHash;
    }

    public String getPasswordSalt() {
        return _passwordSalt;
    }
}
