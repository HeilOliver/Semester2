package at.fhv.minimudv3.map.field;

import at.fhv.minimudv3.game.enums.Directions;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * The Class describes a field in the Mud. You can bind to fields together
 * <p>
 * Created by Oliver H on 20.04.2017.
 */
public class Field implements Serializable {
    // Statics
    private static int _nextID;

    // Variables
    private Map<Directions, Field> _fieldBindings;
    private int _fieldID;
    private String _shortDescription;
    private String _longDescription;

    // Constructors

    /**
     * Creates an Field without any functions
     *
     * @param shortDescription - The short description
     * @param longDescription  - The long description
     */
    public Field(String shortDescription, String longDescription) {
        _shortDescription = shortDescription;
        _longDescription = longDescription;
        _fieldBindings = new EnumMap<Directions, Field>(Directions.class);
        _fieldID = _nextID++;
    }

    // Functions

    /**
     * Adds a binding with a another Field.
     * The binding will be also added in the another field in the reverse direction
     *
     * @param dir   - The {@code {@link Directions}} where you like to bind your field
     * @param field - The {@code {@link Field}} that you want to bind with this field
     */
    public void addBinding(Directions dir, Field field) {
        _fieldBindings.put(dir, field);
        field._fieldBindings.put(Directions.getReverse(dir), this);
    }

    /**
     * Returns all possible walk directions from this field
     *
     * @return - A map of all possible walk directions
     */
    public Map<Directions, IAccessible> getPossibleWalkDirections() {
        Map<Directions, IAccessible> tempMap = new EnumMap<>(Directions.class);
        _fieldBindings.forEach(((directions, field) -> {
            if (field instanceof IAccessible) {
                tempMap.put(directions, (IAccessible) field);
            }
        }));
        return Collections.unmodifiableMap(tempMap);
    }

    /**
     * Returns all connected directions of this field
     *
     * @return - A map of all directions that are connected with something
     */
    public Map<Directions, Field> getAllDirections() {
        return Collections.unmodifiableMap(_fieldBindings);
    }

    // Getter / Setter

    /**
     * Returns the unique ID for this field. The ID its automatically generated.
     *
     * @return - The Field id
     */
    public int getFieldID() {
        return _fieldID;
    }

    /**
     * Returns the short description of this field
     *
     * @return - The short description
     */
    public String getShortDescription() {
        return _shortDescription;
    }

    /**
     * Returns the long description of this field
     *
     * @return - The long description
     */
    public String getLongDescription() {
        return _longDescription;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldID=" + _fieldID +
                _fieldBindings.toString() +
                '}';
    }
}
