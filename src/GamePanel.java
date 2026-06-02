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
        platforms.add(new Platform(0, 600, 1000, 100));
        // các bục nhỏ
        platforms.add(new Platform(200, 450, 150, 25));
        platforms.add(new Platform(420, 400, 100, 25));
        platforms.add(new Platform(620, 240, 120, 25));        
        platforms.add(new Platform(600, 400, 300, 200));
        
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
        int rightSide = player.getX() + player.getWidth();
        int leftSide = player.getX();
        int topSide = player.getY();
        int bottomSide = player.getY() +player.getHeight();

        for (Platform platform : platforms) {
            // Collision chiều nagng
            boolean horizontalOverlap = rightSide > platform.getX() 
                                    && leftSide < platform.getX() + platform.getWidth();
            // COLLISON chiều dọc            
            boolean verticalOverlap = bottomSide > platform.getY()
                                    && topSide < platform.getY() + platform.getHeight();
            // Nếu không đụng platform thì bỏ qua
            if (!horizontalOverlap || !verticalOverlap) {
                continue;
            }
            // Collision từ trên xuống
            boolean collisionTop =
                    bottomSide >= platform.getY()
                            && bottomSide <= platform.getY() + 20;
            // Nếu player đụng platform thì set player đứng trên platfomr
            if (collisionTop) {
                player.landOnGround(platform.getY());
                continue;
            }
            // Collision từ dưới lên
            boolean collisionBottom =
                    topSide <= platform.getY() + platform.getHeight()
                            && topSide >= platform.getY() + platform.getHeight() - 20;
            // nếu player rơi trúng platform thì set player đứng trên platform
            if (collisionBottom) {
                player.setY(platform.getY() + platform.getHeight());
                player.setVelocityY(0);
                continue;
            }
            // Collision 2 bên
            boolean collisionLeft =
                    rightSide >= platform.getX()
                            && leftSide <= platform.getX() + 20;
            //
            if (collisionLeft) {
                player.setX(platform.getX() - player.getWidth());
            }
            boolean collisionRight =
                leftSide <= platform.getX() + platform.getWidth()
                        && leftSide >= platform.getX() + platform.getWidth() - 20;
            if (collisionRight) {
                player.setX(platform.getX() + platform.getWidth());
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