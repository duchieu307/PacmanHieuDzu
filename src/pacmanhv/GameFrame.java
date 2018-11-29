/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanhv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ducthien
 */
public class GameFrame extends JFrame implements Runnable, KeyListener {

    GamePanel gamePanel;
    Thread th;

    public GameFrame() {
        setSize(500, 500);
        gamePanel = new GamePanel();
        addKeyListener(this);
        add(gamePanel);
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        while (!false) {
            try {
                gamePanel.update();
                gamePanel.repaint();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.getAKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}

class GamePanel extends JPanel {

    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;
    final int ALIVE = 5;
    final int DEAD = 6;

    Map1 map;
    int status;
    ArrayFood arrayFood;
    Image pacMan;
    int x = 250;
    int y = 250;
    int direct = DOWN;

    public GamePanel() {
        status = ALIVE;
        map = new Map1();
        this.setSize(500, 500);
        arrayFood = new ArrayFood();
        pacMan = new ImageIcon(this.getClass().getResource("nhung.png")).getImage();
    }

    //hien thi len panel 
    @Override
    public void paint(Graphics g) {
        
        System.out.println(this.getSize().height+" ---- "+this.getSize().width);
        g.setColor(Color.blue);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (map.block[i][j]) {
                    g.fill3DRect(i * 10, j * 10, 10, 10, true);
                }
            }
        }
        //draw pacman
        g.drawImage(pacMan, x - 15, y - 15, 30, 30, this);

        //draw foods
        g.setColor(Color.green);
        for (Food food : arrayFood.allFood) {
            g.fillOval(food.x*10, food.y*10, 10*food.score, 10*food.score);
        }
    }

    // di chuyen, cap nhap vi tri 
    public void update() {
        arrayFood.check(x, y);
        switch (direct) {
            case DOWN:
                down();
                break;
            case LEFT:
                left();
                break;
            case UP:
                up();
                break;
            case RIGHT:
                right();
                break;
            default:
                System.out.println("error");
        }
    }

    void left() {
        if (map.check((x + 500 - 1) % 500, y)) {
            x = (x + 500 - 1) % 500;
        }
    }

    void right() {
        if (map.check((x + 500 + 1) % 500, y)) {
            x = (x + 500 + 1) % 500;
        }
    }

    void down() {
        if (map.check(x, (y + 500 + 1) % 500)) {
            y = (y + 500 + 1) % 500;
        }
    }

    void up() {
        if (map.check(x, (y + 500 - 1) % 500)) {
            y = (y + 500 - 1) % 500;
        }
    }

    void getAKey(int keyCode) {

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (map.check(x, (y + 500 - 1) % 500)) {
                    direct = UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (map.check(x, (y + 500 + 1) % 500)) {
                    direct = DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (map.check((x + 500 - 1) % 500, y)) {
                    direct = LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (map.check((x + 500 + 1) % 500, y)) {
                    direct = RIGHT;
                }
                break;
        }

    }
}
