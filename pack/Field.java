package pack;

public class Field {
    private static final char DEFAULT_CELL_VALUE = ' ';
    private final   int SIZE_FIELD;
    private final  char[][] field;
    private static final int DEF_SIZE_FIELD = 3;
    private int lastSetCell;
    private int[] lastCell = new int[2];
    private int amountEmpty;


    public Field() {
        this(DEF_SIZE_FIELD);
    }
    public Field(int newFieldSize) {
        SIZE_FIELD = newFieldSize;
        field = new char[SIZE_FIELD][SIZE_FIELD];
        this.eraseField();
        amountEmpty = SIZE_FIELD*SIZE_FIELD;
    }



    private void eraseField() {
        for (int i = 0; i < SIZE_FIELD; i++) {
            eraseLine(i);
        }
    }
    private void eraseLine(int lineNumber) {
        for (int i = 0; i < SIZE_FIELD; i++) {
            field[i][lineNumber] = DEFAULT_CELL_VALUE;
        }
    }


    public void showField() {
        System.out.print("Игровое поле\tИндексы ячеек\n ");
        for (int i = 0; i < SIZE_FIELD; i++) {
            showLine(i);
            System.out.print("\t  ");
            showLineIndexes(i);
            System.out.print("\n ");
        }

    }

    private void showLine(int lineNumber) {
        for (int i = 0; i < SIZE_FIELD; i++) {
            System.out.print("[" + field[i][lineNumber] + "]");
        }
    }

    private void showLineIndexes(int lineNumber) {
        for (int i = 0; i < SIZE_FIELD; i++) {
            if (isClean(i, lineNumber)) System.out.print("[" + toNumber(i, lineNumber) + "]");
            else System.out.print("[-]");

        }
    }

    public boolean setValueCell(int x, int y, char symb) {
        if (isClean(x, y)) {
            field[x][y] = symb;
            lastCell[0] = x;
            lastCell[1] = y;
            --amountEmpty;
            return true;
        }
        else return false;
    }

    public boolean isClean(int x, int y) {
        if(field[x][y] == DEFAULT_CELL_VALUE) return true;
        else return false;

    }

    private int toNumber(int x, int y) {
        return y * SIZE_FIELD + x + 1;
    }



    // Этот метод прибавляет к индексу масива число учитывая границы масива.
    // Возвращает значение индекса соответствующее масивую


   private int nextIndex(int i, int step) {
       int result = (i + step + 1) % SIZE_FIELD;
       if (result > 0) return result -1;
       if (result == 0) return SIZE_FIELD -1;
       return SIZE_FIELD + result - 1;
    }




    private boolean checkVert(int x, int y) {
        for (int i = 1; i < SIZE_FIELD; ++i){
            if (field[x][y] != field[x][nextIndex(y, i)]) return false;
        }
        return  true;
    }

    private boolean checkGorizont(int x, int y) {
        for (int i = 1; i < SIZE_FIELD; ++i){
            if (field[x][y] != field[nextIndex(x, i)][y]) return false;
        }
        return  true;
    }
    private boolean checkBackSlashDiag(int x, int y) {
        for (int i = 1; i < SIZE_FIELD; ++i){
            if (field[x][y] != field[nextIndex(x, i)][nextIndex(y, i)]) return false;
        }
        return  true;
    }

    private boolean checkSlashDiag(int x, int y) {
        for (int i = 1; i < SIZE_FIELD; ++i){
            if (field[x][y] != field[nextIndex(x, -i)][nextIndex(y, i)]) return false;
        }
        return  true;
    }

    private boolean checkDiagonals(int x, int y) {
        if (x == y && x + y == SIZE_FIELD -1) return checkSlashDiag(x, y) || checkBackSlashDiag(x, y);
        if (x == y) return checkBackSlashDiag(x, y);
        if (x + y == SIZE_FIELD - 1) return checkSlashDiag(x, y);
        return false;
    }

    private boolean check(int x, int y) {
        if (checkDiagonals(x, y) || checkGorizont(x, y) || checkVert(x, y)) return true;
        else return false;
    }

    public int getAmountOfCell() {
        return SIZE_FIELD * SIZE_FIELD;
    }

    public int getSIZE_FIELD() {
        return SIZE_FIELD;
    }

    public boolean checkLastCell() {
        return check(lastCell[0], lastCell[1]);
    }
    public int getAmountEmpty() {
        return amountEmpty;
    }

    public void resetField() {
        amountEmpty = SIZE_FIELD*SIZE_FIELD;
        eraseField();
    }


}
