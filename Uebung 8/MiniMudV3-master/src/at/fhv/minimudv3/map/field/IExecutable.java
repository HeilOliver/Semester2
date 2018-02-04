package at.fhv.minimudv3.map.field;

import at.fhv.minimudv3.game.enums.ActionFunctions;
import at.fhv.minimudv3.game.player.Player;

/**
 * Describes the possibility to do something
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public interface IExecutable {

    /**
     * If you run this function you do something
     *
     * @param player          - The player that execute this Field
     * @param actionFunctions - What Action do the player perform
     */
    void execute(Player player, ActionFunctions actionFunctions);
}
