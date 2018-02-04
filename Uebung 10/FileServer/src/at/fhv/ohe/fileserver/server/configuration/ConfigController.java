package at.fhv.ohe.fileserver.server.configuration;

import at.fhv.ohe.fileserver.passwordchifre.SecurePassword;
import at.fhv.ohe.fileserver.server.configuration.exceptions.ConfigurationIsNotCompletedException;
import at.fhv.ohe.fileserver.server.configuration.exceptions.ConfigurationVersionException;
import at.fhv.ohe.fileserver.server.user.AccessLevel;
import at.fhv.ohe.fileserver.server.user.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by OliverHeil on 11.06.2017.
 */
public class ConfigController {
    private static final int VERSION = 10000;

    public static ServerConfiguration getConfiguration(File location)
            throws JSONException, IOException, ConfigurationIsNotCompletedException, ConfigurationVersionException {

        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(location))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonData.append(line).append("\n");
            }
        }

        JSONObject obj = new JSONObject(jsonData.toString());
        ServerConfiguration configuration = new ServerConfiguration();

        if (obj.getInt("version") != VERSION) {
            throw new ConfigurationVersionException();
        }
        configuration.setTimeout(obj.getInt("timeout"));
        configuration.setConnectionsAllowed(obj.getInt("connectionsallowed"));
        configuration.setPort(obj.getInt("port"));
        configuration.setSharedDirectory(new File(obj.getString("sharedlocation")));
        configuration.setTransferWindowSize(obj.getInt("transferWindowSize"));

        JSONArray users = obj.getJSONArray("users");
        LinkedList<User> list = new LinkedList<>();

        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            SecurePassword password = new SecurePassword(user.getString("pwH"), user.getString("pwS"));
            list.add(new User(password,user.getString("name"),AccessLevel.valueOf(user.getString("access"))));
        }
        configuration.setKnownUsers(list);

        if (!configuration.isComplete()) {
            throw new ConfigurationIsNotCompletedException();
        }
        return configuration;
    }

    public static void writeConfiguration(File location, ServerConfiguration configuration)
            throws IOException, ConfigurationIsNotCompletedException, JSONException {

        if (!configuration.isComplete()) {
            throw new ConfigurationIsNotCompletedException();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", VERSION);
        jsonObject.put("timeout", configuration.getTimeout());
        jsonObject.put("port", configuration.getPort());
        jsonObject.put("connectionsallowed", configuration.getConnectionsAllowed());
        jsonObject.put("transferWindowSize", configuration.getTransferWindowSize());
        jsonObject.put("sharedlocation", configuration.getSharedDirectory().getAbsolutePath());

        ArrayList<JSONObject> list = new ArrayList<>();
        for (User user : configuration.getKnownUsers()) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("name", user.getName());
            jsonUser.put("access", user.getAccessLevel());
            jsonUser.put("pwH", user.getPassword().getPasswordHash());
            jsonUser.put("pwS", user.getPassword().getSalt());
            list.add(jsonUser);
        }
        jsonObject.put("users", list);

        try (FileWriter writer = new FileWriter(location)) {
            jsonObject.write(writer);
        }
    }

}
