package model;

import java.util.ArrayList;
import factory.LevelFactory;
import service.CollisionManager;

public class GameModel {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;
    private LevelFactory levelFactory;
    private CollisionManager collisionManager;

    private int score = 0;
    private int lives = 3;
    private int currentLevel = 1;
    private final int MAX_LEVEL = 3;
    private GameState gameState = GameState.START;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameModel() {
        player = new Player(100, 100, 50, 50);
        platforms = new ArrayList<>();
        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        collisionManager = new CollisionManager();
        loadCurrentLevel();
        levelFactory = new LevelFactory(platforms, coins, enemies);
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
        collisionManager.checkPlatformCollision(player, platforms);
        boolean collectedCoin = collisionManager.checkCoinCollision(player, coins);
        if (collectedCoin) {
            score++;
            checkWinCondition();
        }
        updateEnemies();
        boolean touchingEnemy = collisionManager.checkEnemyCollision(player, enemies);
        if (touchingEnemy) {
            loseLife();
        }
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
    private void checkWinCondition() {
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                return;
            }
        }
        if (currentLevel < MAX_LEVEL) {
            currentLevel++;
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