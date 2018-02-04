package at.fhv.ohe.uebung4;

import java.io.*;

/**
 *
 * Package ${PACKAGE_NAME}
 * Created by Oliver Heil on 03.04.2017.
 */
public class RunMe {


    public static void main(String[] args) {
        // A little Example

        try {
            Person gerald   = new Person("Gerald","von Riva", Person.Gender.MALE,"26.04.1200");
            Person tris    = new Person("Tris","Merrigold", Person.Gender.FEMALE,"04.06.1223");

            System.out.println(gerald);
            System.out.println(tris);

            System.out.println(gerald.marry(tris));
            System.out.println(tris.divorce("Will nicht als Objekt behandelt werden!"));

            System.out.println(gerald);
            System.out.println(tris);

        } catch (IllegalPersonException e) {
            e.printStackTrace();
            return;
        } catch (IllegalMarriageExeption illegalMarriageExeption) {
            illegalMarriageExeption.printStackTrace();
            return;
        }
    }

}
