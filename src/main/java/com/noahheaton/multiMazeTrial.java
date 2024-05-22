package com.noahheaton;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class multiMazeTrial {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter w = new PrintWriter("output.csv");
        w.println("trial,size,bfs,dfs");
        for (int size = 20; size <= 500; size += 20) {
            System.out.println(size);            for (int trial = 1; trial <= 1000; trial++) {
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
    }
}
