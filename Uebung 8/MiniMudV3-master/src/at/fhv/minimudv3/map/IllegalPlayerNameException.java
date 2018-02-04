package at.fhv.minimudv3.map;

/**
 * An Exception if the Player name is already used or not valid
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class IllegalPlayerNameException extends Exception {

    public IllegalPlayerNameException(String message) {
        super(message);
    }
}
