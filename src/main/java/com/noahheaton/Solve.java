package com.noahheaton;

import java.util.*;

public class Solve {
    private Maze maze;
    private int[][] mazeArr;
    private Loc start;
    private Loc end;
    private boolean[][] visited;
    private Map<Loc, Loc> parent;

    public Solve(Maze maze, Loc start, Loc end) {
        this.maze = maze;
        mazeArr = maze.getMaze();
        this.start = start;
        this.end = end;
        this.visited = new boolean[maze.getMaze().length][maze.getMaze().length];
        this.parent = new HashMap<>();
    }

    public void setMaze() {
        maze.setMaze(mazeArr);
    }

    public boolean bfs(Loc start, Loc end, boolean[][] visited) {
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
                 mazeArr[neighbor.y][neighbor.x] = 5; // mark as visited
              }
        visited[neighbor.y][neighbor.x] = true;
        parent.put(neighbor, current); // store parent
         }
        }
        }
        return false;
    }

    public boolean dfs(Loc start, Loc end, boolean[][] visited) {
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
                        mazeArr[neighbor.y][neighbor.x] = 5; // mark as visited
                    }
                    visited[neighbor.y][neighbor.x] = true;
                    parent.put(neighbor, current); // store parent
                }
            }
        }
        return false;
    }

    private void highlightPath(Loc end) {
    Loc current = end;
    // Skip if current is the end location
    if (current.equals(end)) {
        current = parent.get(current);
    }
    while (current != null && !current.equals(start)) {
        mazeArr[current.y][current.x] = 6; // mark as part of the solution
        current = parent.get(current);
    }
}
}