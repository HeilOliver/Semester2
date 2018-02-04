package at.fhv.ohe.fileserver.logcontroller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Describes an Log File handler.
 * Before you get an Instance you must add a File Location by setFilelocation();
 * The hole Class is ThreatSave
 *
 * Created by Oliver Heil on 11.06.2017.
 */
public class LogController {
    private static File _logFileLocation;
    private static LogController _instance;
    private boolean _isRunning;
    private Thread _writerThread;
    private LinkedBlockingQueue<String> _logQueue;

    private LogController(){
        if (_logFileLocation == null) {
            throw new IllegalStateException("No logfile location set");
        }
        _logQueue = new LinkedBlockingQueue<>();
        _isRunning = true;
        createNewWorker();
    }

    private void createNewWorker() {    // TODO change to start Thread if required or kill with main
        _writerThread = new Thread(() -> {
            while (_isRunning || _logQueue.size() > 0) {
                try {
                    writeInLog(_logQueue.take());
                } catch (InterruptedException ignore) {
                }
            }
        },"LoggerThread");
        _writerThread.start();
    }

    private void addToLog(String s) {
        Date date = new Date();
        StringBuilder builder = new StringBuilder();
        builder.append(date.toString());
        builder.append(" ->");
        builder.append(s);
        builder.append("\n");
        addToQueue(builder.toString());
    }

    private void addToQueue(String s) {
        if (_isRunning) {
            _logQueue.add(s);
        } else {
            throw new IllegalStateException("The logger is not Open anymore");
        }
    }

    private void writeInLog(String s) {
        try(FileWriter fileWriter = new FileWriter(_logFileLocation,true)) {
            fileWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the Logger. The logger will be write still be write the left logging events to the file.
     */
    public void close() {
        _instance = null;
        _isRunning = false;
        _writerThread.interrupt();
    }

    /**
     * Set the LogFile location.
     *
     * @param logFileLocation - The LogFile Location
     */
    public static void setLogFileLocation(File logFileLocation) {
        if (_instance != null) {
            throw new IllegalStateException("Logger already be initiated");
        }
        _logFileLocation = logFileLocation;
    }

    /**
     * Returns a LogFile Instance. Only one Instance can be used in one Program
     *
     * @return {@code LogController} - The instance of the LogController
     */
    public static LogController getInstance(){
        if(_instance == null){
            synchronized (LogController.class) {
                if(_instance == null){
                    _instance = new LogController();
                }
            }
        }
        return _instance;
    }

    /**
     * Writes an custom Message to the log File
     *
     * @param customMessage - The custom message
     */
    public void addCustom(String customMessage) {
        StringBuilder builder = new StringBuilder();
        builder.append("CUSTOM: ");
        builder.append(customMessage);
        addToLog(builder.toString());
    }

    /**
     * Write an Exception to the log File with custom Message
     *
     * @param exception - The exception that occurs
     * */
    public void errorOccurs(Exception exception) {
        errorOccurs(exception,null);
    }

    /**
     * Write an Exception to the log File with custom Message
     *
     * @param exception - The exception that occurs
     * @param customMessage - A custom Message
     * */
    public void errorOccurs(Exception exception, String customMessage) {
        StringBuilder builder = new StringBuilder();
        builder.append("EXEPTION: ");
        builder.append(exception);
        if (customMessage != null) {
            builder.append(" with ");
            builder.append(customMessage);
            builder.append(" - ");
        }
        builder.append(exception.getMessage());
        builder.append(" @ ");
        builder.append(exception.getLocalizedMessage());
        addToLog(builder.toString());
    }

    /**
     * The possible Reasons for loging user events
     */
    public enum UserLogReason {
        ADD,
        DELETE,
        MODIFY
    }

    /**
     * Add a user event to the Logfile
     *
     * @param logReason - The {@code UserLogReason} a rough description of the loging reason
     */
    public void logUser(UserLogReason logReason) {
        logUser(logReason, null);
    }

    /**
     * Add a user event to the Logfile with a custom Message
     *
     * @param logReason - The {@code UserLogReason} a rough description of the loging reason
     * @param customMessage - A custom Message
     */
    public void logUser(UserLogReason logReason, String customMessage) {
        StringBuilder builder = new StringBuilder();
        builder.append("USER: ");
        builder.append(logReason);
        if (customMessage != null) {
            builder.append(" with ");
            builder.append(customMessage);
        }
        addToLog(builder.toString());
    }

    /**
     * The possible Reasons for logging session events
     */
    public enum SessionLogReason {
        OPEN,
        CLOSE,
        DISCARD
    }

    /**
     * Add a session event to the logfile with custom message
     *
     * @param to - witch thing is corresponding to this event
     * @param logReason - The {@code SessionLogReason} - a rough description of the reason
     */
    public void logSession(String to, SessionLogReason logReason) {
        logSession(to, logReason, null);
    }

    /**
     * Add a session event to the logfile with custom message
     *
     * @param to - witch thing is corresponding to this event
     * @param logReason - The {@code SessionLogReason} - a rough description of the reason
     * @param customMessage - A custom message
     */
    public void logSession(String to, SessionLogReason logReason, String customMessage) {
        StringBuilder builder = new StringBuilder();
        builder.append("SESSION: ");
        builder.append(logReason);
        builder.append(" to ");
        builder.append(to);
        if (customMessage != null) {
            builder.append(" with");
            builder.append(customMessage);
        }
        addToLog(builder.toString());
    }

}
