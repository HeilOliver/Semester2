package at.fhv.minimudv3.map;

/**
 * A Exception if the player want to move in a not possible direction
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class IllegalMoveException extends Exception {
    public IllegalMoveException(String message) {
        super(message);
    }
}
