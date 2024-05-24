package com.noahheaton;

public class star {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };
        Maze m = new Maze(10);
        m.generateMaze();
        System.out.println(m.getUnsolvedMaze());


        Loc start = new Loc(m.getStart().x, m.getStart().y, m.getStart().g, m.getStart().h, null);
        Loc end = new Loc(m.getEnd().x, m.getEnd().y, m.getEnd().g, m.getEnd().h, null);

        AStar aStar = new AStar(m.getRawMaze(), start, end);
        Loc endNode = aStar.findPath();
        aStar.printPath(endNode);
        System.out.println("done");
    }
}
