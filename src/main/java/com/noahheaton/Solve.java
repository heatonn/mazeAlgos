package com.noahheaton;

import java.util.LinkedList;
import java.util.Queue;

public class Solve{
    private Maze maze;
    private int[][] mazeArr;
    private Loc start;
    private Loc end;
    private boolean[][] visited;
    public Solve(Maze maze, Loc start, Loc end){
        this.maze = maze;
        mazeArr = maze.getMaze();
        this.start = start;
        this.end = end;
        this.visited = new boolean[maze.getMaze().length][maze.getMaze().length];



    }
    public void setMaze(){
        maze.setMaze(mazeArr);
    }

    public boolean bfs(Loc start, Loc end, boolean[][] visited) {
        Queue<Loc> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Loc current = q.poll();

            if (current.equals(end)) {
                return true;

            }

            // check neighbors
            int count = 0;
            for (Loc neighbor : maze.findNeighbors(current)) {

                if (!visited[neighbor.y][neighbor.x] && maze.getMaze()[neighbor.y][neighbor.x] == 1) {
                    q.offer(neighbor);
                    mazeArr[neighbor.y][neighbor.x] = (count % 7) + 4;
                    visited[neighbor.y][neighbor.x] = true;
                }
                count++;
            }
        }
        return false;
    }


}
