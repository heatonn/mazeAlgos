package com.noahheaton;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
@SuppressWarnings("ALL")
public class SolvableMaze{
  private int[][] maze;
  private Maze m;
  private Loc start;
  private Loc end;

  public SolvableMaze(Maze m){
    this.maze = m.getMaze();
    boolean[][] visited = new boolean[maze.length][maze.length];
    this.m = m;
    start = new Loc(0, 0);
    end = new Loc(maze.length - 1, maze.length - 1);
  }

    public Loc getRandomEdge() {
      int x = 0;
      int y = 0;
      Random rand = new Random();
      // Random number 1 or 2
        int side = rand.nextInt(2) + 1;
        if (side == 1){
            x = rand.nextInt(maze.length);
            y = 0;
        } else {
            x = 0;
            y = rand.nextInt(maze.length);
        }
    return new Loc(x, y);
}

    private boolean bfs(Loc start, Loc end, int[][] maze, boolean[][] visited) {
        Queue<Loc> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Loc current = q.poll();

            if (current.equals(end)) {
                return true;
            }

            // check neighbors
            for (Loc neighbor : m.findNeighbors(current)) {
                if (!visited[neighbor.y][neighbor.x] && maze[neighbor.y][neighbor.x] == 1) {
                    q.offer(neighbor);
                    visited[neighbor.y][neighbor.x] = true;
                }
            }
        }
        return false;
    }

    public void solveMaze(){
      Loc start = getRandomEdge();
      Loc end = getRandomEdge();
      boolean[][] visited = new boolean[maze.length][maze.length];
      if (bfs(start, end, maze, visited) && !start.equals(end)){
          System.out.println("Maze is solvable");
          System.out.println("Start: " + start.y + ", " + start.x);
          this.start = new Loc(start.x, start.y);
          System.out.println("End: " + end.y + ", " + end.x);
          this.end = new Loc(end.x, end.y);
        } else {
            System.out.println("Maze is not solvable");
            System.out.println("Start: " + start.y + ", " + start.x);
            System.out.println("End: " + end.y + ", " + end.x);
            solveMaze();

      }
    }
    public Loc getStart() {
      return start;
    }
    public Loc getEnd() {
      return end;
    }











    public int[][] getMaze() {
      return maze;
    }
}