package com.noahheaton.aStar;

import com.noahheaton.Loc;

public class Node extends Loc {
        public int gCost; // Cost from start to current node
        public int hCost; // Heuristic cost to goal
        public int fCost; // gCost + hCost
        public Node parent;
        public int x;
        public int y;

        public Node(int col, int row) {
            super(col, row);
            this.x = col;
            this.y = row;
            this.gCost = 0;
            this.hCost = 0;
            this.fCost = 0;
            this.parent = null;
        }


        public void calculateFCost() {
            this.fCost = this.gCost + this.hCost;
        }
    }
