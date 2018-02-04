package at.fhv.ohe.uebung3.factory;

/**
 * The abstract class describes the basic attributes for a product that is been used by {@link Trolley}.
 * 
 * @author Oliver H
 * @version 1.0
 * @since 24.03.2017
 */
public abstract class Product {
	private int 	_weight;
	private int 	_width;
	private int 	_length;
	private String	_typeID;
	
	/**
	 * Generate a Product Object that can be used in a {@link Trolley}.
	 * 
	 * @param weight - The total weight of the product
	 * @param width - The outer width of the product
	 * @param length - The outer length of the product
	 * @param typeID - A id that describe the product
	 */
	public Product(int weight, int width, int length, String typeID) {
		_weight = weight;
		_width = width;
		_length = length;
		_typeID = typeID;
	}

	/**
	 * Return the weight of the product
	 * 
	 * @return {@code int} - the given weight
	 */
	public int getWeight() {
		return _weight;
	}
	
	/**
	 * Return the width of the product
	 * 
	 * @return {@code int} - the given width
	 */
	public int getWidth() {
		return _width;
	}
	
	/**
	 * Return the length of the product
	 * 
	 * @return {@code int} - the given length
	 */
	public int getLength() {
		return _length;
	}
	
	/**
	 * Return the TypeID of the product
	 * 
	 * @return {@link String} - the given TypeID
	 */
	public String getTypeID() {
		return _typeID;
	}
}
