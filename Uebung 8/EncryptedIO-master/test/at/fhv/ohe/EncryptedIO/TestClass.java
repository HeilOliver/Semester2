package at.fhv.ohe.EncryptedIO;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test class for the EncryptedIO
 * <p>
 * Created by OliverHeil on 14.05.2017.
 */
class TestClass {

    @Test
    void Test() {
        Writer writer;
        Reader reader;
        String exampleText = "Example ABCDEFGHIJKLMNOPQRSTUVWXYZ :._,*'+#ß?=9";
        String rot10Text = "Ohkwzvo KLMNOPQRSTUVWXYZABCDEFGHIJ :._,*'+#ß?=9";

        try {
            writer = new RotWriter(new FileWriter(new File("test.t")), 10);
            writer.write(exampleText);
            writer.close();

            reader = new FileReader(new File("test.t"));
            char[] buffer = new char[exampleText.length()];
            reader.read(buffer);
            reader.close();
            assertTrue(new String(buffer).equals(rot10Text), "0");

            reader = new RotReader(new FileReader(new File("test.t")), 10);
            buffer = new char[exampleText.length()];
            reader.read(buffer);
            reader.close();
            assertTrue(new String(buffer).equals(exampleText), "1");

        } catch (IOException e) {
            fail("2");
        }
    }
}