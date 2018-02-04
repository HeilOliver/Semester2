package at.fhv.minimudv3;

import at.fhv.minimudv3.game.enums.Directions;
import at.fhv.minimudv3.map.field.Field;
import at.fhv.minimudv3.map.field.Floor;
import at.fhv.minimudv3.map.field.Wall;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test a Complete Map with string
 *
 * Created by Oliver H on 21.04.2017.
 */
public class TestMap {

    @Test
    public void createMap() {
        Field[] fields = new Field[5];
        // Generate the Fields
        fields[0] = new Floor();
        fields[1] = new Floor();
        fields[2] = new Floor();
        fields[3] = new Wall();
        fields[4] = new Wall();

        // Bind the Fields
        fields[0].addBinding(Directions.WEST,fields[1]);
        fields[0].addBinding(Directions.EAST,fields[2]);
        fields[0].addBinding(Directions.NORTH,fields[3]);
        fields[0].addBinding(Directions.SOUTH,fields[4]);


        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            stringBuilder.append("Field: ");
            stringBuilder.append(fields[i].getFieldID());
            stringBuilder.append(", ");
            stringBuilder.append(fields[i].getShortDescription());
            stringBuilder.append("\nDescription: ");
            stringBuilder.append(fields[i].getLongDescription());
            stringBuilder.append("\n");
            Map<Directions, Field> directions = fields[i].getAllDirections();
            directions.forEach((dir,field) -> {
                stringBuilder.append("\n");
                stringBuilder.append(dir);
                stringBuilder.append(": ");
                stringBuilder.append(field.getShortDescription());
            });
            stringBuilder.append("\n--------------------------------------------------\n");
        }
        System.out.println(stringBuilder.toString());
        assertTrue(true);
    }


}
