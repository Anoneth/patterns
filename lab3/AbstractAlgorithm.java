package lab3;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public abstract class AbstractAlgorithm {
    protected List<Item> items;

    public AbstractAlgorithm() {
        items = new ArrayList<>();
    }

    public void tick(Graphics g) {
        moveItems();
        calcSpeeds();
        drawItems(g);
    }

    public void moveItems() {
        for (Item item : items) {
            int x = item.getX() + item.getxSpeed();
            if (x - item.getWidth() / 2 < 0) {
                item.setxSpeed(item.getxSpeed() * -1);
                x = item.getX() - item.getxSpeed() + item.getWidth() / 2;
            } else if (x + item.getWidth() / 2 > 500) {
                item.setxSpeed(item.getxSpeed() * -1);
                x = item.getX() + item.getxSpeed() - item.getWidth() / 2;
            }

            int y = item.getY() + item.getySpeed();
            if (y - item.getHeight() / 2 < 0) {
                item.setySpeed(item.getySpeed() * -1);
                y = item.getY() - item.getySpeed() + item.getHeight() / 2;
            } else if (y + item.getHeight() / 2 > 500) {
                item.setySpeed(item.getySpeed() * -1);
                y = item.getY() + item.getySpeed() - item.getHeight() / 2;
            }

            item.setX(x);
            item.setY(y);
        }
    }

    public abstract void calcSpeeds();

    public abstract void drawItems(Graphics g);

    public abstract void addItem();

    protected void addItem(Item item) {
        items.add(item);
    }
}