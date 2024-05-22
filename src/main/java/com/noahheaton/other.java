package com.noahheaton;
import java.util.Scanner;
public class other {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String input = "";
        Maze mainMaze = new Maze(20);
        mainMaze.generateMaze();
        int[][] mazeArr = mainMaze.getRawMaze();
        do {
            //System.out.print("Enter the size of the maze (anything above 50 might look weird): ");
            int size = 20;//kb.nextInt();
            // Create THE maze
            Maze maze = new Maze(size);
            maze.setMaze(mazeArr);


            Solve sol = new Solve(maze, maze.getStart(), maze.getEnd());
            sol.dfs(maze, mainMaze.getStart(), mainMaze.getEnd(), new boolean[size][size]);


            System.out.println(maze.getFancyMaze(maze.getMaze()));
            //System.out.println(maze2.getFancyMaze(maze2.getMaze()));
            System.out.println("DFS: " + sol.getDfsCount() + "\n");
            sol.resetDfsCount();
            System.out.println("Again? (y/n): ");
            input = kb.next();
        } while (input.equalsIgnoreCase("y"));
    }
}