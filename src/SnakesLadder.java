import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class SnakesLadder {
    
    public int totalLevels;
    private Scanner sc;
    private int currentLevel;
    private int userRole; // 1 for admin | 2 for user
    
    private int[][] levelDS;
    private static final int NOTHING = 1;
    private static final int LADDER = 2;
    private static final int SNAKE = 3;
    
    /**
     * DATA STRUCTURE : 2D ARRAY
     * level : next-level flag
     * indexing from 1 to avoid confusion
     */
    

   // Constructor
    SnakesLadder()
    {
        this.sc = new Scanner(System.in);
        this.totalLevels = 100;
        this.currentLevel = 1;
        
        // +1 bcoz we will use indexing from 1 to avoid confusion
        this.levelDS = new int[this.totalLevels + 1][2];

        // level: 1 to 99 : 1->2 , 2->3 ,...., 99->100
        for (int i = 1; i <= this.totalLevels; i++)
        {
            // next-level init to itself only
            this.levelDS[i][0] = i;

            // flag : Nothing 1 | Ladder 3 | Snake 3
            this.levelDS[i][1] = NOTHING;
        }
        this.levelDS[this.totalLevels][0] = this.totalLevels; // 100->100 itself
    }

    public void displayBoard()
    {
        System.out.println("\n------------------------------BOARD------------------------------\n");
        System.out.println("------FLAG: --------NOTHING N------LADDER L--------SNAKE S-------\n");

        System.out.println("LEVEl\t:\tNEXT-LEVEL\t:\tFLAG");
        System.out.println("-------------------------------------------------------");

        for (int i = 1; i <= this.totalLevels; i++)
        {
            int myFlag = this.levelDS[i][1];
            char flag = 'N';

            if (myFlag == NOTHING) {
                flag = 'N';
            } else if (myFlag == LADDER) {
                flag = 'L';
            } else if (myFlag == SNAKE) {
                flag = 'S';
            }

            String result = i + "\t:\t" + this.levelDS[i][0] + "\t\t:\t" + flag;
            System.out.println(result);
        }

        System.out.println("------------------------------------------------------------------\n");
    }

    public void changeRole(int role)
    {
        this.userRole = role;
    }
    
    private void changeInLevelDS(int sourceLevel, char myFlag, int targetLevel)
    {
        if (sourceLevel < 0 && sourceLevel > 100) {
            System.out.println("\n----Check LEVEL----");
            return;
        }

        if ( !(myFlag == 's' || myFlag == 'l' || myFlag == 'n') ) {
            System.out.println("\n----Check FLAG----");
            return;
        }

        if (targetLevel < 0 && targetLevel > 100) {
            System.out.println("\n----Check JUMP LEVEL----");
            return;
        }

        int flag = NOTHING;
        if (myFlag == 'n') {
            flag = NOTHING;
        } else if (myFlag == 'l') {
            flag = LADDER;
        } else if (myFlag == 's') {
            flag = SNAKE;
        }

        this.levelDS[sourceLevel][0] = targetLevel;
        this.levelDS[sourceLevel][1] = flag;

    }

    private void AddinLevelDS()
    {
        System.out.println("----ADDING LEVEL----");

        System.out.print("\nLevel {int} [1-99] : ");
        int addLevel = sc.nextInt();

        System.out.print("Flag : Snake/Ladder/Nothing {s/l/n} : ");
        char whichOne = sc.next().charAt(0);

        System.out.print("Jump Level {int} : ");
        int jumpLevel = sc.nextInt();

        changeInLevelDS(addLevel, whichOne, jumpLevel);
        System.out.println("---- LEVEL ADDED----\n");
    }
    private void UpdateinLevelDS()
    {
        System.out.println("----UPDATING LEVEL----");

        System.out.print("\nLevel {int} [1-99] : ");
        int editLevel = sc.nextInt();

        System.out.print("Flag : Snake/Ladder/Nothing {s/l/n} :");
        char whichOne = sc.next().charAt(0);

        System.out.print("Jump Level {int} : ");
        int jumpLevel = sc.nextInt();

        changeInLevelDS(editLevel, whichOne, jumpLevel);
        System.out.println("---- LEVEL UPDATED----\n");
    }
    private void DeleteinLevelDS()
    {
        System.out.println("----DELETING LEVEL----");

        System.out.println("Which level entry to delete ? : ");
        int deleteLevel = sc.nextInt();
        char deleteFlag = 'n';

        changeInLevelDS(deleteLevel, deleteFlag, deleteLevel);
        System.out.println("---- LEVEL DELETED----\n");
    }


    public void EditSnakesLadder()
    {
        if (this.userRole != 1)
        {
            System.out.println("\nYou Are not Admin! Can't Edit Snakes & Ladder\n");
            return;
        }

        while (true)
        {
            System.out.println("------------ADMIN-EDITING------------");
            System.out.println("\n1\tADD");
            System.out.println("2\tUPDATE");
            System.out.println("3\tDELETE");
            System.out.println("-1\tEXIT");

            System.out.print("\nChoice: ");

            int option = sc.nextInt();

            switch (option)
            {
                case 1:
                    this.AddinLevelDS();
                    break;

                case 2:
                    this.UpdateinLevelDS();
                    break;

                case 3:
                    this.DeleteinLevelDS();
                    break;

                case -1: return; // return from method

                default:
                    System.out.println("\nWRONG INPUT\n");
                    break;
            }
        }
    }

    private void changeLevel(int diceValue)
    {
        // target value may be less or more / snake or ladder

        System.out.println("\nDice Value: " + diceValue);

        int targetLevel = this.currentLevel + diceValue;
        if (targetLevel > this.totalLevels) // > 100 then not allowed
        {
            System.out.println("\n  -- ROLL DICE AGAIN --");
            return;
        }

        /*********** To check for ladder or snake or nothing ********/
        int myFlag = this.levelDS[targetLevel][1];
        if (myFlag == LADDER) {
            System.out.println("\n  -- Yeahh!! Ladder --");
        } else if (myFlag == SNAKE) {
            System.out.println("\n  -- OHHH!!! SNAKE --");
        } else if (myFlag == NOTHING) {
            // do nothing
        }
        /*************************************************************/

        this.currentLevel = this.levelDS[targetLevel][0];

    }
    
    private void GamePlaying()
    {

        System.out.println("\n------------GAME-STARTED-----------");
        this.currentLevel = 1;
        Random rand = new Random();
        
        while (true)
        {
            if (this.currentLevel == 100)
            {
                System.out.println("\n  ------ YAY!!! ---- YOU WON --------\n");
                return;
            }

            System.out.print("\nROLL DICE? [level=" +this.currentLevel + "] [q to quit] : ");
            char isQuit = sc.next().charAt(0);

            if (isQuit == 'q')
            {
                System.out.println("\n------------GAME-QUIT------------\n");
                return;
            }

            // Syntax: int randomValue = rand.nextInt(max – min + 1) + min;
            // random num between 1 and 6
            int diceValue = rand.nextInt(6 - 1 + 1) + 1;
            this.changeLevel(diceValue);

        }

    }

    public void UserModule()
    {
        System.out.println("------------USER------------");
        // User can only play
        this.GamePlaying();

        // Although we can have control like while and swicth but
        // functionality is limited to play
    }
    
    public void AdminModule()
    {
        while (true)
        {
            System.out.println("------------ADMIN------------");
            System.out.println("\n1\tPLAY");
            System.out.println("2 \tEDIT");
            System.out.println("-1\tEXIT");

            System.out.print("\nChoice: ");
            int option = sc.nextInt();

            switch (option)
            {
                case 1: // PLAY
                    this.GamePlaying();
                    break;
                
                case 2: // CRUD
                    this.EditSnakesLadder();
                    break;
                
                case -1: return; // return from Admin
                    
                default:
                    System.out.println("\nWRONG INPUT\n");
                    break;
            }
        }
    }

};