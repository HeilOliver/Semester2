package at.fhv.ohe.uebung4;

/**
 * The class describes a RegistryOffice with certain method's.
 * The {@link RegistryOffice} have the ruleSet for Marriages and Divorces
 *
 * @author Oliver H
 * @version 1.0
 * @since 04.04.2017
 */
public class RegistryOffice {
    private RegistryOffice() {
    }

    /**
     * Creates a new Marriage Document
     *
     * @param personA - The first person for the marriage
     * @param personB - The second person for the marriage
     * @param _groomsmanA - The groomsman from the first person
     * @param _groomsmanB - The groomsman from the second person
     * @return {@code {@link Marriage}} - The marriage
     * @throws IllegalMarriageExeption - Thrown when one of the rule fails
     */
    public static Marriage marryPersons(Person personA, Person personB, Person _groomsmanA, Person _groomsmanB) throws IllegalMarriageExeption{
        if(!personA.isOlderThan(18) || !personB.isOlderThan(18)) {
            throw new IllegalMarriageExeption("Marry Persons are to young");
        }

        if (personA == personB) {
            throw new IllegalMarriageExeption("You cant marry yourself");
        }

        if (_groomsmanA != null) {
            if (_groomsmanA == personA || _groomsmanA == personB) {
                throw new IllegalMarriageExeption("A spouse cant be a groomsman");
            }
            if (!_groomsmanA.isOlderThan(18)) {
                throw new IllegalMarriageExeption("Groomsman Person are to young");
            }
        }

        if (_groomsmanB != null) {
            if (_groomsmanB == personA || _groomsmanB == personB) {
                throw new IllegalMarriageExeption("A spouse cant be a groomsman");
            }
            if (!_groomsmanB.isOlderThan(18)) {
                throw new IllegalMarriageExeption("Groomsman Person are to young");
            }
        }

        if (personA.getGender() == personB.getGender()) {
            throw new IllegalMarriageExeption("Homosexual marriages are not allowed");
        }

        if (personA.isMarriedWith() != null || personB.isMarriedWith() != null) {
            throw new IllegalMarriageExeption("One of the person is already married");
        }

        return new Marriage(personA,personB);
    }

    /**
     * Creates a new divorce document
     *
     * @param personA - The first person for the divorce
     * @param personB - The second person for the divorce
     * @param reason - The reason for the divorce
     * @return {@code {@link Divorce}} - The divorce document
     */
    public static Divorce divorcePersons(Person personA, Person personB, String reason) throws IllegalMarriageExeption{
        if (personA.isMarriedWith() == null || personB.isMarriedWith() == null) {
            throw new IllegalMarriageExeption("A marriage is needed before you can divorce");
        }
        return new Divorce(personA,personB,reason);
    }
}
