package at.fhv.minimudv3.game;

import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.field.Field;
import at.fhv.minimudv3.map.field.IAccessible;

/**
 * test Field for Player Test
 *
 * Created by Oliver Heil on 26.04.2017.
 */
public class TestField extends Field implements IAccessible{

    public TestField() {
        super("", "");
    }

    @Override
    public Field enter(Player player) {
        return this;
    }
}
