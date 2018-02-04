package at.fhv.ohe.rectangle;

/**
 * The point class provides a point in a 2 dimensional space
 *
 * @author      Oliver Heil - fhv.at
 * @version     1.0
 * @since   	2017-03-14
 */
public class Point {
    int x;
    int y;

    /**
     * Return the X position
     *
     * @return X Position
     */
    public int getX() {
        return x;
    }

    /**
     * Set the point to given X value
     *
     * @param x 	X Position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Return the Y position
     *
     * @return Y Position
     */
    public int getY() {
        return y;
    }

    /**
     * Set the point to given Y value
     *
     * @param y 	Y position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Create a point with given position
     *
     * @param x		X position
     * @param y		Y position
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
