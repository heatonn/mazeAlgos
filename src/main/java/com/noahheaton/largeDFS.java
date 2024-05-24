package com.noahheaton;

import java.util.Scanner;

public class largeDFS {
    public static void main(String[] args){

        do {
            //System.out.print("Enter the size of the maze (anything above 50 might look weird): ");
            int size = 60;//kb.nextInt();
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
            if (sol.getBfsCount() <= 20  && sol2.getDfsCount() >= 1000) {
                System.out.println(ConsoleColors.RED + maze.getUnsolvedFancyMaze());
                System.out.println(ConsoleColors.RED + maze.getFancyMaze(maze.getMaze()));
                System.out.println(ConsoleColors.RED + maze2.getFancyMaze(maze2.getMaze()));
                System.out.println("BFS: " + sol.getBfsCount() + " DFS: " + sol2.getDfsCount() + "\n");
                break;
            }
            System.out.println();


        } while (true);
    }
}
