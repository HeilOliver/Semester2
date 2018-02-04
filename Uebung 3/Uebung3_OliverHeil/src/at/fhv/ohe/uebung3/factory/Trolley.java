package at.fhv.ohe.uebung3.factory;

import java.util.LinkedList;
import java.util.List;

import at.fhv.ohe.uebung3.enums.Direction;
import at.fhv.ohe.uebung3.enums.FieldType;

/**
 * The class describes a Trolley with basic functions. 
 * 
 * @author Oliver H
 * @version 1.0
 * @since 24.03.2017
 */
public class Trolley {
	private int 	_max_CargoWeight;
	private int 	_max_CargoWidth;
	private int 	_max_CargoLength;
	private final String	_TrolleyID;
	private DistributionCenter _distributionCenter;
	
	private int 	_posX;
	private int 	_posY;
	private List<Product> _loadedProducts;
	
	/**
	 * Generate a Trolley Object.
	 * 
	 * @param max_CargoWeight - the maximum cargo weight that the Trolley can handle.
	 * @param max_CargoWidth - the maximum cargo width that the Trolley can handle.
	 * @param max_CargoLength - the maximum cargo length that the Trolley can handle.
	 * @param posX - the X-Position in the {@link DistributionCenter}
	 * @param posY - the Y-Position in the {@link DistributionCenter}
	 * @param distributionCenter - The {@link DistributionCenter} it self.
	 * @param TrolleyID - a id to identify the trolley. Shout be unique.
	 */
	public Trolley(int max_CargoWeight, int max_CargoWidth, int max_CargoLength, int posX,
			int posY, DistributionCenter distributionCenter ,String TrolleyID) throws IllegalTrollyAttributes,IllegalTrollyPosition {
		_max_CargoWeight = max_CargoWeight;
		_max_CargoWidth = max_CargoWidth;
		_max_CargoLength = max_CargoLength;
		_posX = posX;
		_posY = posY;
		_TrolleyID = TrolleyID;
		_distributionCenter = distributionCenter;
		_loadedProducts = new LinkedList<>();
		_distributionCenter.addTrolley(this);
	}

	/**
	 * Return the maximum load width for a {@link Product}.
	 *
	 * @return int - The maximum load width
	 */
	public int getMax_CargoWidth() {
		return _max_CargoWidth;
	}

	/**
	 * Return the maximum load length for a {@link Product}.
	 *
	 * @return int - The maximum load length
	 */
	public int getMax_CargoLength() {
		return _max_CargoLength;
	}

	/**
	 * Return the Trolley ID
	 *
	 * @return String - the Trolley ID
	 */
	public String getId() {
		return _TrolleyID;
	}

	/**
	 * Return the Position of the Trolley
	 *
	 * @return int[] - an Array with X and Y position
	 */
	public int[] getPosition() {
		return new int[] {_posX,_posY};
	}

	/**
	 * Add a new {@link Product} to the Trolley
	 * 
	 * @param productToAdd - The {@link Product} to add.
	 * @return {@code true} - if the product is added; {@code false} if the product can not be add to this trolley. Reasons might be to heavy, to wide or to long.
	 */
	public boolean load(Product productToAdd) throws IllegalTrollyPosition {
		if (_distributionCenter.getFieldType(_posX, _posY, this) == FieldType.LOADING_STATION) {
			if (productToAdd.getWidth() <= _max_CargoWidth &&
					productToAdd.getLength() <= _max_CargoLength) {

				int myWeight = 0;
				for (Product item : _loadedProducts) {
					myWeight += item.getWeight();
				}
				if (myWeight + productToAdd.getWeight() <= _max_CargoWeight) {
					_loadedProducts.add(productToAdd);
					return true;
				}
			}
			return false;
		}
		throw new IllegalTrollyPosition();
	}
	
	/**
	 * Unload the Trolley if he is standing on an {@link FieldType} {@code UNLOADING_STATION} field. 
	 * 
	 * @return a {@link String} with all {@link Product}
	 * @throws IllegalTrollyPosition -if the Trolley is not standing on an {@link FieldType} {@code UNLOADING_STATION}.
	 */
	public String unload() throws IllegalTrollyPosition {
		if (_distributionCenter.getFieldType(_posX, _posY, this) == FieldType.UNLOADING_STATION){
			StringBuilder itemsOnTrolley = new StringBuilder();
			itemsOnTrolley.append("Items on Trolley:");
			for (Product item : _loadedProducts) {
				itemsOnTrolley.append(" [");
				itemsOnTrolley.append(item.getTypeID());
				itemsOnTrolley.append("]");
			}
			_loadedProducts.clear();
			return itemsOnTrolley.toString();
		}
		throw new IllegalTrollyPosition();
	}
	
	/**
	 * Move the Trolley to one Direction.
	 * 
	 * @param direction - the movement direction
	 * @return {@code true OR false} when the movement has been performed or not. 
	 */
	public boolean move(Direction direction){
		switch (direction) {
		case EAST:
			if(_distributionCenter.getFieldType(_posX + 1, _posY) != FieldType.OBSTACLE) {
				_posX += 1;
				return true;
			}
			break;
			
		case WEST:
			if(_distributionCenter.getFieldType(_posX - 1, _posY) != FieldType.OBSTACLE) {
				_posX -= 1;
				return true;
			}
			break;

		case SOUTH:
			if(_distributionCenter.getFieldType(_posX, _posY + 1) != FieldType.OBSTACLE) {
				_posY += 1;
				return true;
			}
			break;

		case NORTH:
			if(_distributionCenter.getFieldType(_posX, _posY - 1) != FieldType.OBSTACLE) {
				_posY -= 1;
				return true;
			}
			break;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "[ " + _TrolleyID + " X=" + _posX + " Y=" + _posY + " ItemsLoaded=" + _loadedProducts.size() + " ]";
	}
}
