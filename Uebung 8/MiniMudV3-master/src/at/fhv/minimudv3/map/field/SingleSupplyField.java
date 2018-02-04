package at.fhv.minimudv3.map.field;

import at.fhv.minimudv3.game.enums.ActionFunctions;
import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.items.Item;

import java.util.LinkedList;
import java.util.List;

/**
 * Describes a single supply field. It supplies only one Item per Player
 * <p>
 * Created by Oliver Heil on 26.04.2017.
 */
public class SingleSupplyField extends Field implements IExecutable, IAccessible {
    private static final String _SUCESSDESCR = "You got ";
    private static final String _ALREADYGOT = "There is nothing left for you...";
    private static final String _FAILDESCR = "You cant do this here...";

    private Item _supplyOf;
    private List<Player> _playerEx;

    /**
     * Creates an SingleSupply Field
     *
     * @param aItem            - The Item that should be supplied
     * @param shortDescription - The short description
     * @param longDescription  - The long description
     */
    public SingleSupplyField(Item aItem, String shortDescription, String longDescription) {
        super(shortDescription, longDescription);
        _supplyOf = aItem;
        _playerEx = new LinkedList<>();
    }

    /**
     * Add the player to the "already get one" list.
     *
     * @param player - The player already get one copy
     */
    private void giveItemTo(Player player) {
        addToInventorry(player);
        _playerEx.add(player);
    }

    /**
     * Give a copy of the specified item to the player
     *
     * @param player - The player that get this copy
     */
    private void addToInventorry(Player player) {
        player.addToInventory(Item.clone(_supplyOf));
    }

    /**
     * Returns {@code true} if the player already got one item from this field
     *
     * @param enterPlayer - The Player that should be checked
     * @return {@code boolean} - true if the player already got one item
     */
    private boolean gotAlreadyItem(Player enterPlayer) {
        for (Player player : _playerEx) {
            if (player == enterPlayer) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Player player, ActionFunctions actionFunctions) {
        if (actionFunctions == ActionFunctions.PICKUP) {
            if (!gotAlreadyItem(player)) {
                giveItemTo(player);
                player.outStream(_SUCESSDESCR + _supplyOf.getShortDescription());
            } else {
                player.outStream(_ALREADYGOT);
            }
        } else {
            player.outStream(_FAILDESCR);
        }
    }

    @Override
    public Field enter(Player player) {
        return this;
    }
}
