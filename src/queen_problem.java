import java.util.ArrayList;

public class queen_problem {

    public static void main(String[] args) {

        ArrayList<Integer> leftD = new ArrayList<>(); // номера занятых левых диаг (1 - (2*8+1))
        ArrayList<Integer> rightD = new ArrayList<>(); // номера занятых правых диаг ((2*8+1) - 1)
        ArrayList<ArrayList<Integer>> blockList = new ArrayList<>(2); // итого
        blockList.add(leftD);
        blockList.add(rightD);

        ArrayList<Integer> curRes = new ArrayList<Integer>();
        combine(0, 0, blockList, curRes);
    }

    public static boolean validPos(int numRow, int numCol, ArrayList<ArrayList<Integer>> check) {
        // проверяем что диагонали не заняты
        if ((check.get(0).indexOf(numRow + numCol) == -1) && (check.get(1).indexOf(numRow - numCol + 8) == -1)) {
            return true;
        }
        return false;
    }

    public static ArrayList<Integer> combine(int curRow, int curCol, ArrayList<ArrayList<Integer>> blockList, ArrayList<Integer> curRes) {
        if (curRes.size() == 8) {
            printList(curRes);
            return curRes; // вернуть результат
        }
        if (curRow == 8) // вышли за границы поля и не нашли ячейку - нужно откатить на 1 уровень
        {// мы не можем поставить в этой колонке ферзя - то вариант неудачный.
            int temp = curRes.remove(curRes.size() - 1);
            blockList.get(0).remove(curRes.size());
            blockList.get(1).remove(curRes.size());
            combine(temp + 1, curCol - 1, blockList, curRes);
            return curRes;
        }

        if (validPos(curRow, curCol, blockList) && (curRes.indexOf(curRow) == -1)) { // ячейка подходит
            curRes.add(curRow);
            blockList.get(0).add(curRow + curCol);
            blockList.get(1).add(curRow - curCol + 8);
            combine(0, curCol + 1, blockList, curRes);
        }
        if (curRes.size() < 8) {
            combine(curRow + 1, curRes.size(), blockList, curRes);
        }
        return curRes;
    }

    public static void printList(ArrayList<Integer> arrList) {
        int sizeBoard = arrList.size();
        System.out.println("Итоговая доска:");
        for (int item : arrList) {
            for (int currCell = 0; currCell < sizeBoard; currCell++) {
                if (currCell == item) System.out.print(("Q") + "\t");
                else System.out.print((".") + "\t");
            }
            System.out.println();
        }
    }
}

