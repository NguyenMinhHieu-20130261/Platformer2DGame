import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{

    private Player player;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        player = new Player(100, 100, 50, 50);

        this.setFocusable(true);
        this.addKeyListener(this);
        //Hàm test di chuyển nva
        Timer timer = new Timer(16, e -> {
            move();
            repaint();
        });
        timer.start();
    }
    private void move() {
        if (leftPressed) {
            player.moveLeft();
        }

        if (rightPressed) {
            player.moveRight();
        }
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
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}