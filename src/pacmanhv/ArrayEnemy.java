/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanhv;

import java.util.ArrayList;

/**
 *
 * @author ducthien
 */
public class ArrayEnemy {

    int score = 0;

    ArrayList<Enemy> allEnemy;
    Map1 map;

    ArrayEnemy() {
        map = new Map1();
        allEnemy = new ArrayList<>();
        allEnemy.add(new Enemy(100, 20, 1));
        allEnemy.add(new Enemy(400, 20, 1));
        allEnemy.add(new Enemy(20, 400, 1));
        allEnemy.add(new Enemy(450, 400, 1));

    }

    void update() {
        for (int i = 0; i < allEnemy.size(); i++) {

            boolean moved = false;

            while (!moved) {

                switch (allEnemy.get(i).direct) {
                    case 0:
                        if (allEnemy.get(i).moveDown()) {
                            allEnemy.get(i).y++;;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                    case 1:
                        if (allEnemy.get(i).moveUp()) {
                            allEnemy.get(i).y--;;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                    case 2:
                        if (allEnemy.get(i).moveLeft()) {
                            allEnemy.get(i).x--;;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                    case 3:
                        if (allEnemy.get(i).moveRight()) {
                            allEnemy.get(i).x++;;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                }
            }
        }
    }

    boolean check(int x, int y) {
        if (allEnemy.size() == 0) {
            return false;
        }
        // tinh toan x,y
        for (int i = 0; i < allEnemy.size(); i++) {
            int xx = allEnemy.get(i).x + 15;
            int yy = allEnemy.get(i).y + 15;

            if ((x - xx) * (x - xx) + (y - yy) * (y - yy) <= 900) {
                return true;
            }
        }

        //default
        return false;
    }

    class Enemy {

        public final int ENEMY = 1;
        public final int FOOD = 2;

        public Enemy(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }

        public int x;
        public int y;
        public int kind;
        public int direct = 1;

        boolean moveUp() {
            return map.check(x + 15, y - 1 + 15);

        }

        boolean moveDown() {
            return map.check(x + 15, y + 15 + 1);
        }

        boolean moveLeft() {
            return map.check(x + 15 - 1, y + 15);
        }

        boolean moveRight() {
            return map.check(x + 1 + 15, y + 15);
        }
    }

}
