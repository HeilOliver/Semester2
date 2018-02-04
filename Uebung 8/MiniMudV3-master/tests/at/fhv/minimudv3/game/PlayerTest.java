package at.fhv.minimudv3.game;

import at.fhv.minimudv3.game.enums.Directions;
import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.items.Item;
import at.fhv.minimudv3.game.enums.ActionFunctions;
import at.fhv.minimudv3.map.field.Field;
import at.fhv.minimudv3.map.TestExecutableField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Oliver Heil on 26.04.2017.
 */
class PlayerTest {
    private Player p0,p1;
    private Field f0,f1;


    @BeforeEach
    void setUp() {
        f0 = new TestField();
        f1 = new TestField();
        f0.addBinding(Directions.NORTH,f1);

        p0 = new Player("Bob", f0);
        p1 = new Player("Alice", f0);
    }

    @Test
    void getName() {
        assertTrue(p0.getName().equals("Bob"));
        assertTrue(p1.getName().equals("Alice"));
    }

    @Test
    void getAllItemsfromType() {
        Item item0 = new Item(10, "", "");
        Item item1 = new Item(1, "", "");
        Item item2 = new TestItem(2);

        p0.addToInventory(item0);
        p0.addToInventory(item1);
        p0.addToInventory(item2);

        assertTrue(p0.getAllItemsfromType(Item.class).size() == 3);
        assertTrue(p0.getAllItemsfromType(TestItem.class).size() == 1);
    }

    @Test
    void deleteFromInventory() {
        Item item0 = new Item(10, "", "");
        Item item1 = new Item(1, "", "");
        Item item2 = new TestItem(2);

        p0.addToInventory(item0);
        p0.addToInventory(item1);
        p0.addToInventory(item2);

        p0.deleteFromInventory(item0);
        p0.deleteFromInventory(item1);
        p0.deleteFromInventory(item2);

        assertTrue(p0.getAllItemsfromType(Item.class).size() == 0);
    }

    @Test
    void addToInventory() {
        Item item0 = new Item(10,"","");
        Item item1 = new Item(1,"","");
        p0.addToInventory(item0);
        p0.addToInventory(item1);
        assertTrue(p0.getAllItemsfromType(Item.class).size() == 2);
        assertTrue(p0.getAllItemsfromType(Item.class).contains(item0));
        assertTrue(p0.getAllItemsfromType(Item.class).contains(item1));

        Item item2 = new Item(1000,"","");
        assertTrue(p0.getAllItemsfromType(Item.class).size() == 2);
        assertFalse(p0.getAllItemsfromType(Item.class).contains(item2));
    }

    @Test
    void healthTest() {
        p0.healthIncrease(22);

        assertTrue(p0.getHealth() == 100);
        p0.healthReduction(44);
        assertTrue(p0.getHealth() == 100-44);
        p0.healthIncrease(40);
        assertTrue(p0.getHealth() == 96);
    }

    @Test
    void move() {
        p0.setPlayerOutstream((s -> s = s));    // Damit die Konsole leer bleibt
        assertTrue(p0.currPosition() == f0);
        p0.move(Directions.SOUTH);
        assertTrue(p0.currPosition() == f0);
        p0.move(Directions.WEST);
        assertTrue(p0.currPosition() == f0);
        p0.move(Directions.EAST);
        assertTrue(p0.currPosition() == f0);
        p0.move(Directions.NORTH);
        assertTrue(p0.currPosition() == f1);
    }

    String s1 = "";
    @Test
    void outStream() {
        p0.setPlayerOutstream((s -> this.s1 = s));
        assertTrue(s1.equals(""));
        p0.outStream("Test");
        assertTrue(s1.equals("Test"));
    }

    @Test
    void executableTest() {
        TestExecutableField field = new TestExecutableField();
        p0 = new Player("",field);
        assertTrue(p0.getHealth() == 100);
        p0.execute(ActionFunctions.PICKUP);
        assertTrue(p0.getHealth() == 40);
        p0.execute(null);
        assertTrue(p0.getHealth() == 30);
    }


    Boolean death = false;
    @Test
    void playerDeath() {
        p0.setPlayerDeathCallBack(() -> death = true);
        assertFalse(death);
        p0.healthReduction(200);
        assertTrue(death);
    }

}