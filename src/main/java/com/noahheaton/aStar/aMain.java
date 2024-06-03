package com.noahheaton.aStar;

import com.noahheaton.Loc;
import com.noahheaton.Maze;

import java.util.List;
import static com.noahheaton.aStar.aStarTrack.findPath;
public class aMain {
    public static void run(int size, Maze m, Maze f) {
        //Maze m = new Maze(size);
        //m.generateMaze();
        int[][] maze = m.getRawMaze();

        System.out.println(m.getUnsolvedFancyMaze());
        //Node starte = new Node(start);
        //Node ende = new Node(end);

        Node start = new Node(m.getStart().getCol(), m.getStart().getRow());
        Node end = new Node(m.getEnd().getCol(), m.getEnd().getRow());
        List<Node> path = findPath(maze, start, end);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
        } else {
            System.out.println("No path found.");
        }
    }
    public static void main(Maze ma) {
        Maze m = ma;
        //m.generateMaze();
        int[][] maze = m.getRawMaze();

        System.out.println(m.getUnsolvedFancyMaze());
       // Node starte = new Node(start);
       // Node ende = new Node(end);

        Node start = new Node(m.getStart().getCol(), m.getStart().getRow());
        Node end = new Node(m.getEnd().getCol(), m.getEnd().getRow());
        List<Node> path = findPath(maze, start, end);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
        } else {
            System.out.println("No path found.");
        }
    }

}