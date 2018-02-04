package at.fhv.ohe.uebung2_c;

import java.util.HashSet;

/**
 * A class for generating unique ids with a given length.
 * 
 * @author      Oliver Heil - fhv.at
 * @version     1.0
 * @since   	2017-03-17
 */
public class IdGenerator {
	private HashSet<String> 	_hashList;
	private int 				_idLength;
	
	/**
	 * Creates an IdGenerator Object. All Ids that are created are unique.
	 * ID are only letters from 'a' to 'z'
	 * 
	 * @param idLength - The ID length
	 */
	public IdGenerator(int idLength) {
		_idLength = idLength;
		_hashList = new HashSet<String>(10,(float) 0.5);
	}

	/**
	 * Return the ID creation length
	 * 
	 * @return - The ID length
	 */
	public int getIdLength() {
		return _idLength;
	}

	/**
	 * Set the ID creation length. The saved IDs will be reseted if ID-length change. 
	 * 
	 * @param idLength - The new ID length
	 */
	public void setIdLength(int idLength) {
		if (_idLength != idLength) {
			_idLength = idLength;
			_hashList = new HashSet<String>(10,(float) 0.5);
		}
	}
	
	/**
	 * Generate a List with unique IDs
	 * 
	 * @param quantity - How many Ids do you like
	 * @return a hash List with unique IDs
	 */
	public HashSet<String> generateList(int quantity){
		HashSet<String> idList =  new HashSet<String>(quantity);
		
		String newID = new String();
		int i = 0;
		
		while (i < quantity && _hashList.size() < Math.pow(26,_idLength)) {
			newID = getRandomID();
			if (!checkIfIdExist(newID)) {
				idList.add(newID);
				_hashList.add(newID);
				i++;
			}	
		}
		return idList;
	}
	
	/**
	 * Add one ID to the generatedId list
	 * 
	 * @param id - The ID that shout be added
	 */
	public void addIdToList (String id) {
		_hashList.add(id);
	}
	
	/**
	 * Check if the ID is already generated
	 * 
	 * @param id - The ID that shout be tested
	 * @return True or False
	 */
	public boolean checkIfIdExist (String id){
		return _hashList.contains(id);
	}
	
	/**
	 * Generate a ID with a given length
	 * 
	 * @return a maybe not unique ID
	 */
	public String getRandomID() {
		StringBuilder newID = new StringBuilder();
		for (int i = 0; i < _idLength; i++){
			newID.append(Character.toChars('a' + (int)(Math.random()*26)));
		}
		return newID.toString();
	}
}
