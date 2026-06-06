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
        platforms.add(new Platform(120, 520, 170, 25));
        platforms.add(new Platform(350, 440, 170, 25));
        platforms.add(new Platform(580, 360, 170, 25));
        platforms.add(new Platform(780, 280, 150, 25));

        coins.add(new Coin(190, 480, 25));
        coins.add(new Coin(420, 400, 25));
        coins.add(new Coin(650, 320, 25));
        coins.add(new Coin(830, 240, 25));

        enemies.add(new Enemy(300, 570, 40, 30, 200, 500));
        enemies.add(new Enemy(600, 330, 40, 30, 580, 750));
    }
    private void createLevel3() {
        platforms.add(new Platform(80, 520, 150, 25));
        platforms.add(new Platform(280, 460, 150, 25));
        platforms.add(new Platform(480, 400, 150, 25));
        platforms.add(new Platform(680, 340, 150, 25));
        platforms.add(new Platform(420, 250, 220, 25));

        coins.add(new Coin(140, 480, 25));
        coins.add(new Coin(340, 420, 25));
        coins.add(new Coin(540, 360, 25));
        coins.add(new Coin(740, 300, 25));
        coins.add(new Coin(510, 210, 25));

        enemies.add(new Enemy(250, 570, 40, 30, 180, 450));
        enemies.add(new Enemy(470, 220, 40, 30, 420, 640));
    }
}