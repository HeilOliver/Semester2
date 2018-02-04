package at.fhv.ohe.uebung3.product;

import at.fhv.ohe.uebung3.factory.Product;

/**
 * Ein Toaster :D
 * 
 * @author Oliver H
 *
 */
public class Toaster extends Product{
	
	public Toaster(int weight, int width, int length) {
		this(weight,width,length, "JavaToast");
	}
	
	public Toaster(int weight, int width, int length, String id) {
		super(weight, width, length, id);
	}
	
	// Hier könnte Ihre Werbung stehen

}