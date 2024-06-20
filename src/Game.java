import java.io.*;
import java.util.Scanner;
// Role: admin, player

public class Game {
    public static void main(String args[]) {

        boolean isGameOn = true;
        Scanner sc = new Scanner(System.in);
        SnakesLadder ob = new SnakesLadder();

        while (isGameOn)
        {
            System.out.println("\n----------------SNAKES & LADDER----------------\n");
            System.out.println("\n1\tADMIN");
            System.out.println("2\tUSER");
            System.out.println("3\tDISPLAY BOARD");
            System.out.println("-1\tEXIT");
            System.out.print("\nChoice: ");

            int option = sc.nextInt();
            switch (option)
            {
                case 1:
                    ob.changeRole(1);
                    ob.AdminModule();
                    break;

                case 2:
                    ob.changeRole(2);
                    ob.UserModule();
                    break;

                case 3:
                    ob.displayBoard();
                    break;

                case -1:
                    System.out.println("\n----------------BYE----------------\n");
                    isGameOn = false;
                    // System.exit(0);
                    break;

                default:
                    System.out.println("\nWRONG INPUT\n");
                    break;
            }
        }

        // closing scanner object
        sc.close();
       

    }
}