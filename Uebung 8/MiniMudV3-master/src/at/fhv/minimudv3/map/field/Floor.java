package at.fhv.minimudv3.map.field;

import at.fhv.minimudv3.game.player.Player;

/**
 * Describes a Floor
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class Floor extends Field implements IAccessible {


    private static final String _SHOERTDESCR = "A piece of Floor";
    private static final String _LONGDESCRIP = "A beautifull pice of floor";

    /**
     * Creates an Floor with standard description
     */
    public Floor() {
        this(_SHOERTDESCR, _LONGDESCRIP);
    }

    /**
     * Creates an Floor with given description
     *
     * @param shortDescription - The short Description
     * @param longDescription  - The long Description
     */
    public Floor(String shortDescription, String longDescription) {
        super(shortDescription, longDescription);
    }

    @Override
    public Field enter(Player player) {
        return this;
    }
}
