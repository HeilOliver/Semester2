package at.fhv.ohe.fileserver.logcontroller.exceptions;

import java.io.IOException;

/**
 * The Exception will be thrown if the File is already in use of an another program/thread
 *
 * Created by Oliver Heil on 11.06.2017.
 */
public class FileAlreadyInUseException extends IOException {

    public FileAlreadyInUseException() {
    }

    public FileAlreadyInUseException(String message) {
        super(message);
    }
}
