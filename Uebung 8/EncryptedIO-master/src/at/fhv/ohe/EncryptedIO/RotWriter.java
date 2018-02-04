package at.fhv.ohe.EncryptedIO;

import java.io.IOException;
import java.io.Writer;

/**
 * Describes an Writer that encrypt all given chars by a ROT encryption.
 * <p>
 * Created by OliverHeil on 14.05.2017.
 */
public class RotWriter extends Writer {
    private Writer _out;
    private int _rotation;

    /**
     * Creates an new Writer with given ROT encryption
     *
     * @param out      - The Output Writer
     * @param rotation - The rotation for the encryption - can´t be negative
     */
    public RotWriter(Writer out, int rotation) {
        if (rotation < 0) {
            throw new IllegalArgumentException();
        }
        _out = out;
        _rotation = rotation % 26;
    }

    /**
     * Encrypt a char by given rotation. It only encrypt a-z and A-Z.
     *
     * @param c - The char
     * @return - the encrypted char
     */
    private char encryptChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ((((c - 'a') + _rotation) % 26) + 'a');
        } else if (c >= 'A' && c <= 'Z') {
            return (char) ((((c - 'A') + _rotation) % 26) + 'A');
        }
        return c;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if ((off | len | (off + len) | (cbuf.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = off; i < (off + len); i++) {
            cbuf[i] = encryptChar(cbuf[i]);
        }
        _out.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        _out.flush();
    }

    @Override
    public void close() throws IOException {
        _out.close();
    }
}
