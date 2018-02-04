package at.fhv.ohe.uebung3.factory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.fhv.ohe.uebung3.enums.FieldType;

/**
 * The class describes a Distribution Center with basic functions. 
 * 
 * @author Oliver H
 * @version 1.0
 * @since 24.03.2017
 */
public class DistributionCenter {
	private Field[][] 		_fieldGrid;
	private List<Trolley>   _trolleyList;

	/**
	 * Generate a DistributionCenter Object.
	 * 
	 * @param gridWidth - The width of the Grid. Need to be {@code >0}. 
	 * @param gridLength - The length of the Grid. Need to be {@code >0}. 
	 * @param fieldWidth - The width of one Field in the grid. Need to be {@code >0}. 
	 * @param fieldLength - The length of one Field in the grid. Need to be {@code >0}. 
	 */
	public DistributionCenter(int gridWidth, int gridLength, int fieldWidth, int fieldLength) {
		if (gridLength <= 0 || gridWidth <= 0 || fieldWidth <= 0 || fieldLength <= 0) {
			throw new IllegalArgumentException("Wrong Input");
		}
		_trolleyList = new LinkedList<>();
		_fieldGrid = new Field[gridWidth][gridLength];
		Field.setDimensions(fieldWidth,fieldLength);
		
		
		for(int i= 0; i < gridWidth; i++) {
			for(int j =0; j < gridLength; j++){
				_fieldGrid[i][j] = new Field();
			}
		}
	}
	
	/**
	 * Set a specific {@link FieldType}} to some fields
	 * 
	 * @param positions - the X and Y position of the fields that shout be change
	 * @param type - the new type of the Field
	 */
	public void setTypeToField(int positions[][], FieldType type) {
		for (int i = 0; i< positions.length; i++) {
			setTypeToField(positions[i][0], positions[i][1], type);
		}
	}
	
	/**
	 * Set a specific {@link FieldType} to one field
	 * 
	 * @param fieldX - The X-Position of the field 
	 * @param fieldY - The Y-Position of the field
	 * @param type - the new type of the Field
	 */
	public void setTypeToField(int fieldX, int fieldY, FieldType type) {
		if (fieldX < 0 || fieldY < 0) {
			throw new IllegalArgumentException("Wrong Input @X" + fieldX + " Y" + fieldY);
		}
		_fieldGrid[fieldX][fieldY].setType(type);
	}
	
	/**
	 * Return the {@link FieldType} on the given position. If a Trolley is standing on this position it returns {@link FieldType} OBSTACLE
	 * 
	 * @param posX - The X-Position of the field
	 * @param posY - The Y-Position of the field
	 * @return the {@link FieldType} of the field
	 */
	public FieldType getFieldType(int posX, int posY) {
		return getFieldType(posX,posY,null);
	}
	
	/**
	 * Return the {@link FieldType} on the given position. If a Trolley is standing on this position it returns {@link FieldType} OBSTACLE
	 * 
	 * @param posX - The X-Position of the field
	 * @param posY - The Y-Position of the field
	 * @param ignoreTrolley - The {@link Trolley} that should be excepted
	 * @return the {@link FieldType} of the field
	 */
	public FieldType getFieldType(int posX, int posY, Trolley ignoreTrolley) {
		if (posX < 0 || !(posX < _fieldGrid.length) ||
				posY < 0 || !(posY < _fieldGrid[0].length)) {
			return FieldType.OBSTACLE;
		} else {
			for (Trolley trolley : _trolleyList) {
				if (trolley.getPosition()[0] == posX && trolley.getPosition()[1] == posY && trolley != ignoreTrolley) {
					return FieldType.OBSTACLE;
				}
			}
		}
		return _fieldGrid[posX][posY].getType();
	}
	
	/**
	 * Search a Trolley by his ID 
	 * 
	 * @param trolleyid - The {@link Trolley} ID
	 * @return {@code null} if no Tr{@link Trolley}olley with this ID is pressent, {@link Trolley} when a {@link Trolley} is found. Have more than one {@link Trolley} the same ID only the first added is return. 
	 */
	public Trolley getTrolleyByID(String trolleyid) {
		Iterator<Trolley> iterator = _trolleyList.listIterator();
		while (iterator.hasNext()) {
			Trolley trolley = iterator.next();
		    if (trolley.getId() == trolleyid) {
		    	return trolley;
		    }
		}
		return null;
	}
	
	/**
	 * Add a new Trolley into the grid
	 * 
	 * @param max_CargoWeight - the maximum cargo weight that the {@link Trolley} can handle.
	 * @param max_CargoWidth - the maximum cargo width that the {@link Trolley} can handle.
	 * @param max_CargoLength - the maximum cargo length that the {@link Trolley} can handle.
	 * @param posX - the X-Position in the {@link DistributionCenter}
	 * @param posY - the Y-Position in the {@link DistributionCenter}
	 * @param TrolleyID - a id to identify the trolley. Shout be unique.
	 * @return {@code null} if its not possible to create a new {@link Trolley}, {@link Trolley} when the {@link Trolley} is added successful
	 */
	public Trolley addTrolley(int max_CargoWeight, int max_CargoWidth, int max_CargoLength, int posX,
			int posY,String TrolleyID) throws IllegalTrollyAttributes,IllegalTrollyPosition{
		Trolley newTrolley = new Trolley(max_CargoWeight, max_CargoWidth, max_CargoLength, posX, posY, this, TrolleyID);
        return newTrolley;
	}

    /**
     * Add a new Trolley into the grid.
     *
     * @param newTrolley - The Trolley that shout be added.
     */
	public void addTrolley(Trolley newTrolley) throws IllegalTrollyPosition, IllegalTrollyAttributes{
	    if(getFieldType(newTrolley.getPosition()[0],newTrolley.getPosition()[1]) == FieldType.OBSTACLE) {
            throw new IllegalTrollyPosition();
        }
        if (newTrolley.getMax_CargoWidth() > Field.getWidth() || newTrolley.getMax_CargoLength() > Field.getLength()) {
            throw new IllegalTrollyAttributes();
        }
		_trolleyList.add(newTrolley);
	}
	
	/**
	 * Delete the given {@link Trolley} from the grid.
	 * 
	 * @param trolleyToDelete - The {@link Trolley} that shout be deleted
	 * @return {@code true} if the {@link Trolley} is deleted successful, {@code false} if the {@link Trolley} is not found.
	 */
	public boolean deleteTrolley (Trolley trolleyToDelete) {
		return _trolleyList.remove(trolleyToDelete);
	}

	@Override
	public String toString() {
		return "[ DistCenter -Width=" + _fieldGrid.length + " -Length=" + _fieldGrid[0].length + " -TrolleysIn=" + _trolleyList.size() + " ]";
	}
}
