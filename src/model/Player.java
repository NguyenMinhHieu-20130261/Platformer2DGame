package model;

public class Player {
    private int x;
    private int y;
    private int width;
    private int height;
    // Tốc di chuyển
    private int speed = 5;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    // Hàm di chuyển nv
     public void moveLeft() {
        x -= speed;
    }
    public void moveRight() {
        x += speed;
    }
}