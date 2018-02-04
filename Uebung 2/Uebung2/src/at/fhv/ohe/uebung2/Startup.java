package at.fhv.ohe.uebung2;


import at.fhv.ohe.uebung2_a.RotEncryption;
import at.fhv.ohe.uebung2_b.PostfixCalculator;
import at.fhv.ohe.uebung2_c.IdGenerator;

public class Startup {
 
	public static void main(String[] args) {

// Uebung 2 A
//		System.out.println("Uebung 2A \n");
//		String testText = "Hallo ich bin ein Text und werde von vielen gelesen :D!!!";
//		System.out.println(testText);
//		String encrypteText = RotEncryption.encrypteText(testText,5);
//		System.out.println(encrypteText);
//		String decrypteText = RotEncryption.decrypteText(encrypteText, 5);
//		System.out.println(decrypteText);
		
// Uebung 2 B
//		System.out.println("\n\nUebung 2B \n");
//		System.out.println("5 1 2 + 4 * + 3 - ; = " + PostfixCalculator.calculate("5 1 2 + 4 * + 3 - ;"));
//		System.out.println("10 5 + ; = " + PostfixCalculator.calculate("10 5 + ;"));
//		System.out.println("5 0 / ; = " + PostfixCalculator.calculate("5 0 / ;"));
		
// Uebung 2 C
		System.out.println("\n\nUebung 2C \n");
		IdGenerator test = new IdGenerator(35);
		int howmanyIds = 10000000;
		long start_time = System.nanoTime();
		test.generateList(howmanyIds);
		long end_time = System.nanoTime();
		System.out.println("Time to Generate " + howmanyIds + " IDs: " + (end_time-start_time)/1000000 + "ms");
		
//		for (String id : test.generateList(10)) {
//			System.out.println(id);
//		}		
	}

}
