package com.noahheaton;

import java.util.*;

public class Solve {
    private Maze maze;
    private int bfsCount;
    private int dfsCount;
    private int[][] bfsMazeArr;
    private int[][] dfsMazeArr;
    private Loc start;
    private Loc end;
    private boolean[][] visited;
    private Map<Loc, Loc> parent;
    private int moves;

    public Solve(Maze maze, Loc start, Loc end) {
        this.maze = maze;
        bfsMazeArr = maze.getMaze();
        dfsMazeArr = maze.getMaze();
        this.start = start;
        this.end = end;
        this.visited = new boolean[maze.getMaze().length][maze.getMaze().length];
        this.parent = new HashMap<>();
        moves = 0;
        bfsCount = 0;
        dfsCount = 0;
    }
    public int getMoves(){
        return moves;
    }
    /*public void setMaze(boolean a) {
         if (a) {
             maze.setMaze(bfsMazeArr);
         } else {
             maze.setMaze(dfsMazeArr);
         }
    }*/

    public boolean bfs(Maze maze, Loc start, Loc end, boolean[][] visited) {
        Queue<Loc> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Loc current = q.poll();
            if (current.equals(end)) {

                highlightPath(current);
                return true;
            }

            // check neighbors
            for (Loc neighbor : maze.findNeighbors(current)) {

                if (!visited[neighbor.y][neighbor.x] && (maze.getMaze()[neighbor.y][neighbor.x] == 1 || maze.getMaze()[neighbor.y][neighbor.x] == 3)) {
                q.offer(neighbor);
        // Skip if neighbor is the end location
                if (!neighbor.equals(end)) {
                 bfsMazeArr[neighbor.y][neighbor.x] = 5; // mark as visited
                    moves++;
                    bfsCount++;
              }
        visited[neighbor.y][neighbor.x] = true;
        parent.put(neighbor, current); // store parent
         }
        }
        }
        return false;
    }
   public boolean stepBfs(Maze maze, Loc start, Loc end, boolean[][] visited) {
    Queue<Loc> q = new LinkedList<>();
    q.offer(start);
    visited[start.y][start.x] = true;

    while (!q.isEmpty()) {
        Loc current = q.poll();
        if (current.equals(end)) {
            highlightPath(current);
            return true;
        }

        // check neighbors
        for (Loc neighbor : maze.findNeighbors(current)) {
            if (!visited[neighbor.y][neighbor.x] && (maze.getMaze()[neighbor.y][neighbor.x] == 1 || maze.getMaze()[neighbor.y][neighbor.x] == 3)) {
                q.offer(neighbor);
                // Skip if neighbor is the end location
                if (!neighbor.equals(end)) {
                    bfsMazeArr[neighbor.y][neighbor.x] = 5; // mark as visited
                    moves++;
                    bfsCount++;

                    // Print the maze after every move
                    System.out.println(maze.getFancyMaze(bfsMazeArr));
                    slep(Mainmain.getSpeed());

                }
                visited[neighbor.y][neighbor.x] = true;
                parent.put(neighbor, current); // store parent
            }
        }
    }
    return false;
}
public boolean stepDfs(Maze maze, Loc start, Loc end, boolean[][] visited) {
    Stack<Loc> q = new Stack<>();
    q.push(start);
    visited[start.y][start.x] = true;

    while (!q.isEmpty()) {
        Loc current = q.pop();
        if (current.equals(end)) {
            highlightPath(current);
            return true;
        }

        // check neighbors
        for (Loc neighbor : maze.findNeighbors(current)) {
            if (!visited[neighbor.y][neighbor.x] && (maze.getMaze()[neighbor.y][neighbor.x] == 1 || maze.getMaze()[neighbor.y][neighbor.x] == 3)) {
                q.push(neighbor);
                // Skip if neighbor is the end location
                if (!neighbor.equals(end)) {
                    dfsMazeArr[neighbor.y][neighbor.x] = 5;
                    moves++;
                    dfsCount++;
                    // mark as visited
                    System.out.println(maze.getFancyMaze(dfsMazeArr));
                    slep(Mainmain.getSpeed());
                }
                visited[neighbor.y][neighbor.x] = true;
                parent.put(neighbor, current); // store parent

                // Print the maze after every move

            }
        }
    }
    return false;
}
    public boolean dfs(Maze maze, Loc start, Loc end, boolean[][] visited) {
        Stack<Loc> q = new Stack<>();
        q.push(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Loc current = q.pop();
            if (current.equals(end)) {

                highlightPath(current);
                return true;
            }

            // check neighbors
            for (Loc neighbor : maze.findNeighbors(current)) {

                if (!visited[neighbor.y][neighbor.x] && (maze.getMaze()[neighbor.y][neighbor.x] == 1 || maze.getMaze()[neighbor.y][neighbor.x] == 3)) {
                    q.push(neighbor);
                    // Skip if neighbor is the end location
                    if (!neighbor.equals(end)) {
                        dfsMazeArr[neighbor.y][neighbor.x] = 5;
                        moves++;
                        dfsCount++;
                        // mark as visited
                    }
                    visited[neighbor.y][neighbor.x] = true;
                    parent.put(neighbor, current); // store parent
                }
            }
        }
        return false;
    }
    public void bfsCounterReset(){
        bfsCount = 0;
    }
    public int[][] getBfsMaze(){
        return bfsMazeArr;
    }
    public int[][] getDfsMaze(){
        return dfsMazeArr;
    }
    public void dfsCounterReset(){
        dfsCount = 0;
    }
    public int getBfsCount(){
        return bfsCount;
    }
    public int getDfsCount(){
        return dfsCount;
    }
    public void resetBfsCount(){
        bfsCount = 0;
    }
    public void resetDfsCount(){
        dfsCount = 0;
    }
    private void highlightPath(Loc end) {
    Loc current = end;
    // Skip if current is the end location
    if (current.equals(end)) {
        current = parent.get(current);
    }
    while (current != null && !current.equals(start)) {
        bfsMazeArr[current.y][current.x] = 6; // mark as part of the solution
        current = parent.get(current);
    }
}
    public static void slep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}