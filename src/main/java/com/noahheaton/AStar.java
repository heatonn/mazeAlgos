package com.noahheaton;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    int[][] maze; // 2D array representing the maze
    Loc start, end;
    PriorityQueue<Loc> openList; // Min-heap to store the open nodes
    HashSet<Loc> closedList; // Set of visited nodes

    AStar(int[][] maze, Loc start, Loc end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
        openList = new PriorityQueue<>(Comparator.comparingInt(a -> a.g + a.h)); // Min-heap based on f = g + h
        closedList = new HashSet<>(); // Use a HashSet for the closed list to ensure O(1) access
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0;
    }

    public int calculateH(int row, int col) {
        return Math.abs(row - end.x) + Math.abs(col - end.y); // Manhattan distance
    }

    public void addNeighbors(Loc current) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int newRow = current.x + dir[0];
            int newCol = current.y + dir[1];
            if (isValid(newRow, newCol)) {
                Loc neighbor = new Loc(newRow, newCol, current.g + 1, calculateH(newRow, newCol), current);
                if (closedList.contains(neighbor)) continue;

                boolean inOpenList = false;
                for (Loc openNode : openList) {
                    if (openNode.x == newRow && openNode.y == newCol && openNode.g <= neighbor.g) {
                        inOpenList = true;
                        break;
                    }
                }

                if (!inOpenList) {
                    openList.add(neighbor);
                }
            }
        }
    }

    public Loc findPath() {
        openList.add(new Loc(start.x, start.y, 0, calculateH(start.x, start.y), null));

        while (!openList.isEmpty()) {
            Loc current = openList.poll();
            if (current.x == end.x && current.y == end.y) {
                return current; // Path found
            }

            closedList.add(current);
            addNeighbors(current);
        }
        return null; // Path not found
    }

    public void printPath(Loc endNode) {
        if (endNode == null) {
            System.out.println("No path found.");
            return;
        }

        Loc current = endNode;
        while (current != null) {
            maze[current.x][current.y] = 5; // Mark the path in the maze
            current = current.parent;
        }

        // Print the maze with the path
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append("| ");
            for (int col : row) {
                sb.append(col == 5 ? "*" : col == 1 ? "#" : " ");
            }
            sb.append(" |\n");

        }
        System.out.println(sb.toString());
    }
}