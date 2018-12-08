package pacmanhv;

class Map1 {

    boolean[][] block;

    // block[cot][hang]
    public Map1() {
        block = new boolean[50][50];
        drawHorizontal(0, 0, 49);
        drawHorizontal(47, 0, 49);
        drawHorizontal(5, 6, 11);
        drawHorizontal(5, 17, 22);
        drawHorizontal(5, 27, 32);
        drawHorizontal(5, 38, 43);
        drawHorizontal(12, 0, 7);
        drawHorizontal(12, 42, 49);
        drawHorizontal(16, 19, 30);
        drawHorizontal(20, 0, 7);
        drawHorizontal(20, 42, 49);
        drawHorizontal(27, 0, 7);
        drawHorizontal(27, 42, 49);
        drawHorizontal(29, 19, 30);
        drawHorizontal(21, 19, 22);
        drawHorizontal(21, 27, 30);
        drawHorizontal(34, 19, 30);
        drawHorizontal(37, 0, 7);
        drawHorizontal(37, 42, 49);
        drawHorizontal(42, 6, 11);
        drawHorizontal(42, 17, 22);
        drawHorizontal(42, 27, 32);
        drawHorizontal(42, 38, 43);
        drawVertical(0, 0, 12);
        drawVertical(0, 37, 47);
        drawVertical(7, 12, 20);
        drawVertical(7, 27, 37);
        drawVertical(13, 12, 20);
        drawVertical(13, 27, 37);
        drawVertical(17, 5, 11);
        drawVertical(32, 5, 11);
        drawVertical(19, 21, 29);
        drawVertical(19, 34, 37);
        drawVertical(24, 12, 16);
        drawVertical(25, 12, 16);
        drawVertical(30, 21, 29);
        drawVertical(30, 34, 37);
        drawVertical(36, 12, 20);
        drawVertical(36, 27, 37);
        drawVertical(42, 12, 20);
        drawVertical(42, 27, 37);
        drawVertical(49, 0, 12);
        drawVertical(49, 37, 47);
    }

    public final void drawVertical(int x, int y1, int y2) {
        if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        for (int i = y1; i <= y2; i++) {
            block[x][i] = true;
        }
    }

    public final void drawHorizontal(int y, int x1, int x2) {
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        for (int i = x1; i <= x2; i++) {
            block[i][y] = true;
        }
    }

    boolean check(int x, int y) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (block[i][j]) {
                    int xx = i * 10;
                    int yy = j * 10;
                    if (x < xx + 25
                            && x > xx - 15
                            && y < yy + 25
                            && y > yy - 15) {
                        return false;
                    };
                }
            }
        }
        return true;
    }

}
