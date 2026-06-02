import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;
import model.Platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

public class GamePanel extends JPanel{
    private GameModel model;

    public GamePanel(GameModel model) {
        this.model = model;
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        Timer timer = new Timer(10, e -> {
            model.update(getWidth());
            repaint();
        });
        timer.start();
    }
    // Vẽ nhân vật
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawPlatforms(g);
    }
    private void drawPlayer(Graphics g) {
        Player player = model.getPlayer();

        g.setColor(Color.RED);
        g.fillRect(
                player.getX(),
                player.getY(),
                player.getWidth(),
                player.getHeight()
        );
    }
    //Vẽ platform
    private void drawPlatforms(Graphics g) {
        g.setColor(Color.GREEN);

        for (Platform platform : model.getPlatforms()) {
            g.fillRect(
                    platform.getX(),
                    platform.getY(),
                    platform.getWidth(),
                    platform.getHeight()
            );
        }
    }
}