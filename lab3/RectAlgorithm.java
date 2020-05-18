package lab3;

import java.awt.Graphics;

public class RectAlgorithm extends AbstractAlgorithm {

    @Override
    public void calcSpeeds() {
        for (Item item : items) {
            item.setySpeed(item.getySpeed() - 2);
        }
    }

    @Override
    public void drawItems(Graphics g) {
        for (Item item : items) {
            g.drawRect(item.getX() - item.getWidth() / 2, item.getY() - item.getHeight() / 2, item.getWidth(), item.getHeight());
        }
    }

    @Override
    public void addItem() {
        addItem(new Item(475, 475, 40, 40, 2, 2));
    }
    
}