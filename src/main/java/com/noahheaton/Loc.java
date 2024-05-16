package com.noahheaton;

public class Loc {
    public int x;
    public int y;
    Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Loc) {
            Loc other = (Loc) obj;
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }
}