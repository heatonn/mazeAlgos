package com.noahheaton.aStar;

import com.noahheaton.Maze;

import java.util.*;

public class aStarTrack {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up

    public static List<Node> findPath(int[][] maze, Node start, Node end) {
        if (!isInBounds(maze, start.x, start.y) || !isInBounds(maze, end.x, end.y)) {
            System.out.println("Start or end node is out of maze bounds.");
            return Collections.emptyList();
        }

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.fCost));
        Set<Node> closedList = new HashSet<>();
        Map<String, Node> allNodes = new HashMap<>();

        start.gCost = 0;
        start.hCost = calculateHeuristic(start, end);
        start.calculateFCost();
        openList.add(start);
        allNodes.put(start.getKey(), start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            if (currentNode.equals(end)) {
                List<Node> path = constructPath(currentNode);
                animateMaze(maze, closedList, path, start, end);
                return path;
            }
            closedList.add(currentNode);

            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];

                if (isWalkable(maze, newX, newY)) {
                    Node neighbor = allNodes.getOrDefault(Node.getKey(newX, newY), new Node(newX, newY));
                    int tentativeGCost = currentNode.gCost + 1;

                    if (tentativeGCost < neighbor.gCost) {
                        neighbor.gCost = tentativeGCost;
                        neighbor.hCost = calculateHeuristic(neighbor, end);
                        neighbor.calculateFCost();
                        neighbor.parent = currentNode;

                        if (!openList.contains(neighbor)) {
                            openList.add(neighbor);
                        }
                        allNodes.put(neighbor.getKey(), neighbor);
                    }
                }
            }

            // Animate the current state of the maze
            animateMaze(maze, closedList, Collections.emptyList(), start, end);
        }

        animateMaze(maze, closedList, Collections.emptyList(), start, end);
        return Collections.emptyList(); // No path found
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
        return Math.abs(node.x - goal.x) + Math.abs(node.y - goal.y); // Manhattan distance
    }

    private static boolean isWalkable(int[][] maze, int x, int y) {
        return isInBounds(maze, x, y) && maze[x][y] == 0;
    }

    private static boolean isInBounds(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }

    private static void animateMaze(int[][] maze, Set<Node> exploredNodes, List<Node> path, Node start, Node end) {
        char[][] mazePrint = new char[maze.length][maze[0].length];

        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                if (maze[x][y] == 1) {
                    mazePrint[x][y] = 'X';
                } else {
                    mazePrint[x][y] = ' ';
                }
            }
        }

        for (Node node : exploredNodes) {
            if (mazePrint[node.x][node.y] == ' ') {
                mazePrint[node.x][node.y] = '*';
            }
        }

        for (Node node : path) {
            mazePrint[node.x][node.y] = 'O';
        }

        mazePrint[start.x][start.y] = 'S'; // Start
        mazePrint[end.x][end.y] = 'E'; // End

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Print the current state of the maze
        for (int x = 0; x < mazePrint.length; x++) {
            for (int y = 0; y < mazePrint[0].length; y++) {
                System.out.print(mazePrint[x][y]);
            }
            System.out.println();
        }

        // Delay to show the animation
        try {
            Thread.sleep(300); // Adjust the delay as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        /*int[][] maze = {
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0}
        }; */
        Maze m = new Maze(20);
        m.generateMaze();
        //System.out.println(m.getUnsolvedFancyMaze());
        int[][] maze = m.getRawMaze();
         /* for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == 1) {
                    maze[r][c] = 0;
                } else if (maze[r][c] == 0){
                    maze[r][c] = 1;
                }
            }
            System.out.println();
        }  */

        System.out.println(m.getUnsolvedFancyMaze());
        //Node start = new Node(0, 0);
        //Node end = new Node(4, 5);
        Node start = new Node(m.getStart().getCol(), m.getStart().getRow());
        Node end = new Node(m.getEnd().getCol(), m.getEnd().getRow());
        List<Node> path = findPath(maze, start, end);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            //for (Node node : path) {
            //    System.out.println("(" + node.x + ", " + node.y + ")");
           // }
        } else {
            System.out.println("No path found.");
        }
    }
}
