package bugelniels.colordialog.model.crosshairs;

import bugelniels.colordialog.model.GradientColor;
import bugelniels.colordialog.model.gradients.SvGradient;
import lombok.Getter;

import java.awt.*;

/**
 * This class represents a cross-hair in a saturation-value gradient. Whenever the coordinates of this cross-hair
 * change, it will update the saturation and brightness of the provided gradient color.
 * Moving in the y direction will update the Brightness, while moving in the x direction will update the Saturation.
 *
 * @author BugelNiels
 */
public class SvCrossHair implements CrossHair {

    @Getter
    private int x;
    @Getter
    private int y;
    private static final int SIZE = 16;
    private final GradientColor gradientColor;

    @Getter
    private final SvGradient parent;

    /**
     * Creates a new saturation-value cross-hair. The position will be initialized according to the saturation and
     * value of the provided color.
     *
     * @param color  The color whose saturation/value this cross-hair updates.
     * @param parent The saturation-value gradient this cross-hair operates in.
     */
    public SvCrossHair(GradientColor color, SvGradient parent) {
        this.gradientColor = color;
        this.parent = parent;
        this.x = (int) color.getSaturation() * parent.getWidth();
        this.y = (int) color.getBrightness() * parent.getHeight();
    }

    private int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Updates the y coordinate of this cross-hair and the corresponding brightness of the color.
     *
     * @param newY The new y coordinate of the cross-hair.
     */
    public void setY(int newY) {
        this.y = clamp(newY, getMinY(), getMaxY());
        gradientColor.setBrightness(1 - y / (float) parent.getHeight());
    }

    /**
     * Updates the x coordinate of this cross-hair and the corresponding saturation of the color.
     *
     * @param newX The new x coordinate of the cross-hair.
     */
    public void setX(int newX) {
        this.x = clamp(newX, getMinX(), getMaxX());
        gradientColor.setSaturation(x / (float) parent.getWidth());
    }

    @Override
    public int getMaxX() {
        return parent.getWidth();
    }

    @Override
    public int getMaxY() {
        return parent.getHeight();
    }

    /**
     * Retrieves the color that the cross-hair itself should be displayed as.
     * This is used to ensure high contrast with background.
     *
     * @return The color of the cross-hair.
     */
    public Color getCrossHairColor() {
        if (y <= (float) parent.getHeight() / 2) {
            return Color.black;
        }
        return Color.white;
    }

    /**
     * Retrieves the size of the cross-hair. The cross-hair itself fits in a square box of getSize() x getSize().
     *
     * @return The size of the cross-hair.
     */
    public int getSize() {
        return SIZE;
    }
}
