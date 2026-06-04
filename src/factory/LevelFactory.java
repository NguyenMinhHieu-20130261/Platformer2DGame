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
        }
    }
    private void createLevel1() {
        platforms.add(new Platform(200, 450, 150, 25));
        platforms.add(new Platform(420, 400, 100, 25));
        platforms.add(new Platform(620, 240, 120, 25));
        platforms.add(new Platform(600, 400, 300, 200));

        coins.add(new Coin(250, 360, 25));
        coins.add(new Coin(480, 280, 25));
        coins.add(new Coin(660, 200, 25));

        enemies.add(new Enemy(300, 570, 40, 30, 200, 500));
        enemies.add(new Enemy(650, 370, 40, 30, 600, 900));
    }
    private void createLevel2() {
        platforms.add(new Platform(150, 500, 120, 25));
        platforms.add(new Platform(350, 420, 120, 25));
        platforms.add(new Platform(550, 340, 120, 25));
        platforms.add(new Platform(750, 260, 120, 25));

        coins.add(new Coin(180, 460, 25));
        coins.add(new Coin(380, 380, 25));
        coins.add(new Coin(580, 300, 25));
        coins.add(new Coin(780, 220, 25));

        enemies.add(new Enemy(300, 570, 40, 30, 150, 450));
        // enemies.add(new Enemy(570, 310, 40, 30, 550, 670));
    }
}