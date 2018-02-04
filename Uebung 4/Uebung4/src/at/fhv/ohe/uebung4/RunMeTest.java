package at.fhv.ohe.uebung4;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Oliver Heil on 04.04.2017.
 */
public class RunMeTest {
    @Test
    public void testPerson() {
        try {
            Person testSubject = new Person("Willi","Brand", Person.Gender.MALE,"20.11.1993");
        } catch (IllegalPersonException e) {
            fail("Fail by Person 1:" + e.getMessage());
        }

        try {
            Person testSubject = new Person("Susi","Hopp", Person.Gender.FEMALE,"04.04.2017");
        } catch (IllegalPersonException e) {
            fail("Fail by Person 2: " + e.getMessage());
        }

        try {
            Person testSubject = new Person("Susi","Hopp", Person.Gender.FEMALE,"04.20.2017");
            fail("Fail by Person3");
        } catch (IllegalPersonException e) {
        }

        try {
            Person testSubject = new Person("","Hopp", Person.Gender.FEMALE,"04.04.2017");
            fail("Fail by Person4");
        } catch (IllegalPersonException e) {
        }

        try {
            Person testSubject = new Person("dd",null, Person.Gender.FEMALE,"04.04.2017");
            fail("Fail by Person5");
        } catch (IllegalPersonException e) {
        }

        try {
            Person testSubject = new Person("Susi","Hopp", Person.Gender.FEMALE,null);
            fail("Fail by Person6");
        } catch (IllegalPersonException e) {
        }
    }

    @Test
    public void testMarriages () {
        try {
            Person subject0 = new Person("Max","Mustermann", Person.Gender.MALE,"13.04.1994");
            Person subject1 = new Person("Susi","Test", Person.Gender.FEMALE,"13.04.1996");
            assertTrue("Fail with name 0", subject1.getSurname() != subject0.getSurname());
            assertTrue("Fail spouse", subject0.isMarriedWith() == null);
            assertTrue("Fail spouse", subject1.isMarriedWith() == null);

            Marriage testMarriage = subject0.marry(subject1);
            assertTrue("Fail with name 1", subject1.getSurname() == subject0.getSurname());
            assertTrue("Fail spouse", subject0.isMarriedWith() == subject1);
            assertTrue("Fail spouse", subject1.isMarriedWith() == subject0);

            Divorce testDivorce = subject1.divorce("Test");
            assertTrue("Fail with name 2", subject1.getSurname() != subject0.getSurname());
            assertTrue("Fail spouse", subject0.isMarriedWith() == null);
            assertTrue("Fail spouse", subject1.isMarriedWith() == null);

            List<Document> testLifeEvents = subject0.getLifeEvents();
            assertTrue("Fail Event 0", testLifeEvents.size() == 2);
            assertTrue("Fail Event 1", testLifeEvents.contains(testMarriage));
            assertTrue("Fail Event 2", testLifeEvents.contains(testDivorce));
        } catch (IllegalPersonException e) {
            e.printStackTrace();
            fail();
        } catch (IllegalMarriageExeption illegalMarriageExeption) {
            illegalMarriageExeption.printStackTrace();
            fail();
        }

        try {
            Person subject0 = new Person("Max","Mustermann", Person.Gender.MALE,"13.04.1994");
            Person subject1 = new Person("Susi","Test", Person.Gender.MALE,"13.04.1996");
            Marriage testMarriage = subject0.marry(subject1);
            fail();
        } catch (IllegalPersonException e) {
            fail();
        } catch (IllegalMarriageExeption illegalMarriageExeption) {
        }

        try {
            Person subject0 = new Person("Max","Mustermann", Person.Gender.MALE,"13.04.1994");
            Marriage testMarriage = subject0.marry(subject0);
            fail();
        } catch (IllegalPersonException e) {
            fail();
        } catch (IllegalMarriageExeption illegalMarriageExeption) {
        }

    }
}