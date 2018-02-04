package at.fhv.ohe.uebung3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fhv.ohe.uebung3.enums.Direction;
import at.fhv.ohe.uebung3.enums.FieldType;
import at.fhv.ohe.uebung3.factory.DistributionCenter;
import at.fhv.ohe.uebung3.factory.Trolley;
import at.fhv.ohe.uebung3.product.Fernseher;

public class TrolleyTest {

	@Test
	public void testMove() {
		try {
			DistributionCenter testDistri = new DistributionCenter(2, 2, 2, 2);
			Trolley steveTheTrolley = new Trolley(100, 2, 2, 0, 0, testDistri, "test");
			assertTrue("Fail to Move North", !steveTheTrolley.move(Direction.NORTH));
			assertTrue("Fail to Move West", !steveTheTrolley.move(Direction.WEST));
			assertTrue("Fail to Move East", steveTheTrolley.move(Direction.EAST));
			assertTrue("Fail to Move West", steveTheTrolley.move(Direction.WEST));
			assertTrue("Fail to Move SOUTH", steveTheTrolley.move(Direction.SOUTH));
			assertTrue("Fail to Move East", steveTheTrolley.move(Direction.EAST));
			assertTrue("Fail PositionX", steveTheTrolley.getPosition()[0] == 1);
			assertTrue("Fail PositionY", steveTheTrolley.getPosition()[1] == 1);
		} catch (Exception e) {
			fail("Exeption Fail");
		}
	}

	@Test
	public void testLoadUnload() {
		DistributionCenter testDistri = new DistributionCenter(2, 2, 100, 100);
		testDistri.setTypeToField(0, 0, FieldType.LOADING_STATION);
		testDistri.setTypeToField(1, 1, FieldType.UNLOADING_STATION);

		try {
			Trolley steveTheTrolley = new Trolley(100, 50, 50, 0, 0, testDistri, "test");
			assertTrue("Fail to Load1",steveTheTrolley.load(new Fernseher(50, 20, 20,"1")));
			assertTrue("Fail to Load2",steveTheTrolley.load(new Fernseher(48, 20, 20,"2")));
			assertTrue("Fail to Load3",!steveTheTrolley.load(new Fernseher(10, 20, 20,"3")));
			assertTrue("Fail to Load4",!steveTheTrolley.load(new Fernseher(1, 60, 20,"4")));
			assertTrue("Fail to Load5",!steveTheTrolley.load(new Fernseher(1, 20, 60,"5")));

			assertTrue("Fail to Move SOUTH", steveTheTrolley.move(Direction.SOUTH));
			assertTrue("Fail to Move East", steveTheTrolley.move(Direction.EAST));

			String unloadString = "";
			unloadString = steveTheTrolley.unload();
			assertTrue("UnloadMiss :(" + unloadString, unloadString.equals("Items on Trolley: [1] [2]"));

		} catch (Exception e) {
			fail("Fail to Load");
		}
	}


}
