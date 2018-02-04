package at.fhv.ohe.uebung4;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The class describes a document.
 *
 * @author Oliver H
 * @version 1.0
 * @since 04.04.2017
 */
public abstract class Document implements Serializable{
    private Person      _personA;
    private Person      _personB;
    private LocalDate   _date;

    /**
     * Creates a Document
     *
     * @param personA - first Person
     * @param personB - second Person
     */
    public Document (Person personA, Person personB) {
        _personA = personA;
        _personB = personB;
        _date = LocalDate.now();
    }

    /**
     * Return the first Person
     *
     * @return - a Person
     */
    protected Person getPersonA(){
        return _personA;
    }

    /**
     * Return the second Person
     *
     * @return - a Person
     */
    protected Person getPersonB(){
        return _personB;
    }

    /**
     * Return the creation date
     *
     * @return {@code {@link LocalDate}} - the creation date
     */
    protected LocalDate getCreationDate() {
        return _date;
    }
}
