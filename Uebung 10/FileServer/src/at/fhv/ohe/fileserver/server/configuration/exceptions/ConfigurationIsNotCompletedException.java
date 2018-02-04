package at.fhv.ohe.fileserver.server.configuration.exceptions;

/**
 * Created by OliverHeil on 12.06.2017.
 */
public class ConfigurationIsNotCompletedException extends ConfigurationException {

    public ConfigurationIsNotCompletedException() {
    }

    public ConfigurationIsNotCompletedException(String message) {
        super(message);
    }
}
