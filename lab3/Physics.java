package lab3;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class Physics extends Frame {
    private BufferedImage buffer;
    private Graphics bGraphics;
    private List<AbstractAlgorithm> algorithms;
    private Canvas canvas;
    public Physics() {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        buffer = gc.createCompatibleImage(502, 502);
        bGraphics = buffer.getGraphics();
        setSize(690, 590);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        algorithms = new ArrayList<>();
        algorithms.add(new CircleAlgorithm());
        algorithms.add(new RectAlgorithm());
        algorithms.add(new StarAlgorithm());

        setLayout(null);

        Button addCircle = new Button();
        addCircle.setBounds(520, 50, 150, 30);
        addCircle.setLabel("Добавить круг");
        addCircle.setVisible(true);
        addCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithms.get(0).addItem();
            }
        });
        add(addCircle);
        Button addRect = new Button();
        addRect.setBounds(520, 90, 150, 30);
        addRect.setLabel("Добавить квадрат");
        addRect.setVisible(true);
        addRect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithms.get(1).addItem();
            }
        });
        add(addRect);
        Button addStar = new Button();
        addStar.setBounds(520, 130, 150, 30);
        addStar.setLabel("Добавить звезду");
        addStar.setVisible(true);
        addStar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithms.get(2).addItem();
            }
        });
        add(addStar);

        canvas = new Canvas();
        canvas.setBounds(10, 35, 501, 501);
        canvas.setVisible(true);
        add(canvas);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    private void update() {
        bGraphics.setColor(Color.white);
        bGraphics.fillRect(0, 0, 500, 500);
        bGraphics.setColor(Color.black);
        bGraphics.drawRect(0, 0, 500, 500);
        for (AbstractAlgorithm aa : algorithms) {
            aa.tick(bGraphics);
        }
        Graphics2D g2d = (Graphics2D)canvas.getGraphics();
        g2d.drawImage(buffer, 0, 0, this);
    }
}