import javax.swing.JPanel;

import model.Player;

import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    private Player player;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        player = new Player(100, 100, 50, 50);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(
                player.getX(),
                player.getY(),
                player.getWidth(),
                player.getHeight()
        );
    }
}