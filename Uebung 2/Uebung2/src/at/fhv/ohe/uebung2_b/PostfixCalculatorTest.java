package at.fhv.ohe.uebung2_b;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PostfixCalculatorTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public void testCalculate() {
		final String[][] testList = { 
				{"5 6 + ;", "11"}, 
				{"6 5 + ;", "11"},
				{"5 1 2 + 4 * + 3 - ;", "14"},
				{"1 2 + 4 * 5 + 3 - ;", "14"},
				{"1 ;", "1"}
		};
		
		for (int i = 0; i < testList.length; i++) {
			assertTrue("FAIL at " + i, PostfixCalculator.calculate(testList[i][0]) == Integer.parseInt(testList[i][1]));	
		}
		
		final String[][] testList2 = { 
				{"3 0 / ;", "0"}, 
				{"6 ? + ;", "0"},
				{"5 + 0 ;", "0"},
				{"5 0 5 6 6 ;", "0"}
		};
		for (int i = 0; i < testList2.length; i++) {
			thrown.expect(ArithmeticException.class);
			PostfixCalculator.calculate(testList2[i][0]);
			
		}
		
		
	}

}
