package at.fhv.minimudv3.map;

import at.fhv.minimudv3.game.enums.Directions;
import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.field.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Oliver Heil on 26.04.2017.
 */
class MapControllerTest {
    private Field f0,f1,f2;
    private MapController mC;

    @BeforeEach
    void setUp() {
        f0 = new Field("tF0","test Field 0");
        f1 = new Field("tF1","test Field 1");
        f2 = new Field("tF2","test Field 2");
        f0.addBinding(Directions.NORTH,f1);
        f0.addBinding(Directions.WEST,f2);
        mC = new MapController(f0,"Test");
    }


    @Test
    void saveLoadMap() {
        try {
            mC.saveMap("test.map");
        } catch (Exception e) {
            fail("Save Map fail");
        }

        MapController mCL = null;
        try {
            mCL = MapController.loadSavedMap("test.map");
        } catch (IOException | ClassNotFoundException e) {
            fail(e.getMessage());
        }

        assertTrue(mCL.getName().equals(mC.getName()));
    }

    @Test
    void addPlayer() {
        try {
            mC.addPlayer("test");
        } catch (IllegalPlayerNameException e) {
            fail(e.getMessage());
        }

        try {
            mC.addPlayer("test");
            fail("Name shoud already exist");
        } catch (IllegalPlayerNameException e) {
        }
    }

    @Test
    void logout() {
        Player player =null;
        try {
            player = mC.addPlayer("test");
        } catch (IllegalPlayerNameException e) {
            fail("fail by insert player");
        }
        assertTrue(mC.lookForAllPlayerOnMap().size() == 1);
        mC.logout(player);
        assertTrue(mC.lookForAllPlayerOnMap().size() == 0);
    }

    @Test
    void lookForAllPlayerOnMap() {

        try {
            mC.addPlayer("0");
            mC.addPlayer("1");
        } catch (IllegalPlayerNameException e) {
            fail("fail by insert player");
        }
        assertTrue(mC.lookForAllPlayerOnMap().size() == 2);
        assertTrue(mC.lookForAllPlayerOnMap().get(0).getName().equals("0"));
        assertTrue(mC.lookForAllPlayerOnMap().get(1).getName().equals("1"));
    }

    public String s0 = "";
    public String s1 = "";
    @Test
    void talkToAll() {
        Player p0 = null ,p1 = null;

        try {
            p0 = mC.addPlayer("0");
            p1 = mC.addPlayer("1");
        } catch (IllegalPlayerNameException e) {
            fail("fail by insert player");
        }
        p0.setPlayerOutstream(s -> s0 =s);
        p1.setPlayerOutstream(s -> s1 =s);

        mC.talkToAll(p0,"test");

        assertTrue(s0.contains("test"));
        assertTrue(s1.contains("test"));
    }
}