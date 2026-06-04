package model;

import java.util.ArrayList;
import factory.LevelFactory;

public class GameModel {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;
    private LevelFactory levelFactory;

    private int score = 0;
    private int lives = 3;
    private int currentLevel = 1;
    private GameState gameState = GameState.START;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameModel() {
        player = new Player(100, 100, 50, 50);
        platforms = new ArrayList<>();
        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        levelFactory = new LevelFactory(platforms, coins, enemies);
        loadCurrentLevel();
    }
    private void loadCurrentLevel() {
        levelFactory.createLevel(currentLevel);
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
        currentLevel = 1;
        score = 0;
        lives = 3;
        player = new Player(100, 100, 50, 50);
        gameState = GameState.PLAYING;
        leftPressed = false;
        rightPressed = false;
        loadCurrentLevel();
    }
    public void resetPlayerPos(){
        player = new Player(100, 100, 50, 50);
        leftPressed = false;
        rightPressed = false;   
    }
    private void loseLife() {
        lives--;
        if (lives <= 0) {
            gameState = GameState.GAME_OVER;
            leftPressed = false;
            rightPressed = false;
        } else {
            resetPlayerPos();
        }
    }
    // Hàm pause game
    public void togglePause() {
        if (gameState == GameState.PLAYING) {
            gameState = GameState.PAUSE;
            leftPressed = false;
            rightPressed = false;
        } else if (gameState == GameState.PAUSE) {
            gameState = GameState.PLAYING;
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
                loseLife();
                return;
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
    // Check collision xu
    private void checkCoinCollision() {
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
                score += 1;
                collectedCoinThisFrame = true;
            }
        }
        if (collectedCoinThisFrame) {
            checkWinCondition();
        }
    }
    private void checkWinCondition() {
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                return;
            }
        }
        if (currentLevel == 1) {
            currentLevel = 2;
            loadCurrentLevel();
            resetPlayerPos();
        } else {
            gameState = GameState.GAME_WIN;
            leftPressed = false;
            rightPressed = false;
        }
    }
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
    // Hảm nhảy
    public void jumpPlayer() {
        if (gameState == GameState.PLAYING) {
            player.jump();
        }
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
    // Hàm lấy STATE Game
    public void startGame() {
        if (gameState == GameState.START) {
            gameState = GameState.PLAYING;
        }
    }  
    public GameState getGameState() {
        return gameState;
    }
    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }
}