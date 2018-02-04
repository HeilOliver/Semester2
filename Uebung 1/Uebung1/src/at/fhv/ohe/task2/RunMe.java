package at.fhv.ohe.task2;

import at.fhv.ohe.rectangle.Rectangle;
import at.fhv.ohe.rectangle.Rectangle.TurnDirection;

import static at.fhv.ohe.rectangle.Rectangle.RectangleEdge.BOTTOMRIGHT;
import static at.fhv.ohe.rectangle.Rectangle.TurnDirection.*;

public class RunMe {
    public static void main(String[] args) {

        Rectangle _tRect = new Rectangle(30,30,10,20);
        _tRect.printRectangle();
        _tRect.turn90Dec(BOTTOMRIGHT, CW);
        _tRect.printRectangle();
    }
}
