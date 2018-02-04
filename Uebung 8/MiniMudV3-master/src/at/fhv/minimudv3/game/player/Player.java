package at.fhv.minimudv3.game.player;

import at.fhv.minimudv3.game.enums.ActionFunctions;
import at.fhv.minimudv3.game.enums.Directions;
import at.fhv.minimudv3.map.field.Field;
import at.fhv.minimudv3.map.field.IAccessible;
import at.fhv.minimudv3.map.field.IExecutable;
import at.fhv.minimudv3.map.items.IConsumable;
import at.fhv.minimudv3.map.items.Item;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Describes a Player on a game Field
 * <p>
 * Created by Oliver H on 21.04.2017.
 */
public class Player implements Serializable {
    private static final int _maxWeight = 200;
    private static final int _maxHealth = 100;
    private static final String _NOTEXECUTEABLE = "There is nothing to do..";
    private static final String _NOWALKDIRECTION = "You cant move in this Direction!!";
    private static final String _NOTCONSUMABLE = "I cant consume this!";

    private String _name;
    private int _health;
    private Field _position;
    private List<Item> _carry;
    private IPlayerOutstream _playerOutstream;
    private IPlayerDeath _playerDeathCallBack;

    /**
     * Creates an Player with given parameter
     *
     * @param name     - The name of the player
     * @param position - The first position of the player
     */
    public Player(String name, Field position) {
        _name = name;
        _health = _maxHealth;
        _position = position;
        _carry = new LinkedList<>();
    }

    //Getter /Setter

    /**
     * Return the Name of the Field
     *
     * @return {@code String} - The Name of the Player
     */
    public String getName() {
        return _name;
    }

    // Inventory Action

    /**
     * Returns all Items that the Player cary from given Type
     *
     * @param type - The Type
     * @return {@code List<Item>} - A list with all Items
     */
    public List<Item> getAllItemsfromType(Class type) {
        List<Item> returnList = new LinkedList<>();
        for (Item item : _carry) {
            if (type.isInstance(item)) {
                returnList.add(item);
            }
        }
        return Collections.unmodifiableList(returnList);
    }

    /**
     * Delete the given Item from Inventory
     *
     * @param item - The Item that should be removed
     */
    public void deleteFromInventory(Item item) {
        _carry.remove(item);
    }

    /**
     * Add a Item to the Player Inventory
     *
     * @param supplyOf - The item that should be added
     */
    public void addToInventory(Item supplyOf) {
        int weight = supplyOf.getWeight();
        for (Item item : _carry) {
            weight += item.getWeight();
        }
        if (weight <= _maxWeight) {
            _carry.add(supplyOf);
        }
    }

    // Player Stats

    /**
     * Increase the health of the player by given value.
     *
     * @param of - the value of the health increase
     */
    public void healthIncrease(int of) {
        _health += of;
        if (_health > _maxHealth) {
            _health = _maxHealth;
        }
    }

    /**
     * Decrease the health of the player by given Value
     *
     * @param of - the value of the health decrease
     */
    public void healthReduction(int of) {
        _health -= of;
        if (_health < 0) {
            onDeath();
        }
    }

    /**
     * Return the current Health
     *
     * @return {@code int} - The Health
     */
    public int getHealth() {
        return _health;
    }

    /**
     * This happen when the player died :(
     */
    private void onDeath() {
        if (_playerDeathCallBack != null) {
            _playerDeathCallBack.death();
        } else {
            _health = 0;
        }
    }

    /**
     * What happen if the player died :(
     *
     * @param playerDeathCallBack - The Callback that executed
     */
    public void setPlayerDeathCallBack(IPlayerDeath playerDeathCallBack) {
        _playerDeathCallBack = playerDeathCallBack;
    }

    // Player Possibility's

    /**
     * Move the player in a given direction.
     *
     * @param direction - The direction where the player want to move
     */
    public void move(Directions direction) {
        Map<Directions, IAccessible> possibleWalkDirections = _position.getPossibleWalkDirections();
        if (possibleWalkDirections.containsKey(direction)) {
            _position = possibleWalkDirections.get(direction).enter(this);
        } else {
            outStream(_NOWALKDIRECTION);
        }
    }

    /**
     * Returns all Neighbors of this Field
     *
     * @return {@code Map<Directions,Field>} - All Neighbors
     */
    public Map<Directions, Field> getNeighbors() {
        // TODO Kann man das hier lassen oder sollte das zum Field?
        return Collections.unmodifiableMap(_position.getAllDirections());
    }

    /**
     * Do something on this Field
     *
     * @param actionFunctions - What should the Player do
     */
    public void execute(ActionFunctions actionFunctions) {
        if (_position instanceof IExecutable) {
            ((IExecutable) _position).execute(this, actionFunctions);
        } else {
            outStream(_NOTEXECUTEABLE);
        }
    }

    /**
     * Consume an Item
     *
     * @param item - The Item that should be consumed
     */
    public void consume(Item item) {
        // TODO das gleiche hier macht das sinn oder item direkt consume
        if (item instanceof IConsumable) {
            ((IConsumable) item).consumeBy(this);
        } else {
            outStream(_NOTCONSUMABLE);
        }
    }

    /**
     * Return the current position of the Player
     *
     * @return {@code {@link Field}} - The current Position
     */
    public Field currPosition() {
        return _position;
    }

    /**
     * Set the player outstream for this player
     *
     * @param playerOutstream
     */
    public void setPlayerOutstream(IPlayerOutstream playerOutstream) {
        _playerOutstream = playerOutstream;
    }

    /**
     * The output stream for this player
     *
     * @param s - The string to print
     */
    public void outStream(String s) {
        if (_playerOutstream != null) {
            _playerOutstream.print(s);
        } else {
            System.out.println(s);
        }
    }
}
