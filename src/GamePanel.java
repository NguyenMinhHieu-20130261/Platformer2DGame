import javax.swing.JPanel;

import model.Player;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    private Player player;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        player = new Player(100, 100, 50, 50);
        //Hàm test di chuyển nva
        Timer timer = new Timer(16, e -> {
            player.moveRight();
            repaint();
        });
        timer.start();
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