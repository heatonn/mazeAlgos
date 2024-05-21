package com.noahheaton;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class multiMazeTrial {
    private static final Object lock = new Object(); // A shared lock object
    private static final PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("output.csv");
            writer.println("maze,trial,size,bfs,dfs");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to create output file", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a fixed-size thread pool
        int numThreads = Runtime.getRuntime().availableProcessors(); // Use the number of available processors
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        int mazeNumber = 1;

        for (int size = 20; size <= 500; size += 20) {
            System.out.println("Working on size: " + size);
            for (int trialGroup = 1; trialGroup <= 10; trialGroup++) { // 10 groups of 100 trials
                // Create the maze once for each group of 100 trials
                Maze maze = new Maze(size);
                maze.generateMaze();
                int[][] mazeArr = maze.getRawMaze();
                Maze maze2 = new Maze(size);
                maze2.setMaze(mazeArr);

                for (int trial = 1; trial <= 100; trial++) {
                    // Create a new task and submit it to the thread pool
                    Runnable task = new MazeTask(mazeNumber, trial, size, mazeArr);
                    executorService.execute(task);
                }
                mazeNumber++;
            }
        }

        // Shutdown the thread pool and wait for all tasks to complete
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        writer.close();
    }

    private static class MazeTask implements Runnable {
        private final int mazeNumber;
        private final int trial;
        private final int size;
        private final int[][] mazeArr;

        MazeTask(int mazeNumber, int trial, int size, int[][] mazeArr) {
            this.mazeNumber = mazeNumber;
            this.trial = trial;
            this.size = size;
            this.mazeArr = mazeArr;
        }

        @Override
        public void run() {
            // Use the provided maze array to create the maze objects
            Maze maze = new Maze(size);
            maze.setMaze(mazeArr);
            Maze maze2 = new Maze(size);
            maze2.setMaze(mazeArr);
            Solve sol = new Solve(maze, maze.getStart(), maze.getEnd());
            Solve sol2 = new Solve(maze2, maze.getStart(), maze.getEnd());
            sol.bfs(maze, maze.getStart(), maze.getEnd(), new boolean[size][size]);
            sol2.dfs(maze2, maze.getStart(), maze.getEnd(), new boolean[size][size]);

            synchronized (lock) {
                writer.println(mazeNumber + "," + trial + "," + size + "," + sol.getBfsCount() + "," + sol2.getDfsCount());
            }
        }
    }
}
