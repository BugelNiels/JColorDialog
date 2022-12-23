package bugelniels.colordialog.model.gradients;

import bugelniels.colordialog.model.GradientColor;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class represents a 2-dimensional gradient containing all saturation and brightness values.
 *
 * @author BugelNiels
 */
public class SvGradient implements PropertyChangeListener {

    @Getter
    private final BufferedImage gradientImage;
    @Getter
    private final GradientColor gradientColor;

    @Getter
    private final int width;
    @Getter
    private final int height;

    /**
     * Creates a new hue gradient with the provided width and height.
     * The width and height do not affect the saturation/value values displayed, only the size of the gradient.
     *
     * @param width         The width the gradient should have.
     * @param height        The height the gradient should have.
     * @param gradientColor The color whose hue value will be used to determine how the gradient should be drawn.
     */
    public SvGradient(int width, int height, GradientColor gradientColor) {
        this.width = width;
        this.height = height;
        gradientImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.gradientColor = gradientColor;
        recalculateImage();
    }

    /**
     * Recalculates the gradient image based on the current hue value of the gradient color.
     */
    private void recalculateImage() {
        int[] gradientRaster = ((DataBufferInt) gradientImage.getRaster().getDataBuffer()).getData();
        float gradientSize = (float) height - 1;

        float hue = gradientColor.getHue();
        for (int y = 0; y < height; y++) {
            float value = 1 - y / gradientSize;
            for (int x = 0; x < width; x++) {
                gradientRaster[y * width + x] = Color.getHSBColor(hue, x / gradientSize, value).getRGB();
            }
        }
    }


    /**
     * Will recalculate the gradient anytime the hue of the gradient color updates.
     *
     * @param evt Property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GradientColor.HUE_CHANGED)) {
            gradientColor.setHue((float) evt.getNewValue());
            recalculateImage();
        }
    }
}
