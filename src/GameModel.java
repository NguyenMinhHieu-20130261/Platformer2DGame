import java.util.ArrayList;

import model.Coin;
import model.Platform;
import model.Player;
import model.Enemy;
import model.GameState;

public class GameModel {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;

    private int score = 0;
    private int lives = 3;
    private GameState gameState = GameState.START;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameModel() {
        player = new Player(100, 100, 50, 50);
        platforms = new ArrayList<>();
        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        //măt đất
        platforms.add(new Platform(0, 600, 1000, 100));
        // các bục nhỏ
        platforms.add(new Platform(200, 450, 150, 25));
        platforms.add(new Platform(420, 400, 100, 25));
        platforms.add(new Platform(620, 240, 120, 25));        
        platforms.add(new Platform(600, 400, 300, 200));
        // xu
        coins.add(new Coin(250, 360, 25));
        coins.add(new Coin(480, 280, 25));
        coins.add(new Coin(660, 200, 25));
        // enemy
        enemies.add(new Enemy(300, 570, 40, 30, 200, 500));
        enemies.add(new Enemy(650, 370, 40, 30, 600, 900));
        // enemies.add(new Enemy(420, 370, 40, 30, 420, 520));
    }
    // Hàm cập nhật 
    public void update(int screenWidth) {
        if (gameState != GameState.PLAYING) {
            return;
        }
        if (leftPressed) {
            player.moveLeft();
        }
        if (rightPressed) {
            player.moveRight();
        }
        player.applyGravity();
        checkPlatformCollision();
        checkCoinCollision();
        updateEnemies();
        checkEnemyCollision();
        player.limitInScreen(screenWidth);
    }
    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }
    // Hàm reset game
    public void resetGame() {
        player = new Player(100, 100, 50, 50);
        score = 0;
        lives = 3;
        gameState = GameState.PLAYING;

        leftPressed = false;
        rightPressed = false;
        for (Coin coin : coins) {
            coin.reset();
        }
    }
    // Collision enemy
    private void checkEnemyCollision() {
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
                 lives--;
                if (lives <= 0) {
                    gameState = GameState.GAME_OVER;
                    leftPressed = false;
                    rightPressed = false;
                } else {
                    player = new Player(100, 100, 50, 50);
                    leftPressed = false;
                    rightPressed = false;
                }
            }
        }
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
    // Check collision xu
    private void checkCoinCollision() {
        int rightSide = player.getX() + player.getWidth();
        int leftSide = player.getX();
        int topSide = player.getY();
        int bottomSide = player.getY() +player.getHeight();
        for (Coin coin : coins) {
            // Xu đã thu thập thì skip
            if (coin.isCollected()) {
                continue;
            }
            boolean touchingCoin = rightSide > coin.getX()
                            && leftSide < coin.getX() + coin.getSize()
                            && bottomSide > coin.getY()
                            && topSide < coin.getY() + coin.getSize();
            // Nếu chạm xu thì + 1 điểm 
            if (touchingCoin) {
                coin.collect();
                score += 1;
                checkWinCondition();
            }
        }
    }
    private void checkWinCondition() {
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                return;
            }
        }
        gameState = GameState.GAME_WIN;
        leftPressed = false;
        rightPressed = false;
    }
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
    // Hảm nhảy
        public void jumpPlayer() {
        player.jump();
    }
    // Hàm tạo char + platform
    public Player getPlayer() {
        return player;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }
    // Hàm collect coin
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public int getScore() {
        return score;
    }
    // Hàm mạng
    public int getLives() {
        return lives;
    }
    public boolean isGameOver() {
        return gameState == GameState.GAME_OVER;
    }
    public boolean isGameWin() {
        return gameState == GameState.GAME_WIN;
    }
    public void startGame() {
        gameState = GameState.PLAYING;
    }
    public GameState getGameState() {
        return gameState;
    }
}