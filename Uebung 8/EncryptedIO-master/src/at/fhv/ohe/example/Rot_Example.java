package at.fhv.ohe.example;

import at.fhv.ohe.EncryptedIO.RotReader;
import at.fhv.ohe.EncryptedIO.RotWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A little Example for the ROT Reader and Writer
 * <p>
 * Created by OliverHeil on 14.05.2017.
 */
public class Rot_Example {

    public static void main(String[] args) {
        RotWriter writer;
        RotReader reader;
        String exampleText = "Hy I´m a example for this. Have fun with this things :)";

        try {
            writer = new RotWriter(new FileWriter(new File("rotExample.txt")), 10);
            writer.write(exampleText);
            writer.flush();
            writer.close();

            reader = new RotReader(new FileReader(new File("rotExample.txt")), 10);
            char[] buffer = new char[exampleText.length()];
            reader.read(buffer);
            reader.close();

            System.out.println(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
