package at.fhv.minimudv3.map.field;

import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.items.Item;
import at.fhv.minimudv3.map.items.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a basic door with an enter function. If the Player want to enter the door he need a key.
 * <p>
 * Created by Oliver H on 21.04.2017.
 */
public class Door extends Field implements IAccessible {
    private static final String _DOOROPENSUCE = "The door opens magically";
    private static final String _DOOROPENFAIL = "I have nothing to open";
    private int _keyID;
    private List<Player> _playerEx;

    // Constructor

    /**
     * Creates an Door
     *
     * @param keyID            - The KeyID to Open the Door
     * @param shortDescription - The short description
     * @param longDescription  - The long description
     */
    public Door(int keyID, String shortDescription, String longDescription) {
        super(shortDescription, longDescription);
        _keyID = keyID;
        _playerEx = new ArrayList<>();
    }

    // Functions

    /**
     * Opens the door for given player
     *
     * @param player - The player
     */
    private void openDoorFor(Player player) {
        _playerEx.add(player);
    }

    /**
     * Check if the door is open for given player
     *
     * @param player - The player
     * @return - If the door open
     */
    private boolean isTheDoorOpenfor(Player player) {
        return _playerEx.contains(player);
    }

    @Override
    public Field enter(Player player) {
        if (!isTheDoorOpenfor(player)) {
            for (Item item : player.getAllItemsfromType(Key.class)) {
                if (_keyID == ((Key) item).getKeyID()) {
                    openDoorFor(player);
                    player.deleteFromInventory(item);
                    player.outStream(_DOOROPENSUCE);
                    return this;
                }
            }
            return player.currPosition();
        }
        player.outStream(_DOOROPENFAIL);
        return this;
    }
}
