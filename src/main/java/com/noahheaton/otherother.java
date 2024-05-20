package com.noahheaton;
import java.util.Scanner;
public class otherother {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String input = "";
        do {
            System.out.print("Enter the size of the maze (anything above 50 might look weird): ");
            int size = kb.nextInt();
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
            System.out.println(maze.getFancyMaze(maze.getMaze()));
            System.out.println(maze2.getFancyMaze(maze2.getMaze()));
            System.out.println("BFS: " + sol.getBfsCount() + " DFS: " + sol2.getDfsCount() + "\n");
            System.out.println("Again? (y/n): ");
            input = kb.next();
        } while (input.equalsIgnoreCase("y"));


    }
}
