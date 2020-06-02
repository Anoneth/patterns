package lab3;

import java.awt.*;

public class CircleAlgorithm extends AbstractAlgorithm {

    @Override
    public void calcSpeeds() {
        for (Item item : items) {
            item.setySpeed(item.getySpeed() - 1);
        }
    }

    @Override
    public void drawItems(Graphics g) {
        synchronized (items) {
            for (Item item : items) {
                g.drawOval(500 - item.getX() - item.getWidth() / 2, 500 - item.getY() - item.getHeight() / 2,
                        item.getWidth(), item.getHeight());
            }
        }
    }

    @Override
    public void addItem() {
        addItem(new Item(50, 50, 50, 50, -5, -5));
    }

}