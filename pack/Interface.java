package pack;
import java.util.Scanner;
import java.io.*;


public class Interface {
    private static String st;
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static int getIntInRange(int range, String mess) {
        int input;
        while (true) {
            input = getIntFromUser(mess);
            if (input > 0 && input <= range) break;
        }
        return input;

    }
     public  void x(int i) {
         i = 8;
         System.out.println(i);
     }

    private static int getIntFromUser(String mess) {
        while (true) {
            System.out.println(mess);
            if (sc.hasNextInt()) {
                break;
            }
            else st = sc.next();
        }
        return sc.nextInt();

    }

    public static void mess(String message) {
        System.out.println(message);
    }

    public static String getString() throws IOException{
        return br.readLine();
    }

    public static void getVerInfo() {
        System.out.println("\n#############################################################");
        System.out.println("# Крестики-нолики v. 0.1                                    #");
        System.out.println("# В данной версии досупен только режим игры на двоих.       #");
        System.out.println("# Игра против компьютера будет доступна в следующей версии. #");
        System.out.println("#############################################################");
    }
}
