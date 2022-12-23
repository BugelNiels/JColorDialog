package bugelniels.colordialog.view;

import bugelniels.colordialog.model.GradientColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

// TODO: add more representations and easy copy-paste
// TODO: add user input

/**
 * Panel that contains the information about the color, such as the RGB and HSV values.
 *
 * @author BugelNiels
 */
public class ColorInformationPanel extends JPanel implements PropertyChangeListener {

    private static final int ICON_WIDTH = 50;
    private static final int ICON_HEIGHT = 20;

    private final GradientColor gradientColor;
    private JLabel colorLabel;

    private JTextField redLabel;
    private JTextField greenLabel;
    private JTextField blueLabel;

    private JTextField hueLabel;
    private JTextField satLabel;
    private JTextField valLabel;

    private JTextField rgbIntLabel;

    /**
     * Initialises the panel.
     *
     * @param gradientColor The gradient color that this panel shows the info of.
     */
    public ColorInformationPanel(GradientColor gradientColor) {
        super(new GridBagLayout());
        this.gradientColor = gradientColor;
        paintLabels();
        colorLabels();
        setBorder(new EmptyBorder(10, 30, 10, 30));
    }

    private void paintLabels() {
        setLayout(new GridLayout(7, 1));
        add(paintColor());
        add(new JSeparator());
        add(paintRGB());
        add(new JSeparator());
        add(paintHSV());
        add(new JSeparator());
        add(paintRGBInt());
    }

    private JPanel paintColor() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel cLabel = new JLabel("Color:");
        panel.add(cLabel);
        colorLabel = new JLabel("");
        colorLabel.setIcon(createIcon(gradientColor.getColor()));
        panel.add(colorLabel);
        return panel;
    }

    private JPanel paintRGB() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));
        JLabel rLabel = new JLabel("Red:");
        panel.add(rLabel);
        JLabel gLabel = new JLabel("Green:");
        panel.add(gLabel);
        JLabel bLabel = new JLabel("Blue:");
        panel.add(bLabel);

        Color color = gradientColor.getColor();
        redLabel = new JTextField(String.valueOf(color.getRed()));
        greenLabel = new JTextField(String.valueOf(color.getGreen()));
        blueLabel = new JTextField(String.valueOf(color.getBlue()));
        addLabelsToPanel(redLabel, greenLabel, blueLabel, panel);
        return panel;
    }

    private JPanel paintHSV() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));
        JLabel hLabel = new JLabel("Hue:");
        panel.add(hLabel);
        JLabel sLabel = new JLabel("Saturation:");
        panel.add(sLabel);
        JLabel vLabel = new JLabel("Value:");
        panel.add(vLabel);

        hueLabel = new JTextField(String.valueOf(gradientColor.getHue()));
        satLabel = new JTextField(String.valueOf(gradientColor.getSaturation()));
        valLabel = new JTextField(String.valueOf(gradientColor.getBrightness()));

        addLabelsToPanel(hueLabel, satLabel, valLabel, panel);
        return panel;
    }

    private JPanel paintRGBInt() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel rLabel = new JLabel("RGB int:");
        panel.add(rLabel);
        rgbIntLabel = new JTextField(String.valueOf(gradientColor.getRGBInt()));
        panel.add(rgbIntLabel);
        return panel;
    }

    private void addLabelsToPanel(JTextField label1, JTextField label2, JTextField label3, JPanel panel) {
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
    }

    private void colorLabels() {
        List<JTextField> textFields = new ArrayList<>();
        textFields.add(redLabel);
        textFields.add(blueLabel);
        textFields.add(greenLabel);

        textFields.add(hueLabel);
        textFields.add(satLabel);
        textFields.add(valLabel);

        textFields.add(rgbIntLabel);

        for (JTextField text : textFields) {
            text.setEditable(false);
            text.setBackground(null);
            text.setBorder(null);
        }
    }

    private ImageIcon createIcon(Color main) {
        BufferedImage image = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(main);
        graphics.fillRect(0, 0, ICON_WIDTH, ICON_HEIGHT);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, ICON_WIDTH - 1, ICON_HEIGHT - 1);
        image.flush();
        return new ImageIcon(image);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
    }

    /**
     * Updates the information on this panel. Executed whenever the gradient color fires an event.
     *
     * @param evt The change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Color color = gradientColor.getColor();
        colorLabel.setIcon(createIcon(color));
        redLabel.setText(String.valueOf(color.getRed()));
        greenLabel.setText(String.valueOf(color.getGreen()));
        blueLabel.setText(String.valueOf(color.getBlue()));

        hueLabel.setText(String.valueOf(Math.round(gradientColor.getHue() * 100d) / 100d));
        satLabel.setText(String.valueOf(Math.round(gradientColor.getSaturation() * 100d) / 100d));
        valLabel.setText(String.valueOf(Math.round(gradientColor.getBrightness() * 100d) / 100d));

        rgbIntLabel.setText(String.valueOf(gradientColor.getRGBInt()));
        repaint();
    }
}
