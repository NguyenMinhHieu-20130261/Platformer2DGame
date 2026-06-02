package model;

public class Coin {
    private int x;
    private int y;
    private int size;
    private boolean collected;

    public Coin(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.collected = false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public boolean isCollected() {
        return collected;
    }
    public void collect() {
        collected = true;
    }
    
}