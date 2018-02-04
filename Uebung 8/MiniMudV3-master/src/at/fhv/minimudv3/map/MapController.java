package at.fhv.minimudv3.map;

import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.field.Field;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Handels the Map
 * Here you can add new Player, Talk to another Player, ...
 * <p>
 * Created by Oliver H on 15.04.2017.
 */
public class MapController implements Serializable {
    private String _mapName;
    private Field _PlayerSporneField;
    private List<Player> _playerPlayOnMap;
    private List<Player> _playerSavedOnMap;

    /**
     * Creates a new MapController
     *
     * @param _startField - The start Field
     * @param mapName     - The Map Name
     */
    public MapController(Field _startField, String mapName) {
        _PlayerSporneField = _startField;
        _playerPlayOnMap = new LinkedList<>();
        _playerSavedOnMap = new LinkedList<>();
        _mapName = mapName;
    }

    /**
     * Load a map from a given Path
     *
     * @param path - The Path of the map
     * @return {@code {@link MapController}} - The Map controller for this map
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static MapController loadSavedMap(String path) throws IOException, ClassNotFoundException {
        MapController temp;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);
            temp = (MapController) objectInputStream.readObject();
            temp._playerPlayOnMap.clear();
            System.out.println("Successful loaded Map");
        } finally {
            objectInputStream.close();
            fileInputStream.close();    // TODO Das kann auch böse enden NullPointer :(
        }
        return temp;
    }

    /**
     * Save the complete Map with all Player
     *
     * @param mapPath Where should the map be saved with filename
     */
    public synchronized void saveMap(String mapPath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(mapPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Map is saved in " + mapPath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Check if the name is free to use
     *
     * @param name - The Name that shoud be checked
     * @return - True if the Name is free
     */
    private boolean isNameFree(String name) {
        for (Player player : _playerSavedOnMap) {
            if (player.getName() == name) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a new Player to the Map
     *
     * @param name - The Name
     * @return - The new Player
     * @throws IllegalPlayerNameException
     */
    private Player addNewPlayer(String name) throws IllegalPlayerNameException {
        if (!isNameFree(name)) {
            throw new IllegalPlayerNameException("Name is already used :(");
        }
        Player newPlayer = new Player(name, _PlayerSporneField);
        _playerSavedOnMap.add(newPlayer);
        _playerPlayOnMap.add(newPlayer);
        return newPlayer;
    }

    /**
     * Add a new Player or an already existing saved Player to the Map
     *
     * @param name - The Name of the Player
     * @return {@code {@link Player}} - The Player
     * @throws IllegalPlayerNameException
     */
    public synchronized Player addPlayer(String name) throws IllegalPlayerNameException {
        for (Player player : _playerSavedOnMap) {
            if (player.getName().equals(name)) {
                if (!_playerPlayOnMap.contains(player)) {
                    _playerPlayOnMap.add(player);
                    return player;
                }
            }
        }
        return addNewPlayer(name);
    }

    /**
     * Logout a player from the map.
     *
     * @param player - The Player
     */
    public synchronized void logout(Player player) {
        if (_playerPlayOnMap.contains(player)) {
            _playerPlayOnMap.remove(player);
        }
        if (!_playerSavedOnMap.contains(player)) {
            _playerSavedOnMap.add(player);
        }
    }

    /**
     * Return all player on the field
     *
     * @param field - The field where should be looked at
     * @return {@code List<Player>} - A List of all player on this field
     */
    private synchronized List<Player> getAllPlayerOnPosition(Field field) {
        List<Player> temp = new LinkedList<>();
        for (Player player : _playerPlayOnMap) {
            if (player.currPosition() == field) {
                temp.add(player);
            }
        }
        return temp;
    }

    /**
     * Return all connected Player
     *
     * @return {@code List<Player>} - The List of all player
     */
    public synchronized List<Player> lookForAllPlayerOnMap() {
        return Collections.unmodifiableList(_playerPlayOnMap);
    }

    /**
     * Return the Name of this Map
     *
     * @return {@code String} - The map name
     */
    public String getName() {
        return _mapName;
    }

    /**
     * Talk to all player on the same position
     *
     * @param player  - The player that want to talk
     * @param message - The message
     */
    public synchronized void talkToAll(Player player, String message) {
        List<Player> temp = getAllPlayerOnPosition(player.currPosition());
        for (Player player1 : temp) {
            player1.outStream((player1 == player) ? "You Say: " : player.getName() + " say: ");
            player1.outStream(message);
        }
    }
}