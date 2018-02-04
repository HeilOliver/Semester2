package at.fhv.minimudv3.map.items;

import at.fhv.minimudv3.game.player.Player;

/**
 * The Interface for consumable Items
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public interface IConsumable {

    /**
     * The function for consuming
     *
     * @param player - The player that consume this Item
     */
    void consumeBy(Player player);

}
