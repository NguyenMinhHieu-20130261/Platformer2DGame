package collision;

import java.util.ArrayList;

import model.Coin;
import model.Enemy;
import model.Platform;
import model.Player;

public class CollisionManager {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Coin> coins;
    // Check va chạm với platform
    public void checkPlatformCollision(Player player, ArrayList<Platform> platforms) {
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
                            && rightSide <= platform.getX() + 20;
            //
            if (collisionLeft) {
                player.setX(platform.getX() - player.getWidth());
                continue;
            }
            boolean collisionRight =
                leftSide <= platform.getX() + platform.getWidth()
                        && leftSide >= platform.getX() + platform.getWidth() - 20;
            if (collisionRight) {
                player.setX(platform.getX() + platform.getWidth());
            }
        }
    }
    // Collision enemy
    public boolean checkEnemyCollision(Player player, ArrayList<Enemy> enemies) {
        int rightSide = player.getX() + player.getWidth();
        int leftSide = player.getX();
        int topSide = player.getY();
        int bottomSide = player.getY() +player.getHeight();
        for (Enemy enemy : enemies) {
            boolean touchingEnemy = rightSide > enemy.getLeft()
                            && leftSide < enemy.getRight()
                            && bottomSide > enemy.getTop()
                            && topSide < enemy.getBottom();
            // Đụng enemy thì reset game
            if (touchingEnemy) {
                return true;
            }
        }
        return false;
    }
    // Check collision xu
    public boolean checkCoinCollision(Player player, ArrayList<Coin> coins) {
        int rightSide = player.getX() + player.getWidth();
        int leftSide = player.getX();
        int topSide = player.getY();
        int bottomSide = player.getY() + player.getHeight();
        boolean collectedCoinThisFrame = false;
        for (Coin coin : coins) {
            if (coin.isCollected()) {
                continue;
            }
            boolean touchingCoin = rightSide > coin.getX()
                            && leftSide < coin.getX() + coin.getSize()
                            && bottomSide > coin.getY()
                            && topSide < coin.getY() + coin.getSize();
            if (touchingCoin) {
                coin.collect();
                collectedCoinThisFrame = true;
            }
        }
        return collectedCoinThisFrame;
    }
}