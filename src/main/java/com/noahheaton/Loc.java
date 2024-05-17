package com.noahheaton;

public class Loc {
    public int x;
    public int y;
    Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean equals(Loc obj){
        if (obj instanceof Loc) {


            return this.x == obj.x && this.y == obj.y;
        }
        return false;
    }
}