package com.noahheaton;

class Node {
    public int x, y; // Coordinates
    public int g, h; // g = distance from start, h = heuristic from current node to end
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Node (Loc loc){
        this.x = loc.x;
        this.y = loc.y;
    }
    public void fromLoc (Loc loc){
        this.x = loc.x;
        this.y = loc.y;
    }

    // Calculate the heuristic (e.g., Manhattan distance)
    int calculateHeuristic(Node endNode) {
        return Math.abs(endNode.x - x) + Math.abs(endNode.y - y);
    }
}
