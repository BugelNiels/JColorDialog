package bugelniels.colordialog.view;

import bugelniels.colordialog.model.crosshairs.SvCrossHair;
import bugelniels.colordialog.model.gradients.SvGradient;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Panel responsible for drawing the saturation-value gradient and its cross-hair.
 *
 * @author BugelNiels
 */
public class SvSelectionPanel extends JPanel implements PropertyChangeListener {

    private final SvGradient svGradient;
    private final SvCrossHair svCrossHair;

    /**
     * Creates a new panel.
     *
     * @param crossHair The cross-hair that should be drawn. This cross-hair has as its parent the gradient itself.
     */
    public SvSelectionPanel(SvCrossHair crossHair) {
        this.svGradient = crossHair.getParent();
        this.svCrossHair = crossHair;
    }

    private void paintGradient(Graphics2D g) {
        g.drawImage(svGradient.getGradientImage(), 0, 0, null);
    }

    private void paintCrossHair(Graphics2D g) {
        g.setColor(svCrossHair.getCrossHairColor());
        g.setStroke(new BasicStroke(2));
        g.drawLine(svCrossHair.getX() - svCrossHair.getSize() / 2, svCrossHair.getY(),
                svCrossHair.getX() + svCrossHair.getSize() / 2, svCrossHair.getY());
        g.drawLine(svCrossHair.getX(), svCrossHair.getY() - svCrossHair.getSize() / 2,
                svCrossHair.getX(), svCrossHair.getY() + svCrossHair.getSize() / 2);
    }


    /**
     * Paints a cross-hair and a saturation-value gradient.
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
     * Repaints the panel. Fires on any event.
     *
     * @param evt The event sent from the gradient color.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
