package at.fhv.ohe.uebung3.product;

import at.fhv.ohe.uebung3.factory.Product;

/**
 * Ein Fernseher :D
 * 
 * @author Oliver H
 *
 */
public class Fernseher extends Product{
		
	public Fernseher(int weight, int width, int length) {
		this(weight,width,length, "JavaTV");
	}
	
	public Fernseher(int weight, int width, int length, String id) {
		super(weight, width, length, id);
	}

	// Hier könnte Ihre Werbung stehen
	
}
