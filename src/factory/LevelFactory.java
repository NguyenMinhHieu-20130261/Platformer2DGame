package factory;

import java.util.ArrayList;

import model.Coin;
import model.Enemy;
import model.Platform;

public class LevelFactory {
    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;
    private ArrayList<Enemy> enemies;

    public LevelFactory(ArrayList<Platform> platforms,ArrayList<Coin> coins,ArrayList<Enemy> enemies) {
        this.platforms = platforms;
        this.coins = coins;
        this.enemies = enemies;
    }
    // Hàm tạo lvl
    public void createLevel(int level) {
        platforms.clear();
        coins.clear();
        enemies.clear();
        // mặt đất
        platforms.add(new Platform(0, 600, 1000, 100));
        if (level == 1) {
            createLevel1();
        } else if (level == 2) {
            createLevel2();
        } else if (level == 3) {
            createLevel3();
        }
    }
    private void createLevel1() {
        // Platform
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
    }
    private void createLevel2() {
        // platform
        platforms.add(new Platform(100, 520, 180, 25));
        platforms.add(new Platform(330, 460, 180, 25));
        platforms.add(new Platform(560, 400, 180, 25));
        platforms.add(new Platform(760, 330, 170, 25));
        // coin
        coins.add(new Coin(175, 480, 25));
        coins.add(new Coin(405, 420, 25));
        coins.add(new Coin(635, 360, 25));
        coins.add(new Coin(830, 290, 25));
        // enemy
        enemies.add(new Enemy(300, 570, 40, 30, 220, 500));
        enemies.add(new Enemy(600, 370, 40, 30, 560, 740));
    }
    private void createLevel3() {
        // platform
        platforms.add(new Platform(80, 520, 170, 25));
        platforms.add(new Platform(300, 460, 170, 25));
        platforms.add(new Platform(520, 400, 170, 25));
        platforms.add(new Platform(720, 330, 170, 25));
        platforms.add(new Platform(430, 250, 240, 25));
        // coin
        coins.add(new Coin(150, 480, 25));
        coins.add(new Coin(370, 420, 25));
        coins.add(new Coin(590, 360, 25));
        coins.add(new Coin(790, 290, 25));
        coins.add(new Coin(540, 210, 25));
        // enemy
        enemies.add(new Enemy(250, 570, 40, 30, 180, 420));
        enemies.add(new Enemy(470, 220, 40, 30, 430, 630));
    }
}