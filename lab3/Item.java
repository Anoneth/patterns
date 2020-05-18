package lab3;

import java.awt.*;

public class Item {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private int width;
    private int height;

    public Item(int x, int y, int width, int height, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}