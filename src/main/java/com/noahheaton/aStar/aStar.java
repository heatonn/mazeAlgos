package com.noahheaton.aStar;

import java.util.*;

public class aStar {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up
    public static List<Node> findPath (int[][] maze, Node start, Node end) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.fCost));
        Set<Node> closedList = new HashSet<>();

        start.gCost = 0;
        start.hCost = calculateHeuristic(start, end);
        start.calculateFCost();
        openList.add(start);

        while(!openList.isEmpty()){
            Node currentNode = openList.poll();
            if (currentNode.y == end.y && currentNode.x == end.x) {
                return constructPath(currentNode);
            }
            closedList.add(currentNode);

            for (int[] direction : DIRECTIONS) {
             int newRow = currentNode.y + direction[0];
             int newCol = currentNode.x + direction[1];

             if (isWalkable(maze, newRow, newCol) && !isInList(closedList, newRow, newCol)) {
                 Node neighbor = new Node(newRow, newCol);
                 int tentativeGCost = currentNode.gCost + 1;

                 if (tentativeGCost < neighbor.gCost || !isInList(openList, neighbor.y, neighbor.x)) {
                     neighbor.gCost = tentativeGCost;
                     neighbor.hCost = calculateHeuristic(neighbor, end);
                     neighbor.calculateFCost();
                     neighbor.parent = currentNode;

                     if (!isInList(openList, neighbor.y, neighbor.x)) {
                         openList.add(neighbor);
                     }
                 }
             }

            }




        }
        return Collections.emptyList();
    }




    private static List<Node> constructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
    private static int calculateHeuristic(Node node, Node goal) {
        return Math.abs(node.y - goal.y) + Math.abs(node.x - goal.x); // Manhattan distance
    }
    private static boolean isWalkable(int[][] maze, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0;
    }

    private static boolean isInList(Collection<Node> list, int row, int col) {
        return list.stream().anyMatch(node -> node.y == row && node.x == col);
    }

}
