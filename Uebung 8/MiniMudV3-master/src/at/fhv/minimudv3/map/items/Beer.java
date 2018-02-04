package at.fhv.minimudv3.map.items;

import at.fhv.minimudv3.game.player.Player;

/**
 * Describes a Beer
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class Beer extends Item implements IConsumable {

    private static final String _SHORTDESC = "A Beer";
    private static final String _LONGDESC = "A perfect cooled Beer, I think its tasty";
    private static final String _CONSUME = "Hmmmmmm sooooo good..";

    /**
     * Creates a Beer
     */
    public Beer() {
        super(5, _SHORTDESC, _LONGDESC);
    }

    @Override
    public void consumeBy(Player player) {
        player.deleteFromInventory(this);
        player.outStream(_CONSUME);
    }
}
