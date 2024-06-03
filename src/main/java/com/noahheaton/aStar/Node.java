package com.noahheaton.aStar;

import java.util.Objects;

public class Node {
    public int x;
    public int y;
    public int gCost; // Cost from start to current node
    public int hCost; // Heuristic cost to goal
    public int fCost; // gCost + hCost
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.gCost = Integer.MAX_VALUE; // Start with a high value
        this.hCost = 0;
        this.fCost = 0;
        this.parent = null;
    }

    public void calculateFCost() {
        this.fCost = this.gCost + this.hCost;
    }

    public String getKey() {
        return x + "," + y;
    }

    public static String getKey(int x, int y) {
        return x + "," + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
