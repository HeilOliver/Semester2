package at.fhv.ohe.uebung3.factory;


/**
 * An {@Link Exception} for an Illegal Trolly position.
 *
 * Created by Oliver Heil on 29.03.2017.
 */
public class IllegalTrollyPosition extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalTrollyPosition(String message) {
		super(message);
	}

}
