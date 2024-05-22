package com.noahheaton;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class murti {
    private static final Object lock = new Object(); // A shared lock object
    private static final PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("output.csv");
            writer.println("trial,size,bfs,dfs");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to create output file", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a fixed-size thread pool
        int numThreads = Runtime.getRuntime().availableProcessors(); // Use the number of available processors
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int size = 20; size <= 500; size += 20) {
            System.out.println("Working on size: " + size);
            for (int trial = 1; trial <= 10000; trial++) {
                // Create a new task and submit it to the thread pool
                Runnable task = new MazeTask(trial, size);
                executorService.execute(task);
            }
        }

        // Shutdown the thread pool and wait for all tasks to complete
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        writer.close();
    }

    private static class MazeTask implements Runnable {
        private final int trial;
        private final int size;

        MazeTask(int trial, int size) {
            this.trial = trial;
            this.size = size;
        }

        @Override
        public void run() {
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

            synchronized (lock) {
                writer.println(trial + "," + size + "," + sol.getBfsCount() + "," + sol2.getDfsCount());
            }
        }
    }
}