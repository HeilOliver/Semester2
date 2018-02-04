package at.fhv.ohe.uebung3.factory;

import at.fhv.ohe.uebung3.enums.FieldType;

/**
 * The class describes a field in a grid with certain properties.
 * 
 * @author Oliver H
 * @version 1.0
 * @since 24.03.2017
 */
public class Field {
	private static int 	_sWidth;
	private static int 	_sLength;
	private FieldType	_type;
	
	/**
	 * Creates an Field-object with {@link FieldType EMPTY} as {@link FieldType}
	 */
	public Field() {
		this(FieldType.EMPTY);
	}
	
	/**
	 * Creates an Field-object with the given Type
	 * 
	 * @param type - represent the {@link FieldType} of the field 
	 */
	public Field(FieldType type) {
		_type = type;
	}
	
	/**
	 * Return the width of one field
	 * 
	 * @return {@code int} - The width of one field
	 */
	public static int getWidth() {
		return _sWidth;
	}
	
	/**
	 * Return the length of one field
	 * 
	 * @return {@code int} - The length of one field
	 */
	public static int getLength() {
		return _sLength;
	}
	
	/**
	 * Return the length of one field
	 * 
	 * @return {@link FieldType} - The {@link FieldType} of one {@link Field}
	 */
	public FieldType getType() {
		return _type;
	}
	
	/**
	 * Set the type of a the field
	 * 
	 * @param type - represent the {@link FieldType} of the field 
	 */
	public void setType(FieldType type) {
		_type = type;
	}
	
	/**
	 * Change the width and the length of all Fields
	 * 
	 * @param width - The width of one field. Note that this affect all class members
	 * @param length - The length of one field. Note that this affect all class members
	 */
	public static void setDimensions(int width, int length) {
		_sWidth = width;
		_sLength = length;
	}
	
	@Override
	public String toString() {
		return "[ Field -Width=" + _sWidth + " -Length=" + _sLength + " -Type=" + _type.toString() + " ]";
	}
}
