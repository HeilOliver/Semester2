package at.fhv.ohe.uebung2_c;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class IdGeneratorTest {

	@Test
	public void testGenerateList() {
		for (int i = 0; i < 2; i++) {
			int length = (int)(Math.random()*5);
			IdGenerator testObj = new IdGenerator(length);
			HashSet<String> testList = testObj.generateList((int)Math.pow(26, length));
		     
			for (String s : testList) {
				int count = 0;
				for (String j : testList) {
					if (s.equals(j)) {
						count++;
					}
				}
				assertTrue("Fail - not unique", count <= 1);
			}
			
			assertTrue("Fail - quantity of items", testList.size() == Math.pow(26, length));
		}
	}

	@Test
	public void testGetRandomID() {
		for (int i = 0; i < 1000000; i++) {
			int length = (int)(Math.random()*1000);
			IdGenerator testObj = new IdGenerator(length);
			String testID = testObj.getRandomID();
			assertTrue("Fail with length: " + length, testID.length() == length);
			
			for (int j = 0; i < testID.length(); i++) {
				assertTrue("Fail witch caracter", testID.charAt(j) >= 'a' && testID.charAt(j) <= 'z');
			}
		}
	}

}
