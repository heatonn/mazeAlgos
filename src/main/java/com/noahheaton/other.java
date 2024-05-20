package com.noahheaton;
import java.util.Scanner;
public class other {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print(ConsoleColors.RED + "Enter the size of the maze (anything above 50 might look weird): ");
        int size = kb.nextInt();
        Maze maze = new Maze(size);
        maze.generateMaze();
        int[][] a = maze.getRawMaze();

        Solve sol = new Solve(maze, maze.getStart(), maze.getEnd());
        sol.bfs(maze, maze.getStart(), maze.getEnd(), new boolean[size][size]);
        System.out.println(maze.getFancyMaze(maze.getMaze()));

        Maze b = new Maze(size);

        b.setMaze(a);
        Solve sol2 = new Solve(b, maze.getStart(), maze.getEnd());
        sol2.dfs(b, maze.getStart(), maze.getEnd(), new boolean[size][size]);
        System.out.println(b.getFancyMaze(b.getMaze()));
        System.out.println("BFS: " + sol.getBfsCount() + " DFS: " + sol2.getDfsCount() + "\n");

    }
}