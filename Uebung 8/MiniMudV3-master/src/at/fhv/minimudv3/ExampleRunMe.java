package at.fhv.minimudv3;

import at.fhv.minimudv3.game.enums.ActionFunctions;
import at.fhv.minimudv3.game.enums.Directions;
import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.IllegalPlayerNameException;
import at.fhv.minimudv3.map.MapController;
import at.fhv.minimudv3.map.field.Field;
import at.fhv.minimudv3.map.field.Floor;
import at.fhv.minimudv3.map.field.Wall;

/**
 * A Little Example
 * Created by Oliver H on 18.04.2017.
 */
public class ExampleRunMe {
    public static void main(String[] args) {
        // A little example
        Field[] fields = new Field[5];
        // Generate the Fields
        fields[0] = new Floor();
        fields[1] = new Floor();
        fields[2] = new Floor();
        fields[3] = new Wall();
        fields[4] = new Wall();

        // Bind the Fields
        fields[0].addBinding(Directions.WEST, fields[1]);
        fields[0].addBinding(Directions.EAST, fields[2]);
        fields[0].addBinding(Directions.NORTH, fields[3]);
        fields[0].addBinding(Directions.SOUTH, fields[4]);

        MapController mapController = null;
        // Add the Field to a new Map
        mapController = new MapController(fields[0], "testMap");

        // Add a new Player to the Game
        Player player = null;
        try {
            player = mapController.addPlayer("TestSubject");
        } catch (IllegalPlayerNameException e) { // Thrown if the player name is already in use
            e.printStackTrace();
        }

        // Change the OutPut (mouth) from the player to S-out
        player.setPlayerOutstream(System.out::println);

        // You can Walk
        player.move(Directions.WEST);

        // You can Do something there
        player.execute(ActionFunctions.PICKUP);

        // All outputs will be printed in your given Outstream
    }


}
