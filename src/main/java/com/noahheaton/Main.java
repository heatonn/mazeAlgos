package com.noahheaton;
import com.noahheaton.menus.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    Scanner kb = new Scanner(System.in);
    int option = firstGUI();
    switch (option) {
        case 1:
            mainSolve.run();
            break;
        case 2:
            System.out.print("Size of maze: ");
            int size = kb.nextInt();
            System.out.print("Speed of animation (~10ms is good): ");
            int speed = kb.nextInt();
            ScreenSaver.run(size, speed);
            break;
        case 3:
            CSVFilesOutput.run();
            break;


    }

    }

    public static int firstGUI() {
        System.out.println("Welcome to the maze demo!\nPlease select an option:");
        System.out.println("1. Run the " + ConsoleColors.RED_BOLD_BRIGHT + "NORMAL"+ ConsoleColors.RESET + " maze solver");
        System.out.println("2. Run the " + ConsoleColors.RED_BOLD_BRIGHT + "ANIMATED"+ ConsoleColors.RESET + " maze solver");
        System.out.println("3. Run the " + ConsoleColors.RED_BOLD_BRIGHT + "DATA OUTPUT"+ ConsoleColors.RESET + " maze solver");

        Scanner kb = new Scanner(System.in);
        int choice = kb.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please select an option:");
            choice = kb.nextInt();


            }
        switch (choice) {
            case 1:
                System.out.println("\nYou have chosen the " + ConsoleColors.RED_BOLD_BRIGHT + "NORMAL" + ConsoleColors.RESET + " maze solver");
                break;
            case 2:
                System.out.println("\nYou have chosen the " + ConsoleColors.RED_BOLD_BRIGHT + "ANIMATED" + ConsoleColors.RESET + " maze solver");
                break;
            case 3:
                System.out.println("\nYou have chosen the " + ConsoleColors.RED_BOLD_BRIGHT + "DATA OUTPUT" + ConsoleColors.RESET + " maze solver");
                break;

        }
        return choice;
    }




}