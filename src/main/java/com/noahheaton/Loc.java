package com.noahheaton;

public class Loc {
    public int x;
    public int y;
    public int g, h;
    public Loc parent;// g = distance from start, h = heuristic from current node to end
    public Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Loc(int row, int col, int g, int h, Loc parent) {
        this.x = row;
        this.y = col;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }
    int calculateHeuristic(Loc endNode) {
        return Math.abs(endNode.x - x) + Math.abs(endNode.y - y);
    }
    public int getRow() {
        return x;
    }
    public int getCol() {
        return y;
    }
    public void setRow(int row) {
        this.x = row;
    }
    public void setCol(int col) {
        this.y = col;
    }

    public boolean equals(Loc obj){
        if (obj instanceof Loc) {


            return this.x == obj.x && this.y == obj.y;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}