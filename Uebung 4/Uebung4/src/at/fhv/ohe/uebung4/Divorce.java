package at.fhv.ohe.uebung4;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The class describes an divorce document.
 *
 * @author Oliver H
 * @version 1.0
 * @since 04.04.2017
 */
public class Divorce extends Document implements Serializable{
    private String _reason;

    /**
     * Creates a new divorce Object
     *
     * @param personA - The first Person
     * @param personB - The second Person
     * @param reason - The reason for the divorce
     */
    public Divorce (Person personA, Person personB, String reason) {
        super(personA,personB);
        _reason = reason;
    }

    /**
     * Return the first Person
     *
     * @return - a Person
     */
    public Person getPersonA() {
        return super.getPersonA();
    }

    /**
     * Return the second Person
     *
     * @return - a Person
     */
    public Person getPersonB() {
        return super.getPersonB();
    }

    /**
     * Return the divorce Date
     *
     * @return {@code {@link LocalDate}} - the divorce date
     */
    public LocalDate getDivorceDate() {
        return super.getCreationDate();
    }

    /**
     * Return the reason of the divorce
     *
     * @return {@code {@link String}} - the reason
     */
    public String getReason() {
        return _reason;
    }

    public String toString() {
        return "[ Divorce: " + super.getPersonA().toString() + " | " +
                super.getPersonB().toString() + " | date: " +
                getDivorceDate().toString() + " | reason: " + getReason() + " ]";
    }
}
