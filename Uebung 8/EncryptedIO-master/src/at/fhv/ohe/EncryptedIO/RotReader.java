package at.fhv.ohe.EncryptedIO;

import java.io.IOException;
import java.io.Reader;

/**
 * Describes an Reader that encrypt all given chars by a ROT encryption.
 * <p>
 * Created by OliverHeil on 14.05.2017.
 */
public class RotReader extends Reader {
    private Reader _in;
    private int _rotation;

    /**
     * Creates an new Reader with given ROT encryption
     *
     * @param in       - The Input Writer
     * @param rotation - The rotation for the encryption - can´t be negative
     */
    public RotReader(Reader in, int rotation) {
        if (rotation < 0) {
            throw new IllegalArgumentException();
        }
        _in = in;
        _rotation = (26 - rotation) % 26;
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
    public int read(char[] cbuf, int off, int len) throws IOException {
        int n = _in.read(cbuf, off, len);
        for (int i = off; i < (off + len); i++) {
            cbuf[i] = encryptChar(cbuf[i]);
        }
        return n;
    }

    @Override
    public void close() throws IOException {

    }
}
