//Лабораторна робота 5
//Завдання 1

import javax.swing.*;
import java.awt.*;

public class Lab6_1 extends JFrame {

    private int centerX, centerY;
    private int lineLength = 200;
    private double angle = 0;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    private int colorIndex = 0;

    private Lab6_1() {
        setTitle("Rotating Line");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        new Thread(this::animate).start();
    }

    private void animate() {
        while (true) {
            angle += 0.05;
            if (angle >= 2 * Math.PI) {
                angle = 0;
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x1 = centerX;
        int y1 = centerY;
        int x2 = centerX + (int) (lineLength * Math.cos(angle));
        int y2 = centerY + (int) (lineLength * Math.sin(angle));

        g.setColor(colors[colorIndex]);
        g.drawLine(x1, y1, x2, y2);

        colorIndex = (colorIndex + 1) % colors.length;
    }

    public static void main(String[] args) {
        new Lab6_1();
    }
}