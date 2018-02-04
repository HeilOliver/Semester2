package at.fhv.ohe.rectangle;


/**
 * The Rectangle class provides a Rectangle and different geometric 
 * methods to manipulate the object.  
 *
 * @author      Oliver Heil - fhv.at
 * @version     1.0
 * @since   	2017-03-14
 */
public class Rectangle {
    private Point position;
    private int width;
    private int height;


    /**
     * Rectangle RectangleEdge
     *
     * @author Oliver Heil
     */
    public enum RectangleEdge {
        UPPERLEFT,
        UPPERRIGHT,
        BOTTOMLEFT,
        BOTTOMRIGHT
    }

    /**
     * Rectangle Turn TurnDirection
     *
     * @author Oliver Heil
     */
    public static enum TurnDirection {
        CCW,
        CW
    }

    /**
     * Create an Rectangle Object
     *
     * @param position	UpperLeft Edge Position 
     * @param width 	Width
     * @param height 	Height
     */
    public Rectangle(Point position, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    /**
     * Create an Rectangle Object
     *
     * @param position1 	UpperLeft Edge Position 
     * @param position2 	BottomRigth Edge Position
     */
    public Rectangle(Point position1, Point position2) {
        this (position1,
                position2.x - position1.x,
                position2.y - position1.y);
    }

    /**
     * Create an Rectangle Object
     *
     * @param x 		X Position UpperLeft Edge
     * @param y 		Y Position UpperLeft Edge
     * @param width 	Width
     * @param height 	Height
     */
    public Rectangle(int x, int y, int width, int height) {
        this (new Point(x,y),width,height);
    }

    /**
     * Return actual position
     *
     * @return position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Return the width
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return the height
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the Rectangle to the given Position
     *
     * @param newPos 	The new position
     */
    public void setNewPos(Point newPos){
        position = newPos;
    }

    /**
     * Change the Rectangle Position by given values
     *
     * @param byX 	change X by value
     * @param byY 	change Y by value
     */
    public void changePosition(int byX, int byY) {
        position.setX(position.x + byX);
        position.setY(position.y + byY);
    }

    /**
     * Turn the Rectangle by +/-90Dec around a given edge. 
     *
     *
     * @param turningEdge 	Enum RectangleEdge 
     * @param direction 	Enum TurnDirection
     */
    public void turn90Dec(RectangleEdge turningEdge, TurnDirection direction) {

        switch (turningEdge) {
            case UPPERLEFT:
                if (direction == TurnDirection.CCW ) {
                    position = new Point(position.x, position.y- width);
                } else {
                    position = new Point(position.x - height, position.y);
                }
                break;
            case UPPERRIGHT:
                if (direction == TurnDirection.CCW ) {
                    position = new Point(position.x+ width, position.y);
                } else {
                    position = new Point(position.x + (width - height), position.y- width);
                }
                break;
            case BOTTOMLEFT:
                if (direction == TurnDirection.CCW ) {
                    position = new Point(position.x- height, position.y+ width);
                } else {
                    position = new Point(position.x, position.y + height);
                }
                break;
            case BOTTOMRIGHT:
                if (direction == TurnDirection.CCW ) {
                    position = new Point(position.x + (width - height), position.y- height);
                } else {
                    position = new Point(position.x+ width, position.y +(height - width));
                }
                break;
        }
        int temp = height;
        //noinspection SuspiciousNameCombination
        height = width;
        width = temp;
    }

    /**
     * Check if the Rectangle is a Square.
     *
     * @return isSquare
     */
    public boolean isSquare() {
        return width == height;
    }

    /**
     * Return the Perimeter
     *
     * @return Perimeter
     */
    public int getPerimeter () {
        return 2*(width + height);
    }

    /**
     * Return the Circumradius of this Rectangle
     *
     * @return Circumradius
     */
    public int getCircumradius () {
        return (int) (0.5* Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)));
    }

    /**
     * Return the Circumference of this Rectangle
     *
     * @return Circumference
     */
    public int getCircumference () {
        return (int) (2* Math.PI * getCircumradius());
    }

    /**
     * Zoom the Rectangle by a given factor. The zoom applied from the middle.
     * > 1 Increase the Size 
     * < 1 Decrease the Size
     * 0 || 1 nothing change
     *
     * @param factor Factor to Zoom. 
     */
    public void zoomAt (double factor) {
        if (factor != 0) {
            int newWidth = (int)(width * factor);
            int newHeigth = (int)(height * factor);

            position.setX(position.x - ((newWidth - width) /2));
            position.setY(position.y - ((newHeigth - height) /2));

            width = newWidth;
            height = newHeigth;
        }
    }

    /**
     * Divide this Rectangle into four new same sized Rectangles.
     *
     * @return an Array of four Rectangle Objekts
     */
    public Rectangle[] divideTo4() {
        Rectangle[] newRectangles = new Rectangle[4];
        newRectangles[0] = new Rectangle(new Point(position.x, position.y), width /2, height /2);
        newRectangles[1] = new Rectangle(new Point(position.x, position.y + height /2), width /2, height /2);
        newRectangles[2] = new Rectangle(new Point(position.x+ width /2, position.y + height /2), width /2, height /2);
        newRectangles[3] = new Rectangle(new Point(position.x+ width /2, position.y), width /2, height /2);
        return newRectangles;
    }

    /**
     * Calculate the intersection between this and a second Rectangle.
     *
     * @param secondRectangle 	Rectangle Object
     *
     * @return Rectangle objects OR null if both Rectangle are not intercept with each other
     */
    public Rectangle getIntercept(Rectangle secondRectangle) {
        int interceptX = Math.max(position.x, secondRectangle.getPosition().x);
        int interceptY = Math.max(position.y, secondRectangle.getPosition().y);
        int right = Math.min(position.x + width,
                secondRectangle.getPosition().x + secondRectangle.getWidth());
        int bottom = Math.min(position.y + height,
                secondRectangle.getPosition().y + secondRectangle.getHeight());
        if (right > interceptX && bottom > interceptY) {
            return new Rectangle(new Point(interceptX,interceptY),right - interceptX,bottom - interceptY);
        } else {
            return null;
        }
    }

    public void printRectangle () {
        System.out.println("X-" + position.x + " Y-" + position.y +
                " Hoehe-" + height + " Breite-" + width + " IsSquare=" + isSquare() + " Umfang=" + getPerimeter());
    }
}