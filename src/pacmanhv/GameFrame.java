package pacmanhv;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GameFrame extends JFrame implements Runnable, KeyListener {

    GamePanel gamePanel;
    Thread th;

    public GameFrame() throws Exception {
        setSize(507, 508);
        setResizable(false);
        gamePanel = new GamePanel();
        addKeyListener(this);
        add(gamePanel);
        th = new Thread(this);
        th.start();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gamePanel.stopMusic();
                e.getWindow().dispose();
            }
        });

    }

    //start game
    @Override
    public void run() {

        while (!false) {
            try {

                if (gamePanel.status == gamePanel.ALIVE) {
                    gamePanel.update();
                    gamePanel.repaint(); // vẽ lại frame 
                    Thread.sleep(7);// 10 là thời gian delay trước khi vẽ lại
                }

            } catch (Exception ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // bộ nhận diện action với phím 
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(gamePanel.status==gamePanel.DEAD){
                try {
                    this.setVisible(false);
                    new GameFrame().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            gamePanel.getAKey(e.getKeyCode());
        } catch (Exception ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}

// màn hình chính của game 
class GamePanel extends JPanel {

    // các hằng số định nghĩa các trạng thái, phương hướng cho dễ nhìn, dễ đoc 
    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;
    final int ALIVE = 5;
    final int DEAD = 6;
    final int WIN = 7;

    Map1 map;
    int status;
    ArrayFood arrayFood;
    ArrayEnemy arrayEnemy;
    Image pacMan;
    int x = 250;
    int y = 250;
    int direct = DOWN;
    int score = 0;
    SoundControl sc;

    //khởi tạo 
    public GamePanel() throws Exception {
        sc = new SoundControl();
        sc.play(SoundControl.play);
        status = ALIVE;
        map = new Map1();
        this.setSize(500, 500);
        arrayFood = new ArrayFood();
        arrayEnemy = new ArrayEnemy();
        pacMan = new ImageIcon(this.getClass().getResource("down.png")).getImage();
        System.out.println("new panel");

    }

    public void stopMusic() {
        sc.playerWAV.stop();
    }

    // hàm vẽ lên frame, FPS = 100 
    @Override
    public void paint(Graphics g) {

        if (status == WIN) {
            pacMan = new ImageIcon(this.getClass().getResource("win.jpg")).getImage();
            g.drawImage(pacMan, 0, 0, 500, 500, this);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.red);
            g.drawString("Score " + score, 170, 350);
            return;
        } else if (status == DEAD) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("YOU LOSE",75, 100);
            g.setColor(Color.cyan);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Space to restart", 180, 200);
        } else {
            g.setColor(Color.black); // color of background
            g.fillRect(0, 0, this.getSize().width, this.getSize().height);
            g.setColor(Color.blue);
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (map.block[i][j]) {
                        g.fill3DRect(i * 10, j * 10, 10, 10, true);
                    }
                }
            }

            //draw foods
            g.setColor(Color.yellow);
            for (Food food : arrayFood.allFood) {
                g.setColor(Color.yellow);
                g.fillOval(food.x, food.y, 10 * food.score, 10 * food.score);

            }
            g.setColor(Color.green);
            for (int i = 0; i < arrayEnemy.allEnemy.size(); i++) {
                g.drawImage(arrayEnemy.allEnemy.get(i).ic, arrayEnemy.allEnemy.get(i).x, arrayEnemy.allEnemy.get(i).y, 30, 30, this);
            }
            drawPacMan(g, x, y);
            g.setColor(Color.red);
            g.drawString("Score: \n" + score, 10, 160);
        }
    }

    // di chuyen, cap nhap vi tri của pacman 
    public void update() throws Exception {
        if (arrayFood.allFood.size() == 0) {
            status = WIN;
            sc.playerWAV.stop();
            sc.play(SoundControl.win);

        } else if (arrayEnemy.check(x, y)) {
            status = DEAD;
            sc.playerWAV.stop();
            sc.play(SoundControl.lose);

        } else {
            arrayEnemy.update();
            int s = arrayFood.check(x, y); // kiểm tra xem vị trí của pacman có trùng với food nào không 
            if (s == -1) {
                status = WIN;
            } else {
                score += s;
            }
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

    // lọc những trường hợp không di chuyển được
    void getAKey(int keyCode) throws Exception {

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (map.check(x, (y + 500 - 1) % 500)) { // check(x,y) check xem vị trí có thỏa mãn để di chuyển tới không 
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

    private void drawPacMan(Graphics g, int x, int y) {
        switch (direct) {
            case DOWN:
                pacMan = new ImageIcon(this.getClass().getResource("down.png")).getImage();
                break;
            case UP:
                pacMan = new ImageIcon(this.getClass().getResource("up.png")).getImage();
                break;
            case LEFT:
                pacMan = new ImageIcon(this.getClass().getResource("left.png")).getImage();
                break;
            case RIGHT:
                pacMan = new ImageIcon(this.getClass().getResource("right.png")).getImage();
                break;
            default:
                pacMan = new ImageIcon(this.getClass().getResource("right.png")).getImage();
                break;
        }

        g.drawImage(pacMan, x - 15, y - 15, 30, 30, this);

    }
}
