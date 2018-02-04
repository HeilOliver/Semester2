package at.fhv.ohe.uebung4;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The class describes a person with certain method's and properties.
 * You can marry and divorce two persons. The rules for marriages are in the RegistryOffice.
 *
 * @author Oliver H
 * @version 1.0
 * @since 04.04.2017
 */
public class Person implements Serializable {
    private String      _forename;
    private String      _surname;
    private String      _birthname;
    private Gender      _gender;
    private LocalDate   _birthdate;
    private LinkedList<Document> _lifeEvents;
    private Person      _spouse;

    /**
     * The possible genders that a person object can have.
     */
    public enum Gender {
        MALE,
        FEMALE
    }

    /**
     * Generate a new Person.
     *
     * @param forename {@code String} - The forename of the new person
     * @param birthname {@code String} - The birthname of the new person
     * @param gender {@code Gender} - The gender of the new person
     * @param birthday {@code String} - The birthday in the format dd.MM.yyyy
     * @throws IllegalPersonException - Throws an exception when the Person cant be build with this parameters.
     */
    public Person (String forename, String birthname, Gender gender, String birthday) throws IllegalPersonException{
        _forename = forename;
        _birthname = birthname;
        _gender = gender;
        _lifeEvents = new LinkedList<>();

        if (forename == null || birthname == null || forename.equals("") || birthname.equals("")) {
            throw new IllegalPersonException("The person must have a name");
        }
        try {
            _birthdate = LocalDate.parse(birthday,DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeException e) {
            throw new IllegalPersonException("Wrong birthday");
        } catch (NullPointerException e){
            throw new IllegalPersonException("A birthdate is needed");
        }
        if (_birthdate.isAfter(LocalDate.now())) {
            throw new IllegalPersonException("birthday is in the Future");
        }
    }

    /**
     * Set the new spouse of this person. If the Person is {@code FEMALE} than the surname is change to the {@code MALE} surname
     *
     * @param newSpouse - The new spouse
     */
    private void setSpouse(Person newSpouse) {
        if (getGender() == Gender.FEMALE) {
            _surname = (newSpouse==null) ? null : newSpouse.getSurname();
        }
        _spouse = newSpouse;
    }

    /**
     * Marry two persons together.
     *
     * @param spouse - The new spouse
     * @return {@code {@link Marriage}} - The marriage Document
     * @throws IllegalMarriageExeption - Throws an exception when some conditions are not fulfills
     */
    public Marriage marry(Person spouse) throws IllegalMarriageExeption{
        return marry(spouse,null,null);
    }

    /**
     * Marry two persons together.
     *
     * @param spouse - The new spouse
     * @param groomsmanA - The groomsman from this person
     * @param groomsmanB - The groomsman of the spouse
     * @return {@code {@link Marriage}} - The marriage Document
     * @throws IllegalMarriageExeption - Throws an exception when some conditions are not fulfills
     */
    public Marriage marry(Person spouse, Person groomsmanA, Person groomsmanB) throws IllegalMarriageExeption{
        Marriage marriage = RegistryOffice.marryPersons(this,spouse,groomsmanA,groomsmanB);
        setSpouse(spouse);
        addLiveEvent(marriage);
        spouse.setSpouse(this);
        spouse.addLiveEvent(marriage);
        return marriage;
    }

    /**
     * Add a new life event to the person
     *
     * @param liveEvent - The new liveEvent
     */
    private void addLiveEvent (Document liveEvent) {
        _lifeEvents.add(liveEvent);
    }

    /**
     * Return all lifeEvents that this person have.
     *
     * @return - All lifeEvents
     */
    public List<Document> getLifeEvents() {
        return Collections.unmodifiableList(_lifeEvents);
    }

    /**
     * Divorce two persons
     *
     * @param reason - The reason for the divorce
     * @return {@code {@link Divorce}} - The divorce Document
     * @throws IllegalMarriageExeption - Thrown if you have no spouse
     */
    public Divorce divorce(String reason) throws IllegalMarriageExeption{
        Divorce divorce = RegistryOffice.divorcePersons(this,_spouse,reason);
        _spouse.addLiveEvent(divorce);
        _spouse.setSpouse(null);
        addLiveEvent(divorce);
        setSpouse(null);
        return divorce;
    }

    /**
     * Return the Surname of this person
     *
     * @return {@code String} - the surname
     */
    public String getSurname() {
        return (_surname != null) ? _surname : _birthname;
    }

    /**
     * Return the spouse of this person
     *
     * @return {@code {@link Person}} - the spouse or {@code null} if the person have no spouse
     */
    public Person isMarriedWith() {
        return _spouse;
    }

    /**
     * Return the gender of this person
     *
     * @return {@code {@link Gender}} - the gender
     */
    public Gender getGender() {
        return _gender;
    }

    /**
     * Return {@code true/false} if the person is older than a specific year
     *
     * @param years - the years to test
     * @return {@code boolean} - The condition result
     */
    public boolean isOlderThan (int years) {
        return _birthdate.plusYears(years).minusDays(1).isBefore(LocalDate.now());
    }

    public String toString() {
        String test = (_spouse!=null) ? "1" : "0";
        return "[ Person: " + _forename + " | " + getSurname() + " | " +
                _birthdate.toString() + " | " + test + " ]";
    }


}
