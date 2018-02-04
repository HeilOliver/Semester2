package at.fhv.ohe.fileserver;

import at.fhv.ohe.fileserver.logcontroller.LogController;
import at.fhv.ohe.fileserver.passwordchifre.SecurePassword;
import at.fhv.ohe.fileserver.server.configuration.ConfigController;
import at.fhv.ohe.fileserver.server.configuration.ServerConfiguration;
import at.fhv.ohe.fileserver.server.configuration.exceptions.ConfigurationIsNotCompletedException;
import at.fhv.ohe.fileserver.server.configuration.exceptions.ConfigurationVersionException;
import at.fhv.ohe.fileserver.server.server.Server;
import at.fhv.ohe.fileserver.server.user.AccessLevel;
import at.fhv.ohe.fileserver.server.user.User;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Oliver Heil on 10.06.2017.
 */
public class ServerRun {
    ServerConfiguration _configuration;


    private ServerRun(String[] args) {
        try {
            _configuration = ConfigController.getConfiguration(new File("config.conf"));
        } catch (JSONException | ConfigurationVersionException | ConfigurationIsNotCompletedException | IOException e) {
            LogController.getInstance().errorOccurs(e, "Not be able to load the Config File");
            LogController.getInstance().close();
            System.err.println("Cant load config File. For more Information show in log file");
            return;
        }

        if (args.length == 1 && args[0].equals("-r")) {
            runServer();
        } else {
            runConfig();
        }
    }

    public static void main(String[] args) {
        LogController.setLogFileLocation(new File("log.txt"));
        LogController logController = LogController.getInstance();
        logController.addCustom("NEW START");
        new ServerRun(args);
    }

    private void runConfig() {
        boolean loggedIn = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (!loggedIn) {
            try {
                System.out.println("Login\nUsername:");
                String user = br.readLine();
                System.out.println("Password:");
                String pass = br.readLine();
                if (checkUsername(user, pass)) {
                    System.out.println("Logged in!");
                    loggedIn = true;
                    break;
                }
                System.err.println("Wrong Username/Password/Level");
                Thread.sleep(1000);
            } catch (IOException e) {
                LogController.getInstance().errorOccurs(e);
            } catch (InterruptedException ignore) {
            }
        }

        while (loggedIn) {
            System.out.println("\n");
            String input = null;
            try {
                input = br.readLine().toLowerCase();
            } catch (IOException e) {
                LogController.getInstance().errorOccurs(e);
            }

            switch (input) {
                case "help":
                case "h":
                    System.out.println("Help:\n" +
                            "Add  \t-> Add a new User\n" +
                            "Del  \t-> Delete a User\n" +
                            "Help \t-> Help\n" +
                            "Close\t-> Close the Console\n" +
                            "Save \t-> Save the configuration to \"config.conf\"");
                    break;

                case "add":
                case "a":
                    try {
                        addUser(br);
                    } catch (IOException e) {
                        LogController.getInstance().errorOccurs(e);
                    }
                    break;

                case "del":
                case "d":
                    try {
                        delUser(br);
                    } catch (IOException e) {
                        LogController.getInstance().errorOccurs(e);
                    }
                    break;

                case "close":
                case "c":
                    return;

                case "save":
                case "s":
                    System.out.println("Save Configuration");
                    try {
                        ConfigController.writeConfiguration(new File("config.conf"), _configuration);
                        System.out.println("Save Sucessfull");
                    } catch (IOException | ConfigurationIsNotCompletedException | JSONException e) {
                        e.printStackTrace();
                        System.out.println("Save Failed");
                    }
                    break;
            }
        }
    }

    private void delUser(BufferedReader br) throws IOException {
        System.out.println("Delete User:\nUsername:");
        String username = br.readLine();

        for (User user : _configuration.getKnownUsers()) {
            if (user.getName().equals(username)) {
                _configuration.getKnownUsers().remove(user);
                return;
            }
        }
    }

    private void addUser(BufferedReader br) throws IOException {
        System.out.println("New User:\nUsername:");
        String username = br.readLine();
        System.out.println("Password:");
        String password = br.readLine();
        SecurePassword securePassword = new SecurePassword(password);
        _configuration.getKnownUsers().add(new User(securePassword, username, AccessLevel.RWUSER));
    }

    private boolean checkUsername(String username, String password) {
        for (User user : _configuration.getKnownUsers()) {
            if (user.getName().equals(username) &&
                    user.getAccessLevel() == AccessLevel.ADMIN) {
                return user.getPassword().checkAgainst(password);
            }
        }
        return false;
    }

    private void runServer() {
        new Server(_configuration);
    }
}
