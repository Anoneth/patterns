package lab3;

import java.awt.Graphics;

public class StarAlgorithm extends AbstractAlgorithm {

    @Override
    public void calcSpeeds() {
        synchronized (items) {
            for (Item item : items) {
                item.setySpeed(item.getySpeed() + 0);
            }
        }
    }

    @Override
    public void drawItems(Graphics g) {
        synchronized (items) {
            for (Item item : items) {
                int[] x = new int[] { item.getWidth() / 2, (int) (item.getWidth() * 0.38), 0,
                        (int) (item.getWidth() * 0.3), (int) (item.getWidth() * 0.2), item.getWidth() / 2,
                        (int) (item.getWidth() * 0.8), (int) (item.getWidth() * 0.7), item.getWidth(),
                        (int) (item.getWidth() * 0.63) };
                for (int i = 0; i < x.length; i++) {
                    x[i] = item.getX() + x[i] - item.getWidth() / 2;
                }
                int[] y = new int[] { 0, (int) (item.getHeight() * 0.38), (int) (item.getHeight() * 0.38),
                        (int) (item.getHeight() * 0.62), item.getHeight(), (int) (item.getHeight() * 0.78),
                        item.getHeight(), (int) (item.getHeight() * 0.62), (int) (item.getHeight() * 0.39),
                        (int) (item.getHeight() * 0.39) };
                for (int i = 0; i < y.length; i++) {
                    y[i] = item.getY() + y[i] - item.getHeight() / 2;
                }
                g.drawPolygon(x, y, x.length);
            }
        }
    }

    @Override
    public void addItem() {
        addItem(new Item(450, 450, 50, 50, 5, 5));
    }

}