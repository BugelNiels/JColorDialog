package bugelniels.colordialog;

import bugelniels.colordialog.controller.CrossHairSelectionController;
import bugelniels.colordialog.model.GradientColor;
import bugelniels.colordialog.model.crosshairs.HueCrossHair;
import bugelniels.colordialog.model.crosshairs.SvCrossHair;
import bugelniels.colordialog.model.gradients.HueGradient;
import bugelniels.colordialog.model.gradients.SvGradient;
import bugelniels.colordialog.view.ColorInformationPanel;
import bugelniels.colordialog.view.HueSliderPanel;
import bugelniels.colordialog.view.SvSelectionPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Spawns a new JColorDialog. The aim is to provide an easy-to-use (both in terms of implementation and user experience)
 * component that can be used for picking colors. The color dialog can be initialized with an existing color or without
 * one. Whenever the correct color has been chosen, the dialog can be closed.
 *
 * @author BugelNiels
 */
public class JColorDialog extends JDialog {

    private static final int DIALOG_WIDTH = 600;
    private static final int DIALOG_HEIGHT = 300;

    private static final int HSV_GRADIENT_WIDTH = 255;
    private static final int HSV_GRADIENT_HEIGHT = 255;

    private static final int HUE_GRADIENT_WIDTH = 20;
    private static final int HUE_GRADIENT_HEIGHT = 255;
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static final int HSV_GRADIENT_OFFSET_LEFT = 1;

    private final GradientColor gradientColor;

    // TODO: make this not private to give users the option to attach listeners to it.

    /**
     * Instantiates a new JColorDialog.
     *
     * @param color The color to initialise the dialog with.
     */
    private JColorDialog(Color color) {
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setResizable(false);

        gradientColor = new GradientColor(color);
        JPanel colorInfoPanel = initColorInfoPanel(gradientColor);
        JPanel hsvPanel = initHsvPanel(gradientColor);
        JPanel huePanel = initHuePanel(gradientColor);
        add(initView(hsvPanel, huePanel, colorInfoPanel));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Shows a new color dialog. Will return whenever the user closes the window.
     * @param color The color that the color chooser should be initialized with.
     * @return The color picked by the user.
     */
    public static Color showColorDialog(Color color) {
        JColorDialog c = new JColorDialog(color);
        return c.getPickedColor();
    }

    /**
     * Shows a new color dialog. Will return whenever the user closes the window.
     * @return The color picked by the user.
     */
    public static Color showColorDialog() {
        return showColorDialog(DEFAULT_COLOR);
    }

    private JPanel initColorInfoPanel(GradientColor color) {
        ColorInformationPanel informationPanel = new ColorInformationPanel(color);
        color.addListener(informationPanel);
        return informationPanel;
    }

    private JPanel initHuePanel(GradientColor color) {

        HueGradient hueGradient = new HueGradient(HUE_GRADIENT_WIDTH, HUE_GRADIENT_HEIGHT, color);
        HueCrossHair hueCrossHair = new HueCrossHair(color, hueGradient);
        HueSliderPanel hueSliderPanel = new HueSliderPanel(hueCrossHair);
        new CrossHairSelectionController(hueCrossHair, hueSliderPanel);
        color.addListener(hueSliderPanel);
        return hueSliderPanel;
    }

    private JPanel initHsvPanel(GradientColor color) {
        SvGradient svGradient = new SvGradient(HSV_GRADIENT_WIDTH, HSV_GRADIENT_HEIGHT, color);
        SvCrossHair svCrosshair = new SvCrossHair(color, svGradient);
        SvSelectionPanel svSelectionPanel = new SvSelectionPanel(svCrosshair);
        new CrossHairSelectionController(svCrosshair, svSelectionPanel);
        color.addListener(svGradient);
        color.addListener(svSelectionPanel);
        return svSelectionPanel;
    }

    private JComponent initView(JPanel hsvSelectionPanel, JPanel hueSliderPanel, JPanel colorInformationPanel) {

        JSplitPane selectorPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, hsvSelectionPanel, hueSliderPanel);
        selectorPane.setDividerLocation(HSV_GRADIENT_WIDTH + HSV_GRADIENT_OFFSET_LEFT);
        selectorPane.setEnabled(false);
        selectorPane.setDividerSize(0);

        JSplitPane informationPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, selectorPane, colorInformationPanel);
        informationPane.setDividerLocation(HSV_GRADIENT_HEIGHT + HSV_GRADIENT_OFFSET_LEFT + HUE_GRADIENT_WIDTH * 2);
        informationPane.setEnabled(false);
        informationPane.setDividerSize(0);

        return informationPane;
    }

    /**
     * Retrieves picked color.
     *
     * @return the picked color
     */
    private Color getPickedColor() {
        return gradientColor.getColor();
    }

}
