package lab4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class View extends JFrame implements MyControlListener {
    private Model model;
    private JPanel panel;
    private JTable table;

    public View() {
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBounds(20, 20, 540, 540);
        
        model = new Model();
        model.addListener(this);
        
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        model.addTableModelListener(new TableModelListener() {
            
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(e.getType());
            }
        });
        
        scrollPane.setBounds(570, 40, 80, 200);
        scrollPane.setEnabled(true);

        JButton addButton = new JButton();
        addButton.setText("+");
        addButton.setBounds(650, 40, 40, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow();
            }
        });
        JButton removeButton = new JButton();
        removeButton.setText("-");
        removeButton.setBounds(650, 80, 40, 40);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeRow();
            }
        });
        
        add(addButton);
        add(removeButton);
        add(panel);
        add(scrollPane);

    }

    private void drawGraphic() {
        Graphics g = panel.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 540, 540);
        g.setColor(Color.black);
        double[] x = model.getX();
        double[] y = model.getY();
        double dx, dy = 0;
        double zoom = 0;
        if (x.length == 0) {
            drawAxis(g, 250, 250);
        } else if (x.length == 1) {
            zoom = 500 / Math.max(Math.abs(x[0]), Math.abs(y[0]));
            int axisX = x[0] > 0 ? 0 : 500;
            int axisY = y[0] > 0 ? 500 : 0;
            drawAxis(g, axisX, axisY);
            double pointX = x[0] > 0 ? x[0] * zoom : 500 - x[0] * zoom;
            double pointY = y[0] > 0 ? y[0] * zoom : 500 - y[0] * zoom;
            drawPoint(g, pointX, pointY);
        } else {
            double x0 = findMin(x);
            double y0 = findMin(y);
            double xL = findMax(x);
            double yL = findMax(y);

            if (x0 > 0 && xL < 0 || x0 < 0 && xL > 0) {
                dx = xL - x0;
            } else {
                dx = x0 > 0 ? xL : x0;
            }
            if (y0 > 0 && yL < 0 || y0 < 0 && yL > 0) {
                dy = y0 - yL;
            } else {
                dy = y0 > 0 ? yL : y0;
            }
            zoom = 500 / Math.max(Math.abs(dx), Math.abs(dy));
            int x1 = (int) (zoom * (x0 < 0 ? x[0] - x0 : x[0]));
            int y1 = 500 - (int) (zoom * (y0 < 0 ? y[0] - y0 : y[0]));
            for (int i = 0; i < x.length; i++) {
                double pointX = zoom * (x0 < 0 ? x[i] - x0 : x[i]);
                double pointY = 500 - zoom * (y0 < 0 ? y[i] - y0 : y[i]);
                g.drawLine(x1 + 20, y1 + 20, (int) pointX + 20, (int) pointY + 20);
                x1 = (int) pointX;
                y1 = (int) pointY;
            }
            drawAxis(g, (int) (zoom * (x0 < 0 ? 0 - x0 : 0)), 500 - (int) (zoom * (y0 < 0 ? -y0 : 0)));
        }
    }

    private double findMax(double[] a) {
        double max = Double.NEGATIVE_INFINITY;
        for (double d : a) {
            if (max < d)
                max = d;
        }
        return max;
    }

    private double findMin(double[] a) {
        double min = Double.POSITIVE_INFINITY;
        for (double d : a) {
            if (min > d)
                min = d;
        }
        return min;
    }

    private void drawPoint(Graphics g, double x, double y) {
        g.fillOval((int) x + 17, 517 - (int) y, 6, 6);
    }

    private void drawAxis(Graphics g, int x, int y) {
        g.drawLine(20, 20 + y, 520, 20 + y);
        g.drawLine(20 + x, 20, 20 + x, 520);
    }

    @Override
    public void onDataChanged() {
        revalidate();
        drawGraphic();
        table.updateUI();
    }

}