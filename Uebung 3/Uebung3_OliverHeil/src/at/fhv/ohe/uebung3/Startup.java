package at.fhv.ohe.uebung3;

import at.fhv.ohe.uebung3.enums.Direction;
import at.fhv.ohe.uebung3.enums.FieldType;
import at.fhv.ohe.uebung3.factory.DistributionCenter;
import at.fhv.ohe.uebung3.factory.IllegalTrollyAttributes;
import at.fhv.ohe.uebung3.factory.IllegalTrollyPosition;
import at.fhv.ohe.uebung3.factory.Trolley;
import at.fhv.ohe.uebung3.product.Fernseher;
import at.fhv.ohe.uebung3.product.Toaster;

public class Startup {

	public static void main(String[] args) {
		// Example for Uebung 3
		DistributionCenter myFactory = new DistributionCenter(2,2,200,200);
		myFactory.setTypeToField(0, 0, FieldType.LOADING_STATION);
		myFactory.setTypeToField(1, 1, FieldType.UNLOADING_STATION);
		myFactory.setTypeToField(1, 0, FieldType.OBSTACLE);

		try {
			myFactory.addTrolley(200, 150, 150, 0, 0, "MyFirstTrolley");
		} catch (Exception e) {
			return;
		}
		Trolley steve = myFactory.getTrolleyByID("MyFirstTrolley");
		System.out.println(myFactory);
		System.out.println(steve);

		try {
			steve.load(new Toaster(20, 60, 100));
			steve.load(new Fernseher(20, 60, 100));
			steve.load(new Toaster(50, 100, 100));
		} catch (IllegalTrollyPosition illegalTrollyPosition) {
			System.out.println("NOPE I don´t like my position :(");
		}
		System.out.println(steve);
		
		steve.move(Direction.SOUTH);
		System.out.println(steve);
		steve.move(Direction.EAST);
		System.out.println(steve);
		
		try {
			System.out.println(steve.unload());
		} catch(Exception e) {
			System.out.println("NOPE I don´t like :(");
		}
		System.out.println(steve);
		
		myFactory.deleteTrolley(steve);
		System.out.println(myFactory);
	}
}
