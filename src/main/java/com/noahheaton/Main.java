package com.noahheaton;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    String input;

    do {
        System.out.print("Enter the size of the maze (anything above 50 might look weird): ");
        int size = kb.nextInt();
        Maze maze = new Maze(size);
        maze.generateMaze();
        System.out.println("SYMBOLIC MAZE\n" + maze.getFancyMaze());

        System.out.println("Enter 'y' to redo or 'n' to exit: ");
        input = kb.next();
    } while(input.equalsIgnoreCase("y"));
}
}