package bugelniels.colordialog.model.crosshairs;

/**
 * Represents a simple cross-hair with a position in a bounded movement space.
 *
 * @author BugelNiels
 */
public interface CrossHair {

    /**
     * Retrieves the x position of the cross-hair.
     *
     * @return The x position of the cross-hair.
     */
    int getX();

    /**
     * Retrieves the y position of the cross-hair.
     *
     * @return The y position of the cross-hair.
     */
    int getY();

    /**
     * Updates the x-position of the cross-hair.
     *
     * @param newX New x position of the cross-hair. The value is clamped if it false outside the x range.
     */
    void setX(int newX);

    /**
     * Updates the y-position of the cross-hair.
     *
     * @param newY New y position of the cross-hair. The value is clamped if it false outside the y range.
     */
    void setY(int newY);

    /**
     * Retrieves the minimum value the x coordinate can take.
     *
     * @return Minimum value the x coordinate can take. Default is 0.
     */
    default int getMinX() {
        return 0;
    }

    /**
     * Retrieves the minimum value the x coordinate can take.
     *
     * @return Maximum value the x coordinate can take.
     */
    int getMaxX();

    /**
     * Retrieves the minimum value the y coordinate can take.
     *
     * @return Minimum value the y coordinate can take. Default is 0.
     */
    default int getMinY() {
        return 0;
    }

    /**
     * Retrieves the minimum value the y coordinate can take.
     *
     * @return Maximum value the y coordinate can take.
     */
    int getMaxY();
}
