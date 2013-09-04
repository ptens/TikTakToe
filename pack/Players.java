package pack;
import java.io.*;


public class Players {
    private String name = "N/A";
    private int score = 0;
    private char selfChar;
    private static int hwoFirst;
    private static char defChar = 'X';


    public Players() {
        this(defChar);
    }

    public Players(char setSelfChar) {
        selfChar = setSelfChar;
    }

    public void doStep(Field field) {
        while (true) {
            int[] indx = toIndex(Interface.getIntInRange(field.getAmountOfCell(), "Введите один из доступных номеров ячеек"), field);
            if (field.setValueCell(indx[0], indx[1], selfChar)) break;
            Interface.mess("Эта ячейка уже заполнена");
        }
    }


    public void setName() throws IOException {
        name = Interface.getString();
    }

    public String getName() {
        return name;
    }

    public static void setHwoFirst(int i) {
        hwoFirst = i;
    }

    public static int getHwoFirst() {
        return hwoFirst;
    }

    public static void changeTurn() {
        if (hwoFirst == 1) hwoFirst = 2;
        else hwoFirst = 1;
    }

    public void scorePlusOne() {
        ++score;
    }

    public int getScore() {
        return score;
    }

    public int[] toIndex(int num, Field field) {
        int[] numToIndex = new int[2];
        if (num % field.getSIZE_FIELD() != 0) {
            numToIndex[0] = num % field.getSIZE_FIELD() -1;
            numToIndex[1] = num / field.getSIZE_FIELD();
            return numToIndex;
        }
        else {
            numToIndex[0] = field.getSIZE_FIELD() - 1;
            numToIndex[1] = num / field.getSIZE_FIELD() - 1;
            return numToIndex;
        }
    }
    public static String hwoLeader(Players p1, Players p2) {
        if (p1.getScore() > p2.getScore()) return " в пользу крестиков!";
        if (p2.getScore() > p1.getScore()) return " в пользу ноликов!";
        return " - ничья!";
    }


}