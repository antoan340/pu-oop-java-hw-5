package Screen;
import javax.swing.*;
public class Modal extends JDialog {
    /**
     * @param "Клас с който се създава изкачащ прозорец като се инициализира"
     * @author Antoan
     */
    public Modal( JFrame parent, String title, String message ) {
        super(parent, title, true);
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(Button);
        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public static JButton Button = new JButton("Restart");
    /**
     * @param "Визоализиране на JPanel"
     * @author Antoan
     */
    public static void render(JFrame parent, String title, String message) {
        new Modal(parent, title, message);
    }

}