package at.fhv.minimudv3.map.field;

/**
 * Describes a Wall
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class Wall extends Field {

    private static final String _WALLSHORTDESC = "A Wall";
    private static final String _WALLLONGDESC = "A beautifull Wall.";

    /**
     * Creates a Wall
     */
    public Wall() {
        super(_WALLSHORTDESC, _WALLLONGDESC);
    }
}
