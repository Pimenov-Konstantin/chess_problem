public class chess {

    static int sizeBoard = 8; // размер доски
    static int[][] moves = new int[][]{{2, 1, -1, -2, -2, -1, 1, 2}, {1, 2, 2, 1, -1, -2, -2, -1}};

    public static void main(String[] args) {

        int[][] board = new int[sizeBoard][sizeBoard];

        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                board[i][j] = 0;
            }
        }

        board[0][0] = 1; // стартуем с точки (0,0)
        if (!findBoard(0, 0, 2, board)) {
            System.out.println("решения нет");
        } else {
            printArr(board);
        }
    }

    public static boolean validPos(int curRow, int curCol, int[][] curBoard) {

        if ((curRow >= 0) && (curCol >= 0) && (curRow < sizeBoard) && (curCol < sizeBoard)
                && (curBoard[curRow][curCol] == 0)) {
            return true;//ход в пределах доски и ячейка не занята
        }
        return false;
    }

    public static boolean findBoard(int curRow, int curCol, int count, int[][] curBoard) {
        if (count == sizeBoard * sizeBoard + 1) {
            return true; // вернуть результат
        }
        for (int currMove = 0; currMove < 8; currMove++) {
            int nextRow = curRow + moves[0][currMove];
            int nextCol = curCol + moves[1][currMove];

            if (validPos(nextRow, nextCol, curBoard)) {
                curBoard[nextRow][nextCol] = count;
                if (findBoard(nextRow, nextCol, count + 1, curBoard)) {
                    return true;
                } else {
                    curBoard[nextRow][nextCol] = 0;
                }
            }
        }
        return false;
    }

    public static void printArr(int[][] arr) {
        System.out.println("print Board");
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

}