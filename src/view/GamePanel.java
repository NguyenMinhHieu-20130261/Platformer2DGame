package view;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;
import model.Coin;
import model.Enemy;
import model.GameModel;
import model.GameState;
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
        drawPlatforms(g);
        drawCoins(g);
        drawEnemy(g);
        drawPlayer(g);
        drawScore(g);
        drawLives(g);
        drawLevel(g);
        if (model.getGameState() == GameState.GAME_OVER) {
            drawGameOver(g);
        }
        if (model.getGameState() == GameState.GAME_WIN) {
            drawGameWin(g);
        }
        if (model.getGameState() == GameState.START) {
            drawStartScreen(g);
        }
        if (model.getGameState() == GameState.PAUSE) {
            drawGamePause(g);
        }
    }
    // Vẽ nhân vật
    private void drawPlayer(Graphics g) {
        Player player = model.getPlayer();
        g.setColor(Color.RED);
        g.fillRect(player.getX(),player.getY(),player.getWidth(),player.getHeight());
    }
    private void drawEnemy(Graphics g) {
        for (Enemy enemy : model.getEnemies()) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight());
        }
    }
    //Vẽ platform
    private void drawPlatforms(Graphics g) {
        g.setColor(Color.GREEN);
        for (Platform platform : model.getPlatforms()) {
            g.fillRect(platform.getX(),platform.getY(),platform.getWidth(),platform.getHeight());
        }
    }
    // vẽ xu
    private void drawCoins(Graphics g) {
        g.setColor(Color.YELLOW);
        for (Coin coin : model.getCoins()) {
            if (!coin.isCollected()) {
                g.fillOval( coin.getX(),coin.getY(),coin.getSize(),coin.getSize());
            }
        }
    }
    // vẽ điểm số
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), 20, 20);
    }
    private void drawLives(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Lives: " + model.getLives(), 20, 40);
        g.setColor(Color.RED);
        for (int i = 0; i < model.getLives(); i++) {
            g.fillOval(80 + i * 25, 28, 12, 12);
            g.fillOval(88 + i * 25, 28, 12, 12);
            g.fillPolygon(new int[]{80 + i * 25, 100 + i * 25, 90 + i * 25},
                        new int[]{36, 36, 50}, 3);
        }
    }
    // Màn hình kết thúc game
    private void drawGameOver(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(40f));
        g.drawString("GAME OVER!", getWidth() / 2 - 120, getHeight() / 2 - 20);

        g.setFont(g.getFont().deriveFont(20f));
        g.drawString("Bấm nút R để reset Game", getWidth() / 2 - 115, getHeight() / 2 + 25);
    }
    // Madn hình win game
    private void drawGameWin(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.YELLOW);
        g.setFont(g.getFont().deriveFont(40f));
        g.drawString("GAME WIN!", getWidth() / 2 - 100, getHeight() / 2 - 20);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(20f));
        g.drawString("Bấm nút R để chơi lại", getWidth() / 2 - 100, getHeight() / 2 + 25);
    }
    private void drawStartScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(40f));
        g.drawString("2D PLATFORMER", getWidth() / 2 - 150, getHeight() / 2 - 40);
        g.setFont(g.getFont().deriveFont(18f));
        g.drawString("Bấm ENTER để chơi", getWidth() / 2 - 90, getHeight() / 2 + 10);
        g.setFont(g.getFont().deriveFont(14f));
        g.drawString("A / D để di chuyển, SPACE để nhảy", getWidth() / 2 - 105, getHeight() / 2 + 40);
    }
    private void drawGamePause(Graphics g) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(40f));
        g.drawString("PAUSED", getWidth() / 2 - 80, getHeight() / 2 - 20);

        g.setFont(g.getFont().deriveFont(18f));
        g.drawString("Bấm P để tiếp tục", getWidth() / 2 - 80, getHeight() / 2 + 25);
    }
    //
    private void drawLevel(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Level: " + model.getCurrentLevel(), 20, 60);
    }
}