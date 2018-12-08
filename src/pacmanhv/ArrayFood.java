package pacmanhv;

import java.util.ArrayList;

public class ArrayFood {

    int score = 0;

    ArrayList<Food> allFood;

    public ArrayFood() {
        allFood = new ArrayList<>();
        addNew(2, 2, 2);
        addNew(7, 2, 1);
        addNew(11, 2, 1);
        addNew(15, 2, 1);
        addNew(19, 2, 1);
        addNew(24, 2, 2);
        addNew(30, 2, 1);
        addNew(34, 2, 1);
        addNew(38, 2, 1);
        addNew(42, 2, 1);
        addNew(46, 2, 2);
        addNew(3, 8, 1);
        addNew(7, 8, 1);
        addNew(11, 8, 1);
        addNew(15, 8, 1);
        addNew(19, 8, 1);
        addNew(24, 8, 2);
        addNew(30, 8, 1);
        addNew(34, 8, 1);
        addNew(38, 8, 1);
        addNew(42, 8, 1);
        addNew(46, 8, 1);
        addNew(24, 19, 2);
        addNew(1, 23, 2);
        addNew(2, 44, 2);
        addNew(24, 44, 2);
        addNew(46, 23, 2);
        addNew(46, 44, 2);
        addNew(2, 40, 1);
        addNew(6, 40, 1);
        addNew(6, 44, 1);
        addNew(10, 12, 1);
        addNew(10, 16, 1);
        addNew(10, 20, 1);
        addNew(10, 24, 1);
        addNew(10, 28, 1);
        addNew(10, 32, 1);
        addNew(10, 36, 1);
        addNew(10, 40, 1);
        addNew(10, 44, 1);
        addNew(13, 24, 1);
        addNew(14, 40, 1);
        addNew(14, 44, 1);
        addNew(16, 16, 1);
        addNew(16, 20, 1);
        addNew(16, 24, 1);
        addNew(16, 28, 1);
        addNew(16, 32, 1);
        addNew(16, 36, 1);
        addNew(18, 40, 1);
        addNew(18, 44, 1);
        addNew(19, 13, 1);
        addNew(20, 19, 1);
        addNew(20, 32, 1);
        addNew(22, 36, 1);
        addNew(22, 40, 1);
        addNew(22, 44, 1);
        addNew(23, 32, 1);
        addNew(26, 32, 1);
        addNew(29, 32, 1);
        addNew(29, 19, 1);
        addNew(27, 36, 1);
        addNew(27, 40, 1);
        addNew(27, 44, 1);
        addNew(31, 40, 1);
        addNew(31, 44, 1);
        addNew(33, 16, 1);
        addNew(33, 20, 1);
        addNew(33, 24, 1);
        addNew(33, 28, 1);
        addNew(33, 32, 1);
        addNew(33, 36, 1);
        addNew(35, 40, 1);
        addNew(35, 44, 1);
        addNew(39, 12, 1);
        addNew(39, 16, 1);
        addNew(39, 20, 1);
        addNew(39, 24, 1);
        addNew(39, 28, 1);
        addNew(39, 32, 1);
        addNew(39, 36, 1);
        addNew(39, 40, 1);
        addNew(39, 44, 1);
        addNew(43, 40, 1);
        addNew(43, 44, 1);
        addNew(47, 40, 1);
//cot truoc hang sau, kich co
allFood.clear();
        addNew(1, 1, 1);
    }

    public final void addNew(int i, int j, int score) {
        allFood.add(new Food(
                i,
                j,
                score));
    }

    int check(int x, int y) {
        if (allFood.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < allFood.size(); i++) {
//            Food f = allFood.get(i);
//            if ((f.x*10 < x + 15 && f.x*10 > x - 15)
//                    && (f.y*10 < y + 15 && f.y*10 > y - 15)) {
//                this.allFood.remove(i);
//                return f.score;
//            };

            int fs = allFood.get(i).score;
            int fx = allFood.get(i).x + fs*5;
            int fy = allFood.get(i).y + fs*5;
            if ((x - fx) * (x - fx) + (y - fy) * (y - fy) <= (15 + fs*5) * (15 + fs*5)) {
                allFood.remove(i);
                System.out.println("fx: " + fx);
                System.out.println("fy: " + fy);
                System.out.println("fs: " + fs);
                System.out.println("x: " + x);
                System.out.println("y: " + y);
                return fs;
            }
        }
        return 0;
    }
}

class Food {

    int x;
    int y;
    int score;

    public Food(int x, int y, int score) {
        this.x = 10 * x;
        this.y = 10 * y;
        this.score = score;
    }

}
