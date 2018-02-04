package at.fhv.minimudv3.map.field;

import at.fhv.minimudv3.game.player.Player;

/**
 * Describes the possibility to enter.
 * <p>
 * Created by Oliver H on 20.04.2017.
 */
public interface IAccessible {

    /**
     * Describes the possibility to enter
     *
     * @param player - The Player that wants to enter
     * @return - The new Position/Field
     */
    Field enter(Player player);
}
