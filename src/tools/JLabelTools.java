
package tools;

import javax.swing.JLabel;

public class JLabelTools {
    public static void changeFontSize(JLabel j, float size){
        j.setFont(j.getFont().deriveFont(size));
    }
}
