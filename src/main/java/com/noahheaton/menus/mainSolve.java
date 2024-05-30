package com.noahheaton.menus;

import com.noahheaton.*;

import java.util.Scanner;
public class mainSolve {


    public static void run() {
        Scanner kb = new Scanner(System.in);
        String input = "";
        do {
            System.out.print( ConsoleColors.RED + "Enter the size of the maze (Try not to make the maze larger than the screen): ");
            int size = kb.nextInt();

            // Create maze
            Maze maze = new Maze(size);

            maze.generateMaze();
            int[][] mazeArr = maze.getRawMaze();
            System.out.println(maze.getUnsolvedMaze());
            // Create second maze object
            Maze maze2 = new Maze(size);
            // Maze it the exact same as the first one
            maze2.setMaze(mazeArr);
            // Create both solve objects
            Solve solBfs = new Solve(maze, maze.getStart(), maze.getEnd());
            // SolDfs has the same starts and ends as solBfs
            Solve solDfs = new Solve(maze2, maze.getStart(), maze.getEnd());

            // BFS
            solBfs.bfs(maze, maze.getStart(), maze.getEnd(), new boolean[size][size]);
            // DFS
            solDfs.dfs(maze2, maze.getStart(), maze.getEnd(), new boolean[size][size]);

            do {
                System.out.println("Show unsolved maze first? (y/n): ");
                input = kb.next();
            } while (!input.equals("y") && !input.equals("n"));
            if (input.equals("y")) {
                System.out.println(maze.getUnsolvedFancyMaze());
            }
            if (input.equals("y")) {
                slep(3000);
            }

            do {

                System.out.println("Show solved maze? (y/n): ");
                input = kb.next();
            } while (!input.equals("y") && !input.equals("n"));
            if (input.equals("y")) {
                System.out.println(maze.getFancyMaze(maze.getMaze()));
                System.out.println("Breadth-First search tiles scanned: " + solBfs.getBfsCount());
                slep(3000);
                System.out.println("\nEnter any character to continue to Depth-first search");
                input = kb.next();
                System.out.println(maze2.getFancyMaze(maze2.getMaze()));
                System.out.println("Depth-First search tiles scanned: " + solDfs.getDfsCount());
                slep(3000);
                System.out.println("\nBFS tiles scanned: " + solBfs.getBfsCount() + " DFS tiles scanned: " + solDfs.getDfsCount() + "\n");
                slep(3000);
            }





            System.out.println("Again? (y/n): ");
            input = kb.next();
        } while (input.equals("y"));
    }
    public static void slep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
