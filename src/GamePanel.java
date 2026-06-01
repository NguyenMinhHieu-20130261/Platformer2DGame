import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;
import model.Platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener{

    private Player player;
    private ArrayList<Platform> platforms;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        player = new Player(100, 100, 50, 50);

        platforms = new ArrayList<>();
        //măt đất
        platforms.add(new Platform(0, 600, 900, 100));
        // các bục nhỏ
        platforms.add(new Platform(200, 400, 150, 25));
        platforms.add(new Platform(430, 320, 150, 25));
        platforms.add(new Platform(620, 240, 120, 25));
        
        //Hàm test di chuyển nva
        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });
        timer.start();
    }
    // Hàm cập nhật 
    private void update() {
        if (leftPressed) {
            player.moveLeft();
        }
        if (rightPressed) {
            player.moveRight();
        }
        player.applyGravity();
        checkPlatformCollision();
        player.limitInScreen(getWidth());
    }  
    // Check va chạm với platform
    private void checkPlatformCollision() {
        for (Platform platform : platforms) {
            boolean horizontalOverlap =
                    player.getX() + player.getWidth() > platform.getX()
                            && player.getX() < platform.getX() + platform.getWidth();
            if (!horizontalOverlap) {
                continue;
            }
            // Player rơi từ trên xuống platform
            boolean landingOnPlatform =
                    player.getBottom() >= platform.getY()
                            && player.getBottom() <= platform.getY() + 20;

            if (landingOnPlatform) {
                player.landOnGround(platform.getY());
            }
            // Player nhảy từ dưới lên, đầu đụng đáy platform
            boolean hittingPlatformFromBelow =
                    player.getTop() <= platform.getY() + platform.getHeight()
                            && player.getTop() >= platform.getY() + platform.getHeight() - 20;
            if (hittingPlatformFromBelow) {
                player.setY(platform.getY() + platform.getHeight());
                player.setVelocityY(10);
            }
        }
    }
    // Vẽ nhân vật
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawPlatforms(g);
    }
    private void drawPlayer(Graphics g){
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
        for (Platform platform : platforms) {
            g.fillRect(
                    platform.getX(),
                    platform.getY(),
                    platform.getWidth(),
                    platform.getHeight()
            );
        }
    }
    // Bấm nút
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
            player.jump();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}