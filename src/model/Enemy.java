package model;

public class Enemy {
    private int x;
    private int y;
    private int width;
    private int height;
    // tốc độ di chuyển sẽ chậm hơn player 
    private int speed = 2;
    // phạm vi di chuyển của enemy
    private int startX;
    private int endX;

    public Enemy(int x, int y, int width, int height, int startX, int endX) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.endX = endX;
    }
    // Hàm di chuyển enemy
    public void update() {
        x += speed;
        if (x <= startX || x + width >= endX) {
            speed = -speed;
        }
    }
    public int getLeft() {
        return x;
    }
    public int getRight() {
        return x + width;
    }
    public int getTop() {
        return y;
    }
    public int getBottom() {
        return y + height;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}