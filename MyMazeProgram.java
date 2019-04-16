import java.util.Scanner;
import java.util.Random;

public class MyMazeProgram {

    public static Scanner myScanner = new Scanner(System.in);
    public static Random RNG = new Random(Config.RANDOM_SEED);
    public static void main(String[] args) {

        System.out.println("Welcome to MyMazeProgram!");
        String menu = "1. Temperature Converter\n2. Simple Robot Maze\n3. Random Robot Maze with Obstacle\n4. Exit";
        System.out.println(menu);

        boolean done = false;
        while (!done) {
            System.out.print("Enter choice: ");
            String option = myScanner.next();
            myScanner.nextLine();
            if (option.equals("1")) {
                System.out.print("Enter Fahrenheit temperature: ");
                double f = myScanner.nextDouble();
                double c = (f - 32) * 5 / 9;
                System.out.println(f + "F=" + c + "C");
            } else if (option.equals("2")) {
                int size = 5;
                helper(size, -1, -1, size-1, false);
            } else if (option.equals("3")) {
                System.out.printf("How big is the maze? (%d - %d)\n", Config.MIN_VALUE, Config.MAX_VALUE);
                int size = myScanner.nextInt();
                int obstacleRow = RNG.nextInt(size-2) + 1;
                int obstacleColumn = RNG.nextInt(size);
                int exitColumn = RNG.nextInt(size);
                helper(size, obstacleRow, obstacleColumn, exitColumn, true);
            } else if (option.equals("4")) {
                System.out.println("Live Long And Prosper!");
                done = true;
                System.exit(0);
            } 
            else {
                System.out.println("Sorry, I don't understand that.");
            }
        }
    }
    public static void helper(int size, int obstacleRow, int obstacleColumn, int exitColumn, boolean countMove){
        int robotI = 0;
        int robotJ = 0;
        int exitI = size - 1;
        int exitJ = exitColumn;
        int count = 0;
        String subMenu = "1. up\n2. down\n3. left\n4. right";
        System.out.println("Help Robot (R) get to Exit (E)");
        while ((robotI != exitI) || (robotJ != exitJ)){
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    if (i == robotI && j == robotJ) {
                        System.out.print("R ");
                    }
                    else if (i == exitI && j == exitJ) {
                        System.out.print("E ");
                    }
                    else if (i == obstacleRow && j == obstacleColumn) {
                        System.out.print("X ");
                    }
                    else {
                        System.out.print("O ");
                    }
                }
                System.out.println();
            }
            System.out.println(subMenu);
            System.out.print("Move? ");
            String choice = myScanner.next();
            if (choice.equals("1")) {
                if ((robotI - 1 != obstacleRow || robotJ != obstacleColumn) && robotI > 0) {
                    robotI --;
                }
                count ++;
            }
            else if (choice.equals("2")) {
                if ((robotI + 1 != obstacleRow || robotJ != obstacleColumn) && robotI < size-1) {
                    robotI ++;
                }
                count ++;
            }
            else if (choice.equals("3")) {
                if ((robotI  != obstacleRow || robotJ - 1 != obstacleColumn) && robotJ > 0) {
                    robotJ --;
                }
                count ++;
            }
            else if (choice.equals("4")) {
                if ((robotI  != obstacleRow || robotJ + 1 != obstacleColumn) && robotJ < size-1) {
                    robotJ ++;
                }
                count ++;
            }
        }
        System.out.println("Congratulations! Robot is free!");
        if (countMove) {
            System.out.printf("It took %d moves.\n", count);
        }
        return;
    }
}