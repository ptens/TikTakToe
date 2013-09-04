import pack.Field;
import pack.Interface;
import pack.Players;

import java.io.*;


public class Main {

          public static  void main(String[] args)  throws IOException {
              Field field = new Field();
              Players player1 = new Players('X');
              Players player2 = new Players('O');

              Interface.getVerInfo();
              Interface.mess("\nПервый игрок, Вы будете играть крестиками, представьтесь пожалуйста");
              player1.setName();
              Interface.mess("Второй игрок, Вы будете играть ноликами, представьтесь пожалуйста");
              player2.setName();

              while (true) {
                 Interface.mess("Кто начнет партию?");
                 Interface.mess("1 - " + player1.getName() + "\n2 - " + player2.getName());
                 Players.setHwoFirst(Interface.getIntInRange(2, "Введите 1 или 2"));
                 field.resetField();

                 while (field.getAmountEmpty() > 0) {
                     field.showField();
                     if (Players.getHwoFirst() == 1) {
                        Interface.mess("\r" + player1.getName() + " (крестики), ходите!");
                        player1.doStep(field);
                        Players.changeTurn();
                     }
                    else {
                        Interface.mess("\r" + player2.getName() + " (нолики), ходите!");
                        player2.doStep(field);
                        Players.changeTurn();
                    }
                    if (field.checkLastCell()) break;

                 }
                 field.showField();
                 if (field.getAmountEmpty() == 0 && !field.checkLastCell()) Interface.mess("\rПартия сыграна в ничью!");
                 else {
                     if (Players.getHwoFirst() == 1) {
                         Interface.mess("\rПартию выиграл(а) " + player2.getName()+ " (нолики).");
                         player2.scorePlusOne();
                     }
                     else { Interface.mess("\rПартию выиграл(а) " + player1.getName()+ " (крестики).");
                         player1.scorePlusOne();
                     }
                 }
                 Interface.mess("Счет: " + player1.getScore() + ":" + player2.getScore() + Players.hwoLeader(player1, player2));
                 Interface.mess("Еще одну партию? \n1 - да\n2 - нет");
                 if (Interface.getIntInRange(2, "Введите 1 или 2") == 2) break;
              }

          }

}
