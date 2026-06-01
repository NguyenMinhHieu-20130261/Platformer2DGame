package model;

public class Player {
    private int x;
    private int y;
    private int width;
    private int height;
    // Tốc di chuyển
    private int speed = 5;
    // Tốc độ rơi (velocity) và trọng lực (gravity)
    private double velocityY = 0;
    private double gravity = 0.5;
    // Nhảy
    private double jumpPower = -15;
    // biến check coi nv có nằm trên mặt đấy hay ko
    private boolean onGround = false;

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
    // Hàm nhảy
    public void jump() {
        if (onGround) {
            velocityY = jumpPower;
            onGround = false;
        }
    }
    // Hàm áp dụng trọng lực
    public void applyGravity() {
        velocityY += gravity;
        y += velocityY;
    }
    public void landOnGround(int groundY) {
        y = groundY - height;
        velocityY = 0;
        onGround = true;
    }
    public int getBottom() {
        return y + height;
    }
}