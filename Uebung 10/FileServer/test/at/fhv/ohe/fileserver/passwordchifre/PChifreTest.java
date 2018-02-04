package at.fhv.ohe.fileserver.passwordchifre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by OliverHeil on 12.06.2017.
 */
class PChifreTest {

    @Test
    void testPW() {
        String[] testPasswords = {
                "test",
                "",
                "HAAAAAALLLLLLLLLOOOOOOOSssssssssssssssss551313132)=()=/()&/(%&/(%&/$§",
                "#######+++/\"(§)!/()$\""
        };

        for (String password : testPasswords) {
            SecurePassword securePassword = new SecurePassword(password);
            assertTrue(securePassword.checkAgainst(password));
        }

        for (String password : testPasswords) {
            SecurePassword securePassword = new SecurePassword(password);
            assertFalse(securePassword.checkAgainst(password + "2"));
        }

        for (int i = 0; i < 10; i++) {
            SecurePassword securePassword1 = new SecurePassword("test");
            SecurePassword securePassword2 = new SecurePassword("test");
            assertFalse(securePassword1.getPasswordHash().equals(securePassword2));
        }
    }

}