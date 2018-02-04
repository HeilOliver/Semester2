package at.fhv.ohe.fileserver.server.configuration.exceptions;

/**
 * Created by OliverHeil on 14.06.2017.
 */
public class ConfigurationVersionException extends ConfigurationException {

    public ConfigurationVersionException() {
    }

    private ConfigurationVersionException(String message) {
        super(message);
    }
}
