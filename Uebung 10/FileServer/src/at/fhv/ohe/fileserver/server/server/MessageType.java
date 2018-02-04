package at.fhv.ohe.fileserver.server.server;


/**
 * Created by OliverHeil on 21.06.2017.
 */
public enum  MessageType {
    LOGIN (0),
    CLOSE (1),
    GET (2),
    PUT (3),
    FILES (4),
    UNKNOWN (256);

    final int _value;
    MessageType(int i) {
        _value = i;
    }

    public int getValue() {
        return _value;
    }

    public static MessageType getType(byte i) {
        for (MessageType type : MessageType.values()) {
            if (type.getValue() == i) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
