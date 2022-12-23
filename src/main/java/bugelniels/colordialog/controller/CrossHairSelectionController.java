package bugelniels.colordialog.controller;

import bugelniels.colordialog.model.crosshairs.CrossHair;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse selection controller for cross-hairs. Updates the position of the cross-hair to the position of the mouse.
 *
 * @author BugelNiels
 */
public class CrossHairSelectionController extends MouseInputAdapter {

    private final CrossHair crossHair;

    /**
     * Adds a new selection listener to the provided panel.
     *
     * @param crossHair The cross-hair associated with the provided panel.
     * @param panel     The panel to listen to.
     *                  The coordinates of the cross-hair are assumed to be in the space of the panel.
     */
    public CrossHairSelectionController(CrossHair crossHair, JPanel panel) {
        this.crossHair = crossHair;
        initMouseListeners(panel);
    }

    /**
     * Adds the relevant mouse listeners to the panel.
     *
     * @param panel The panel the mouse listeners will be added to.
     */
    private void initMouseListeners(JPanel panel) {
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    /**
     * Whenever the mouse is pressed, it will update the cross-hair to the coordinates of the mouse event.
     *
     * @param m Mouse event.
     */
    @Override
    public void mousePressed(MouseEvent m) {
        updateCrossHairCoordinates(m);
    }

    /**
     * Whenever the mouse is dragged, it will update the cross-hair to the coordinates of the mouse event.
     *
     * @param m Mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent m) {
        updateCrossHairCoordinates(m);
    }

    /**
     * Whenever the mouse is released, it will update the cross-hair to the coordinates of the mouse event.
     *
     * @param m Mouse event
     */
    @Override
    public void mouseReleased(MouseEvent m) {
        updateCrossHairCoordinates(m);
    }

    /**
     * Updates the position of the cross-hair according to the provided mouse event.
     *
     * @param m Mouse event containing the x and y coordinates.
     */
    protected void updateCrossHairCoordinates(MouseEvent m) {
        crossHair.setX(m.getX());
        crossHair.setY(m.getY());

    }

}
