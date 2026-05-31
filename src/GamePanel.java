import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    public GamePanel() {
        this.setBackground(Color.BLACK);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(100, 100, 50, 50);
    }
}