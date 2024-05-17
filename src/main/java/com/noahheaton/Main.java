package com.noahheaton;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    String input;

    do {
        System.out.print(ConsoleColors.RED + "Enter the size of the maze (anything above 50 might look weird): ");
        int size = kb.nextInt();
        Maze maze = new Maze(size);
        maze.generateMaze();

        Solve sol = new Solve(maze, maze.getStart(), maze.getEnd());
        sol.bfs(maze.getStart(), maze.getEnd(), new boolean[size][size]);
        sol.setMaze();
        System.out.println("SYMBOLIC MAZE\n" + maze.getUnsolvedFancyMaze());



        do {
            System.out.println("Enter 'y' to see the solved maze or 'n' to exit: ");
            input = kb.next();
            if (input.equalsIgnoreCase("y")) {
                System.out.println("SOLVED MAZE\n" + maze.getFancyMaze());
            }
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));





        System.out.println("Enter 'y' to redo or 'n' to exit: ");
        input = kb.next();
    } while(input.equalsIgnoreCase("y"));
}
}