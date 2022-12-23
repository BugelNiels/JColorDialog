package bugelniels.colordialog.model;

import lombok.Getter;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class contains functionality related to colors. Its fundamental representation is hue-saturation-brightness.
 * The class provides methods for converting this into other representations.
 * Fires PropertyChangeEvents whenever the hue, saturation or brightness is updated. Other classes can listen to this
 * and handle these events accordingly.
 *
 * @author BugelNiels
 */
@Getter
public class GradientColor {

    /**
     * Name of the PropertyChangeEvent that is fired when the saturation has changed.
     */
    public static final String SATURATION_CHANGED = "saturationChanged";
    /**
     * Name of the PropertyChangeEvent that is fired when the brightness has changed.
     */
    public static final String BRIGHTNESS_CHANGED = "brightnessChanged";
    /**
     * Name of the PropertyChangeEvent that is fired when the hue has changed.
     */
    public static final String HUE_CHANGED = "hueChanged";

    private float hue;
    private float saturation;
    private float brightness;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    /**
     * Instantiates a new Gradient color from provided red, green, blue values.
     *
     * @param red   The Red value of the color. Must be between 0 and 255.
     * @param green The Green value of the color. Must be between 0 and 255.
     * @param blue  The Blue value of the color. Must be between 0 and 255.
     */
    public GradientColor(int red, int green, int blue) {
        updateHSB(new Color(red, green, blue));
    }

    /**
     * Instantiates a new Gradient color from provided red, green, blue values.
     *
     * @param color Color to instantiate the gradient color with.
     */
    public GradientColor(Color color) {
        updateHSB(color);
    }

    /**
     * Instantiates a new Gradient color based on an existing Gradient color; copies all the values.
     *
     * @param copyColor The Gradient color that the values need to be copied from.
     */
    public GradientColor(GradientColor copyColor) {
        updateHSB(copyColor.getColor());
    }

    /**
     * Sets the RGB and HSV based on an RGB int.
     *
     * @param rgb An integer representation of RGB.
     */
    public void setColor(int rgb) {
        Color color = new Color(rgb);
        updateHSB(color);
    }

    /**
     * Sets the saturation value. Fires a propertyChangeEvent.
     *
     * @param saturation The new saturation value. Must be between 0 and 1.
     */
    public void setSaturation(float saturation) {
        float oldSaturation = this.saturation;
        this.saturation = saturation;
        changeSupport.firePropertyChange(SATURATION_CHANGED, oldSaturation, saturation);
    }

    /**
     * Sets the brightness value. Fires a propertyChangeEvent.
     *
     * @param brightness The new brightness. Must be between 0 and 1.
     */
    public void setBrightness(float brightness) {
        float oldBrightness = this.brightness;
        this.brightness = brightness;
        changeSupport.firePropertyChange(BRIGHTNESS_CHANGED, oldBrightness, brightness);
    }

    /**
     * Sets the hue value. Fires a propertyChangeEvent.
     *
     * @param hue The new hue. Must be between 0 and 1.
     */
    public void setHue(float hue) {
        float oldHue = this.hue;
        this.hue = hue;
        changeSupport.firePropertyChange(HUE_CHANGED, oldHue, hue);
    }

    /**
     * Updates the Hue, Saturation and Brightness values based on the provided color.
     *
     * @param color The color to update the hsb values with.
     */
    private void updateHSB(Color color) {
        float[] hsv = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsv);
        hue = hsv[0];
        saturation = hsv[1];
        brightness = hsv[2];
    }

    /**
     * Retrieves a Color representation of this class.
     *
     * @return The color that this gradient color represents.
     */
    public Color getColor() {
        return Color.getHSBColor(hue, saturation, brightness);
    }

    /**
     * Retrieves RGB as an integer value.
     *
     * @return An integer representation of RGB.
     */
    public int getRGBInt() {
        Color col = Color.getHSBColor(hue, saturation, brightness);
        return col.getRGB();
    }

    /**
     * Adds a PropertyChangeListener to this gradient color.
     *
     * @param listener Instance that will be listening to this gradient color.
     */
    public void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

}
