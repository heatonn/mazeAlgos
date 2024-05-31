package com.noahheaton.aStar;

import com.noahheaton.Loc;

public class aStarMain {
    public static class Node extends Loc {
        public int gCost; // Cost from start to current node
        public int hCost; // Heuristic cost to goal
        public int fCost; // gCost + hCost
        public Node parent;

        public Node(int row, int col) {
            super(row, col);
            this.gCost = 0;
            this.hCost = 0;
            this.fCost = 0;
            this.parent = null;
        }


        public void calculateFCost() {
            this.fCost = this.gCost + this.hCost;
        }
    }
}
