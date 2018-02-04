package at.fhv.minimudv3.map.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Oliver H on 21.04.2017.
 */
class ItemTest {
    private Item i0,i1,i2;

    @BeforeEach
    void setUp() {
        i0 = new Item(10,"td0", "test description 0");
        i1 = new Item(100,"td1", "test description 1");
        i2 = new Item(1000,"td2", "test description 2");
    }

    @Test
    void cloneTest() {
        Item clonetest = Item.clone(i0);
        // Kein Hash Vergleich möglich -> Implementierung fehlt
        assertTrue(i0.getLongDescription().equals(clonetest.getLongDescription()));
        assertTrue(i0.getShortDescription().equals(clonetest.getShortDescription()));
        assertTrue(i0.getWeight() == clonetest.getWeight());
    }

    @Test
    void getWeightTest() {
        assertTrue(i0.getWeight() == 10);
        assertTrue(i1.getWeight() == 100);
        assertTrue(i2.getWeight() == 1000);
    }

    @Test
    void descriptionTest() {
        assertTrue(i0.getShortDescription().equals("td0"));
        assertTrue(i1.getShortDescription().equals("td1"));
        assertTrue(i2.getShortDescription().equals("td2"));
        assertTrue(i0.getLongDescription().equals("test description 0"));
        assertTrue(i1.getLongDescription().equals("test description 1"));
        assertTrue(i2.getLongDescription().equals("test description 2"));
    }

}