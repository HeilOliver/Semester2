package at.fhv.minimudv3.map.items;

import java.io.*;

/**
 * Describes a basic Item.
 * <p>
 * Created by Oliver H on 21.04.2017.
 */
public class Item implements Serializable {
    // Variables
    private int _weight;
    private String _shortDescription;
    private String _longDescription;

    // Constructors

    /**
     * Creates an basic Item. The weight cant be negative or zero.
     *
     * @param weight           - The weight of this Item -> (weight > 0)
     * @param shortDescription - The short description
     * @param longDescription  - The long description
     */
    public Item(int weight, String shortDescription, String longDescription) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weigth cant be negative or zero");
        }
        _weight = weight;
        _shortDescription = shortDescription;
        _longDescription = longDescription;
    }

    // Functions

    /**
     * Performe a deep coppie of the given Item
     *
     * @param item - The item that should be copied
     * @return - The copy
     */
    public static Item clone(Item item) {
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(item);

            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
            ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
            return (Item) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Getter / Setter

    /**
     * Return the weight of this Item
     *
     * @return - The weight
     */
    public int getWeight() {
        return _weight;
    }

    /**
     * Returns the short description of this field
     *
     * @return - The short description
     */
    public String getShortDescription() {
        return _shortDescription;
    }

    /**
     * Returns the long description of this field
     *
     * @return - The long description
     */
    public String getLongDescription() {
        return _longDescription;
    }
}