package bugelniels.colordialog.model.gradients;

import bugelniels.colordialog.model.GradientColor;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Represents a 1-dimensional gradient containing all different hue values.
 *
 * @author BugelNiels
 */
public class HueGradient {

    @Getter
    private final int height;
    @Getter
    private final int width;
    private final double[] reds;
    private final double[] greens;
    private final double[] blues;

    private final BufferedImage hueGradientImage;

    /**
     * Creates a new hue gradient with the provided width and height.
     * The width and height do not affect the hue values displayed, only the size of the gradient.
     *
     * @param width         The width the gradient should have.
     * @param height        The height the gradient should have.
     * @param gradientColor The color whose hue value will be used for initialization.
     */
    public HueGradient(int width, int height, GradientColor gradientColor) {
        this.width = width;
        this.height = height;
        reds = new double[height];
        greens = new double[height];
        blues = new double[height];
        hueGradientImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        calculateImage();
    }

    /**
     * Initializes the image with the different hue values.
     */
    private void calculateImage() {
        calcHueArray();
        int[] gradientRaster = ((DataBufferInt) hueGradientImage.getRaster().getDataBuffer()).getData();
        int currentColorRGB;
        for (int y = 0; y < height; y++) {
            currentColorRGB = getRGBColorAtIndex(y);
            for (int x = 0; x < width; x++) {
                gradientRaster[y * width + x] = currentColorRGB;
            }
        }
    }

    /**
     * Fills in the reds, greens and blues arrays.
     */
    private void calcHueArray() {
        int[] order = new int[]{1, 0, 2};
        int segmentWidth = height / 6;
        double direction = 6;
        int count = 0;
        int i;
        // Start of RGB hue array; starts at a red color
        int[] rgb = new int[]{255, 0, 0};
        for (i = 0; i < 255 - segmentWidth; i += segmentWidth) {
            calcSegment(rgb, i, i + segmentWidth, order[count % 3], direction);
            count++;
            direction *= -1;
        }
        calcSegment(rgb, i, 255, order[count % 3], direction);
    }

    private void calcSegment(int[] rgb, int start, int end, int colorChannel, double direction) {
        for (int i = start; i < end; i++) {
            rgb[colorChannel] += direction;
            reds[i] = rgb[0];
            greens[i] = rgb[1];
            blues[i] = rgb[2];
        }
    }

    /**
     * Gets hue gradient image.
     *
     * @return the hue gradient image
     */
    public BufferedImage getHueGradientImage() {
        return hueGradientImage;
    }

    private int getRGBColorAtIndex(int index) {
        return (int) (65536 * reds[index] + 256 * greens[index] + blues[index]);
    }

}
