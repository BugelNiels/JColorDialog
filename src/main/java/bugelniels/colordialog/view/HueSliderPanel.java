package bugelniels.colordialog.view;

import bugelniels.colordialog.model.GradientColor;
import bugelniels.colordialog.model.crosshairs.HueCrossHair;
import bugelniels.colordialog.model.gradients.HueGradient;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Panel responsible for drawing the hue gradient and its cross-hair.
 *
 * @author BugelNiels
 */
public class HueSliderPanel extends JPanel implements PropertyChangeListener {

    private static final int STROKE_WIDTH = 3;
    private final HueGradient hueGradient;
    private final HueCrossHair hueCrossHair;

    /**
     * Creates a new panel.
     *
     * @param crossHair The cross-hair that should be drawn. This cross-hair has as its parent the gradient itself.
     */
    public HueSliderPanel(HueCrossHair crossHair) {
        this.hueCrossHair = crossHair;
        this.hueGradient = crossHair.getParent();
    }

    private void paintGradient(Graphics2D g) {
        g.drawImage(hueGradient.getHueGradientImage(), 0, 0, null);
    }

    private void paintCrossHair(Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(STROKE_WIDTH));
        g.drawLine(0, hueCrossHair.getY(), hueGradient.getWidth(), hueCrossHair.getY());
    }

    /**
     * Paints a cross-hair and a hue gradient.
     *
     * @param g the <code>Graphics</code> object used to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        paintGradient(g2d);
        paintCrossHair(g2d);
    }

    /**
     * Repaints the panel. Happens only when the hue value of the gradient color is changed.
     *
     * @param evt The event sent from the gradient color.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GradientColor.HUE_CHANGED)) {
            repaint();
        }
    }
}
