package at.fhv.minimudv3.map.items;

import at.fhv.minimudv3.game.player.Player;

/**
 * Descripes a Apple
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class Apple extends Item implements IConsumable {
    private static final int _HEALTHINCREASE = 10;
    private static final String _CONSUME = "Tasty :D";
    private static final String _SHORTDESC = "A Apple";
    private static final String _LONGDESC = "A perfect green Apple";

    /**
     * Creates an Apple
     */
    public Apple() {
        super(5, _SHORTDESC, _LONGDESC);
    }


    @Override
    public void consumeBy(Player player) {
        player.deleteFromInventory(this);
        player.outStream(_CONSUME);
        player.healthIncrease(_HEALTHINCREASE);
    }
}
