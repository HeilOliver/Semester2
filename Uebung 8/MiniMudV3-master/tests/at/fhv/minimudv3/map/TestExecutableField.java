package at.fhv.minimudv3.map;

import at.fhv.minimudv3.game.enums.ActionFunctions;
import at.fhv.minimudv3.game.player.Player;
import at.fhv.minimudv3.map.field.Field;
import at.fhv.minimudv3.map.field.IExecutable;

/**
 * Test field with Executable
 *
 * Created by Oliver Heil on 26.04.2017.
 */
public class TestExecutableField extends Field implements IExecutable {

    public TestExecutableField() {
        super("","");
    }

    @Override
    public void execute(Player player, ActionFunctions actionFunctions) {
        if (actionFunctions == ActionFunctions.PICKUP) {
            player.healthReduction(50);
        }
        player.healthReduction(10);
    }
}
