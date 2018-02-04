package at.fhv.minimudv3.map.items;

/**
 * A basic Key with an key ID
 * <p>
 * Created by Oliver H on 21.04.2017.
 */
public class Key extends Item {
    // statics
    private static final int _KEYWIGHT = 10;

    // Vars
    private int _keyID;

    // Constructors

    /**
     * Creates an basic key.
     *
     * @param keyID            - The Key id
     * @param shortDescription - The short description
     * @param longDescription  - The long description
     */
    public Key(int keyID, String shortDescription, String longDescription) {
        this(keyID, _KEYWIGHT, shortDescription, longDescription);
    }

    /**
     * Creates an basic key.
     *
     * @param keyID            - The Key id
     * @param keyWight         - The key wight
     * @param shortDescription - The short description
     * @param longDescription  - The long description
     */
    public Key(int keyID, int keyWight, String shortDescription, String longDescription) {
        super(keyWight, shortDescription, longDescription);
        _keyID = keyID;
    }

    // Funktions
    // Getter / Setter

    /**
     * Returns the Key ID of this key
     *
     * @return - The Key ID
     */
    public int getKeyID() {
        return _keyID;
    }
}
