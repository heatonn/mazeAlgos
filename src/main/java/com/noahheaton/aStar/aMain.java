package com.noahheaton.aStar;

import java.util.List;
import static com.noahheaton.aStar.aStarTrack.findPath;

public class aMain {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0}
        };

        Node start = new Node(0, 0);
        Node end = new Node(5, 4);
        List<Node> path = findPath(maze, start, end);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.y + ", " + node.x + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}


