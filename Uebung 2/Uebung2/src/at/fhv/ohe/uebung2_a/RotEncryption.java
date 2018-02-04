package at.fhv.ohe.uebung2_a;

/**
 * A class for Decrypte and Encrypte with ROT-Encryption
 * 
 * @author      Oliver Heil - fhv.at
 * @version     1.0
 * @since   	2017-03-17
 */
public class RotEncryption {
	private int 	_rotation;
	
	/**
	 * Creates a RotEncryption Object.
	 * 
	 * @param rotation Set the rotation that shout be used
	 */
	public RotEncryption(int rotation) {
		_rotation = rotation;
	}
	
	/**
	 * Creates a RotEncryption Object.
	 * As default the rotation is set to 0.
	 */
	public RotEncryption() {
		this(0);
	}

	/**
	 * Get the rotation
	 * 
	 * @return the rotation
	 */
	public int getRotation() {
		return _rotation;
	}

	/**
	 * Set the rotation for Encrypte / Decrypte
	 * 
	 * @param rotation
	 */
	public void setRotation(int rotation) {
		_rotation = rotation;
	}
	
	/**
	 * Encrypte a given String with ROT-Encryption. The Encryption doesn´t work with a negativ rotation.  
	 * To decrypte use {@decrypteText}. The rotation is set with setRotation.
	 * 
	 * @param textToEncrypte	The Text that shoud be encrypte
	 * @return return the encrypted Text
	 */
	public String encrypteText(String textToEncrypte) {
		return encrypteText(textToEncrypte,_rotation);
	}
	
	/**
	 * Decrypte a given String with ROT-Encryption. The decrypte doesn´t work with a negativ rotation.  
	 * To encrypte use {@encrypteText}. The rotation is set with setRotation.
	 * 
	 * @param textToDecrypte	The Text that shoud be decrypte
	 * @return return the decrypted Text
	 */
	public String decrypteText(String textToEncrypte) {
		return decrypteText(textToEncrypte,_rotation);
	}

	/**
	 * Encrypte a given String with ROT-Encryption. The Encryption doesn´t work with a negativ rotation.  
	 * To decrypte use {@decrypteText}
	 * 
	 * @param textToEncrypte	The Text that shoud be encrypte
	 * @param rotation			The rotation that shud be used
	 * @return return the encrypted Text
	 */
	public static String encrypteText(String textToEncrypte, int rotation) {
		rotation = rotation % 26;
		StringBuilder encryptedText = new StringBuilder();
		for (int i = 0; i < textToEncrypte.length(); i++) {
			char c = textToEncrypte.charAt(i);
			if (c >= 'a' && c <= 'z') {
				encryptedText.append(Character.toChars((((c-'a') + rotation) % 26) + 'a'));
			} else if (c >= 'A' && c <= 'Z') {
				encryptedText.append(Character.toChars((((c-'A') + rotation) % 26) + 'A'));
			} else {
				encryptedText.append(c);
			}
		}
		return encryptedText.toString();
	}
	
	/**
	 * Decrypte a given String with ROT-Encryption. The decrypte doesn´t work with a negativ rotation.  
	 * To encrypte use {@encrypteText}
	 * 
	 * @param textToDecrypte	The Text that shoud be decrypte
	 * @param rotation			The rotation that shud be used
	 * @return return the decrypted Text
	 */
	public static String decrypteText(String textToDecrypte, int rotation) {
		rotation = rotation % 26;
		return encrypteText(textToDecrypte, 26-rotation);
	}
}
