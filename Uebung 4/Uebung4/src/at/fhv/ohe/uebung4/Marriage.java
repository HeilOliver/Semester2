package at.fhv.ohe.uebung4;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The class describes marriage Document.
 *
 * @author Oliver H
 * @version 1.0
 * @since 04.04.2017
 */
public class Marriage extends Document implements Serializable {
    private Person _groomsmanA;
    private Person _groomsmanB;

    /**
     *
     * @param personA
     * @param personB
     */
    public Marriage (Person personA, Person personB) {
        this(personA,personB,null,null);
    }

    /**
     * Creates an Marriage Document
     *
     * @param personA - the first Person
     * @param personB - the second Person
     * @param groomsmanA - the groomsman from the first Person
     * @param groomsmanB - the groomsman from the second Person
     */
    public Marriage (Person personA, Person personB, Person groomsmanA, Person groomsmanB) {
        super(personA,personB);
        _groomsmanA = groomsmanA;
        _groomsmanB = groomsmanB;
    }

    /**
     * Return the first spouse
     *
     * @return {@code {@link Person}} - a Person
     */
    public Person getSpouseA() {
        return super.getPersonA();
    }

    /**
     * Return the second spouse
     *
     * @return {@code {@link Person}} - a Person
     */
    public Person getSpouseB() {
        return super.getPersonB();
    }

    /**
     * Return the groomsmann from the first spouse
     *
     * @return {@code {@link Person}} - a Person
     */
    public Person getGroomsmanA() {
        return _groomsmanA;
    }

    /**
     * Return the groomsmann from the second spouse
     *
     * @return {@code {@link Person}} - a Person
     */
    public Person getGroomsmanB() {
        return _groomsmanB;
    }

    /**
     * Return the marriage Date.
     *
     * @return {@code {@link LocalDate}} - th marriage Date
     */
    public LocalDate getMarriageDate() {
        return super.getCreationDate();
    }

    public String toString() {
        return "[ Marriage: " + super.getPersonA().toString() + " | " +
                super.getPersonB().toString() + " | date: " + getMarriageDate().toString()  + " ]";
    }
}
