import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;
import model.Coin;
import model.Enemy;
import model.Platform;

import java.awt.Color;
import java.awt.Graphics;

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
    // Vẽ components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawEnemy(g);
        drawPlatforms(g);
        drawCoins(g);
        drawScore(g);
    }
    // Vẽ nhân vật
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
    private void drawEnemy(Graphics g) {
        for (Enemy enemy : model.getEnemies()) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(
                    enemy.getX(),
                    enemy.getY(),
                    enemy.getWidth(),
                    enemy.getHeight()
            );
        }
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
    // vẽ xu
    private void drawCoins(Graphics g) {
        g.setColor(Color.YELLOW);

        for (Coin coin : model.getCoins()) {
            if (!coin.isCollected()) {
                g.fillOval(
                        coin.getX(),
                        coin.getY(),
                        coin.getSize(),
                        coin.getSize()
                );
            }
        }
    }
    // vẽ điểm số
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), 20, 20);
    }
}