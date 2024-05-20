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
        Maze a = maze;
        Solve sol = new Solve(maze, maze.getStart(), maze.getEnd());
        sol.bfs(maze, maze.getStart(), maze.getEnd(), new boolean[size][size]);

        //sol.setMaze(true);

        System.out.println("SYMBOLIC MAZE\n" + maze.getUnsolvedFancyMaze());



        do {
            System.out.println("Enter 'y' to see the solved maze or 'n' to exit: ");
            input = kb.next();
            if (input.equalsIgnoreCase("y")) {

                maze.setMaze(sol.getBfsMaze());
                System.out.println("SOLVED BFS MAZE\n" + maze.getFancyMaze(sol.getBfsMaze()));
                maze.setMaze(sol.getDfsMaze());
                //sol.setMaze(false);
                sol.dfs(a, maze.getStart(), maze.getEnd(), new boolean[size][size]);

                System.out.println("SOLVED DFS MAZE\n" + maze.getFancyMaze(sol.getDfsMaze()));
                System.out.println("BFS: " + sol.getBfsCount() + " DFS: " + sol.getDfsCount() + "\n");
                System.out.println(sol.getMoves());
            }

        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));





        System.out.println("Enter 'y' to redo or 'n' to exit: ");
        input = kb.next();
    } while(input.equalsIgnoreCase("y"));
}
}