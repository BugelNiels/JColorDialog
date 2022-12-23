import bugelniels.colordialog.JColorDialog;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class Runtest {

    @Test
    void testInterface() {
        Color color = JColorDialog.showColorDialog();
    }

}
