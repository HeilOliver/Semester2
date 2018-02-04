package at.fhv.minimudv3.map;

import at.fhv.minimudv3.game.enums.Directions;
import at.fhv.minimudv3.map.field.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Oliver H on 20.04.2017.
 */
class FieldTest {
    private Field f0,f1,f2;

    @BeforeEach
    void setUp() {
        f0 = new Field("tF0","test Field 0"); // Only Field
        f1 = new Field("tF1","test Field 1");
        f2 = new Field("tF2","test Field 2"); //
    }

    @Test
    void addBinding() {
        f0.addBinding(Directions.NORTH,f1);

        assertTrue(f0.getAllDirections().size() == 1);
        assertTrue(f0.getAllDirections().containsKey(Directions.NORTH));

        assertTrue(f1.getAllDirections().size() == 1);
        assertTrue(f1.getAllDirections().containsKey(Directions.SOUTH));
    }

    @Test
    void getPossibleWalkDirections() {
        TestIAccessibleField af0 = new TestIAccessibleField();
        TestIAccessibleField af1 = new TestIAccessibleField();

        af0.addBinding(Directions.NORTH,af1);
        af0.addBinding(Directions.WEST,f0);

        assertTrue(af0.getPossibleWalkDirections().size() == 1);
        assertTrue(af1.getPossibleWalkDirections().size() == 1);

        assertTrue(af0.getPossibleWalkDirections().containsKey(Directions.NORTH));
        assertTrue(af0.getPossibleWalkDirections().get(Directions.NORTH) == af1);

        assertTrue(af1.getPossibleWalkDirections().containsKey(Directions.SOUTH));
        assertTrue(af1.getPossibleWalkDirections().get(Directions.SOUTH) == af0);
    }

    @Test
    void getAllDirections() {
        f0.addBinding(Directions.NORTH,f1);
        f0.addBinding(Directions.SOUTH,f1);
        f0.addBinding(Directions.EAST,f1);
        f0.addBinding(Directions.WEST,f1);

        assertTrue(f0.getAllDirections().size() == 4);
    }

    @Test
    void getFieldID() {
        assertFalse(f0.getFieldID() == f1.getFieldID());
        assertFalse(f1.getFieldID() == f2.getFieldID());
        assertFalse(f2.getFieldID() == f0.getFieldID());
    }

    @Test
    void getShortDescription() {
        assertTrue(f0.getShortDescription() == "tF0");
        assertTrue(f1.getShortDescription() == "tF1");
    }

    @Test
    void getLongDescription() {
        assertTrue(f0.getLongDescription() == "test Field 0");
        assertTrue(f1.getLongDescription() == "test Field 1");
    }


}