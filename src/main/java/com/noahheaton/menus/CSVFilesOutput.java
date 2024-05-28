package com.noahheaton.menus;
import com.noahheaton.ConsoleColors;
import com.noahheaton.Maze;
import com.noahheaton.Solve;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVFilesOutput {
    public static void run() throws FileNotFoundException {
        Scanner kb = new Scanner(System.in);
        PrintWriter w = new PrintWriter("output.csv");
        w.println("trial,size,bfs,dfs");
        System.out.println("Enter the " + ConsoleColors.BLUE_BOLD_BRIGHT + "FINAL" + ConsoleColors.RESET + " size of the maze (goes from 20 up to "+ ConsoleColors.BLUE_BOLD_BRIGHT + "FINAL" + ConsoleColors.RESET +" in increments of 20): ");
        int sizeEnd = kb.nextInt();
        System.out.println("How many " + ConsoleColors.PURPLE_BOLD_BRIGHT + "TRIALS" + ConsoleColors.RESET + " would you like to run for " + ConsoleColors.RED_BOLD_BRIGHT + "EACH SIZE" + ConsoleColors.RESET + "?: ");
        int trials = kb.nextInt();
        for (int size = 20; size <= sizeEnd; size += 20) {
            System.out.println("Working on size: " + size);
            for (int trial = 1; trial <= trials; trial++) {
                // Create THE maze
                Maze maze = new Maze(size);
                maze.generateMaze();
                int[][] mazeArr = maze.getRawMaze();
                // Second maze object
                Maze maze2 = new Maze(size);
                maze2.setMaze(mazeArr);
                Solve sol = new Solve(maze, maze.getStart(), maze.getEnd());
                Solve sol2 = new Solve(maze2, maze.getStart(), maze.getEnd());
                sol.bfs(maze, maze.getStart(), maze.getEnd(), new boolean[size][size]);
                sol2.dfs(maze2, maze.getStart(), maze.getEnd(), new boolean[size][size]);
                //System.out.println(maze.getFancyMaze(maze.getMaze()));
                //System.out.println(maze2.getFancyMaze(maze2.getMaze()));
                //System.out.println("BFS: " + sol.getBfsCount() + " DFS: " + sol2.getDfsCount() + "\n");
                //System.out.println("Again? (y/n): ");
                w.println(trial + "," + size + "," + sol.getBfsCount() + "," + sol2.getDfsCount());

            }
        }

        w.close();
        System.out.println("CSV file has been created at " + ConsoleColors.BLUE_UNDERLINED + "output.csv" + ConsoleColors.RESET);
        mainSolve.slep(5000);
    }
}
