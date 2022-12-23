package bugelniels.colordialog.model.crosshairs;

import bugelniels.colordialog.model.GradientColor;
import bugelniels.colordialog.model.gradients.HueGradient;
import lombok.Getter;

/**
 * This class represents the cross-hair in a hue gradient. As a result, this cross-hair can only be moved in the
 * y-direction. Updating this y-position of the cross-hair will update the hue of the provided gradient color.
 *
 * @author BugelNiels
 */
public class HueCrossHair implements CrossHair {

    private static final int MIN_HUE = 0;
    private static final int MAX_HUE = 255;

    @Getter
    private final int x;
    @Getter
    private int y;
    private final GradientColor gradientColor;

    @Getter
    private final HueGradient parent;

    /**
     * Creates a new cross-hair for the hue gradient. The position will be initialized based on the hue value of the
     * provided color, and the width of the hue gradient itself.
     *
     * @param color  The color whose hue this cross-hair updates.
     * @param parent The hue gradient this cross-hair operates in.
     */
    public HueCrossHair(GradientColor color, HueGradient parent) {
        this.gradientColor = color;
        this.parent = parent;
        this.x = parent.getWidth();
        this.y = (int) gradientColor.getHue() * parent.getHeight();
    }

    private int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Does not do anything, as the hue gradient is 1-dimensional.
     *
     * @param newX New x position of the cross-hair. Its value is ignored.
     */
    @Override
    public void setX(int newX) {
    }

    /**
     * Updates the y coordinate of this cross-hair and the corresponding hue of the color.
     *
     * @param newY The new y coordinate of the cross-hair.
     */
    public void setY(int newY) {
        this.y = clamp(newY, getMinY(), getMaxY());
        gradientColor.setHue(newY / (float) parent.getHeight());
    }

    @Override
    public int getMinX() {
        return x;
    }

    @Override
    public int getMaxX() {
        return x;
    }

    @Override
    public int getMinY() {
        return MIN_HUE;
    }

    @Override
    public int getMaxY() {
        return MAX_HUE;
    }

}
