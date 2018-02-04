package at.fhv.ohe.uebung3.tests;

import static org.junit.Assert.*;

import com.sun.rmi.rmid.ExecPermission;
import org.junit.Rule;
import org.junit.Test;

import at.fhv.ohe.uebung3.enums.FieldType;
import at.fhv.ohe.uebung3.factory.DistributionCenter;
import org.junit.rules.ExpectedException;

public class DistributionCenterTest {

	@Test
	public void testConstruct() {
		DistributionCenter test = new DistributionCenter(10,10,2,2);
		int pos[][] = {{0,0},{9,9}};
		test.setTypeToField(pos, FieldType.LOADING_STATION);
		assertTrue("TypeCheck Fail", test.getFieldType(0, 0) == FieldType.LOADING_STATION);
		assertTrue("TypeCheck Fail", test.getFieldType(9, 9) == FieldType.LOADING_STATION);
		int pos2[][] = {{0,0},{9,9}};
		test.setTypeToField(pos2, FieldType.UNLOADING_STATION);
		assertTrue("TypeCheck Fail", test.getFieldType(0, 0) == FieldType.UNLOADING_STATION);
		assertTrue("TypeCheck Fail", test.getFieldType(9, 9) == FieldType.UNLOADING_STATION);
	}
	
	@Test
	public void testBuildTrolley() {
        DistributionCenter test = new DistributionCenter(10, 10, 100, 100);

        try {
			assertTrue("Fail to Build (1)", test.addTrolley(1, 1, 1, 0, 0, "Hy") != null);
        } catch (Exception e){
            fail("Fail Exeption (1)");
        }
        try {
            test.addTrolley(0, 0, 0, 0, 0, "Bu");
            fail("Fail to Build (2)");
        } catch (Exception e){
        }
        try {
            test.addTrolley(0, 0, 0, -2, 0, "Da");
            fail("Fail to Build (3)");
        } catch (Exception e){
        }
        try {
            test.addTrolley(0, 0, 0, 0, -2, "Fu");
            fail("Fail to Build (4)");
        } catch (Exception e){
        }

	}
}
