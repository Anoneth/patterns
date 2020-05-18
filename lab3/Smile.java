package lab3;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.event.MouseInputAdapter;

public class Smile extends Frame {
    private boolean isLeftEyeOpen = true;
    private boolean isRightEyeOpen = true;
    private boolean isSmile = false;

    private static Color[] noseColors = new Color[] { Color.white, Color.red, Color.green, Color.cyan, Color.magenta };
    private Color noseColor = Color.yellow;

    private int eyeR = 50;
    private int pupilR = 25;
    private int pupilMaxD = 25;

    private int leftEyeX = 150;
    private int leftEyeY = 200;
    private int leftPupilXD = 0;
    private int leftPupilYD = 0;

    private int rightEyeX = 350;
    private int rightEyeY = 200;
    private int rightPupilXD = 0;
    private int rightPupilYD = 0;

    public Smile() {
        setSize(500, 500);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (((x - leftEyeX) * (x - leftEyeX) + (y - leftEyeY) * (y - leftEyeY)) < eyeR * eyeR) {
                    isLeftEyeOpen = !isLeftEyeOpen;
                } else if (((x - rightEyeX) * (x - rightEyeX) + (y - rightEyeY) * (y - rightEyeY)) < eyeR * eyeR) {
                    isRightEyeOpen = !isRightEyeOpen;
                } else if (x > 200 && x < 300 && y > 200 && y < 300 && x > (200 + ((250 - 200) / (300 - 200) * (y - 200)))
                        && x < (300 - ((250 - 200) / (300 - 200) * (300 - y)))) {
                    int t = (int) Math.round(Math.random() * (noseColors.length - 1));
                    noseColor = noseColors[t];
                } else if (x > 150 && x < 350 && y > 350 && y < 400) {
                    isSmile = !isSmile;
                }
                getGraphics().dispose();
                repaint();
            }
        });
        addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (Math.abs(x - leftEyeX) < pupilMaxD && Math.abs(y - leftEyeY) < pupilMaxD) {
                    leftPupilXD = x - leftEyeX;
                    leftPupilYD = y - leftEyeY;
                }
                if (Math.abs(x - rightEyeX) < pupilMaxD && Math.abs(y - rightEyeY) < pupilMaxD) {
                    rightPupilXD = x - rightEyeX;
                    rightPupilYD = y - rightEyeY;
                }
                else {
                    double rangeL = Math.sqrt(Math.pow(x - leftEyeX, 2) + Math.pow(y - leftEyeY, 2));
                    leftPupilXD = (int) (pupilMaxD * (1 - (double)Math.abs(x - leftEyeX) / 500) * (x - leftEyeX) / rangeL);
                    leftPupilYD = (int) (pupilMaxD * (1 - (double)Math.abs(y - leftEyeY) / 500) * (y - leftEyeY) / rangeL);
                    double rangeR = Math.sqrt(Math.pow(x - rightEyeX, 2) + Math.pow(y - rightEyeY, 2));
                    rightPupilXD = (int) (pupilMaxD * (1 - (double)Math.abs(x - rightEyeX) / 500) * (x - rightEyeX) / rangeR);
                    rightPupilYD = (int) (pupilMaxD * (1 - (double)Math.abs(y - rightEyeY) / 500) * (y - rightEyeY) / rangeR);
                }

                getGraphics().dispose();
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(8.0f));
        g2.drawOval(50, 50, 400, 400);
        g2.setColor(Color.YELLOW);
        g2.fillOval(50, 50, 400, 400);
        drawMouth(g2);
        drawNose(g2);
        drawEyes(g2);
    }

    private void drawNose(Graphics2D g) {
        g.setColor(Color.black);
        g.drawPolygon(new int[] { 250, 200, 300 }, new int[] { 200, 300, 300 }, 3);
        g.setColor(noseColor);
        g.fillPolygon(new int[] { 250, 200, 300 }, new int[] { 200, 300, 300 }, 3);
    }

    private void drawMouth(Graphics2D g) {
        g.setColor(Color.black);
        if (isSmile) {
            GeneralPath gp = new GeneralPath();
            gp.moveTo(150, 350);
            gp.curveTo(250, 400, 350, 350, 350, 350);
            g.draw(gp);
        } else {
            g.drawLine(150, 350, 350, 350);
        }
    }

    private void drawEyes(Graphics2D g) {
        g.setColor(Color.black);
        if (isLeftEyeOpen) {
            g.drawOval(leftEyeX - eyeR, leftEyeY - eyeR, eyeR * 2, eyeR * 2);
            g.setColor(Color.white);
            g.fillOval(leftEyeX - eyeR, leftEyeY - eyeR, eyeR * 2, eyeR * 2);
            g.setColor(Color.black);
            g.fillOval(leftEyeX - pupilR + leftPupilXD, leftEyeY - pupilR + leftPupilYD, pupilR * 2, pupilR * 2);
        } else {
            g.drawOval(leftEyeX - eyeR, leftEyeY - eyeR, eyeR * 2, eyeR * 2);
            g.setColor(Color.yellow);
            g.fillOval(leftEyeX - eyeR, leftEyeY - eyeR, eyeR * 2, eyeR * 2);
        }
        g.setColor(Color.black);
        if (isRightEyeOpen) {
            g.drawOval(rightEyeX - eyeR, rightEyeY - eyeR, eyeR * 2, eyeR * 2);
            g.setColor(Color.white);
            g.fillOval(rightEyeX - eyeR, rightEyeY - eyeR, eyeR * 2, eyeR * 2);
            g.setColor(Color.black);
            g.fillOval(rightEyeX - pupilR + rightPupilXD, rightEyeY - pupilR + rightPupilYD, pupilR * 2, pupilR * 2);
        } else {
            g.drawOval(rightEyeX - eyeR, rightEyeY - eyeR, eyeR * 2, eyeR * 2);
            g.setColor(Color.yellow);
            g.fillOval(rightEyeX - eyeR, rightEyeY - eyeR, eyeR * 2, eyeR * 2);
        }
    }
}