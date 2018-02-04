package at.fhv.ohe.uebung2_a;


import org.junit.Test;

import junit.framework.TestCase;

public class RotEncryptionTest extends TestCase {

	@Test
	public void testEncrypteDecrypteTextString() {
		for (int i = 0; i < 10000; i++) {
			String testString = randomString((int)((Math.random()+1)* 256 * (Math.random() + 1)));
			int rotation	= (int)(Math.random()*1000);
				
			String encryptString = RotEncryption.encrypteText(testString, rotation);
			String decryptString = RotEncryption.decrypteText(encryptString, rotation);
				
			assertTrue("Fail", testString.equals(decryptString));
		}
	}
	
	public String randomString(int _length) {
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < _length; i++){
			newString.append(Character.toChars('a' + (int)(Math.random()*26)));
			newString.append(Character.toChars('A' + (int)(Math.random()*26)));
			if (i % 10 == 0) {
				newString.append(' ');	
				newString.append(Character.toChars(':' + (int)(Math.random()*26)));	// random punctuation character
			}
		}
		return newString.toString();
	}
}
