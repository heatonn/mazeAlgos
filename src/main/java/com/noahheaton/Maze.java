package com.noahheaton;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Arrays;

@SuppressWarnings("ALL")
public class Maze {
    private int dim;
    private int[][] maze;
    private Random rand = new Random();
    private Stack<Loc> stack = new Stack<>();
    private Loc start;
    private Loc end;

    /*
     * █ (Wall)
     * ░ (Path)
     * ☺ (Start)
     * ☻ (Stop)
     *
     */
    public Maze(int dim) {
        this.dim = dim;
        this.maze = new int[dim][dim];

    }
    @SuppressWarnings("unused")
    public void printMaze(){
        System.out.println(Arrays.deepToString(maze));
    }
    public void generateMaze(){
        stack.push(new Loc(0,0));
        while (!stack.empty()){
            Loc next = stack.pop();
            if(isValidLoc(next)){

                maze[next.y][next.x] = 1;
                ArrayList<Loc> neighbors = findNeighbors(next);
                rndmLocAdd(neighbors);
            }
        }
        SolvableMaze s = new SolvableMaze(this);
        s.solveMaze();
        this.addGoal(s.getStart(), s.getEnd());


    }

    public boolean isValidLoc(Loc loc) {
        int numNeighboringOnes = 0;
        for (int y = loc.y - 1; y <= loc.y + 1; y++) {
            for (int x = loc.x - 1; x <= loc.x + 1; x++) {
                if (isOnGrid(x, y) && !(loc.x == x && loc.y == y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes <= 2) && maze[loc.y][loc.x] == 0;
    }

    public ArrayList<Loc> findNeighbors(Loc loc){
        ArrayList<Loc> neighbors = new ArrayList<Loc>();
        for(int y = loc.y-1; y <= loc.y+1; y++ ){
            for(int x = loc.x-1; x <= loc.x+1; x++){
                if(isOnGrid(x,y) && isNotCorner(loc, x, y) && !(loc.x == x && loc.y == y)) neighbors.add(new Loc(x,y));
            }
        }
        return neighbors;
    }

    private boolean isNotCorner(Loc loc, int x, int y){
        return (loc.x == x || loc.y == y);
    }
    private boolean isOnGrid(int x, int y){
        return (x >= 0 && x < dim && y >= 0 && y < dim);
    }
    private void rndmLocAdd(ArrayList<Loc> locs){
        int targetIndex;
        while(!locs.isEmpty()){
            targetIndex = rand.nextInt(locs.size());
            stack.push(locs.remove(targetIndex));
        }
    }
    public String getRawMaze(){
        StringBuilder gar = new StringBuilder();
        for(int[] i : maze){
            gar.append(Arrays.toString(i) + "\n" );
        }
        return gar.toString();
    }
    public String getFancyMaze(){
        StringBuilder gar = new StringBuilder();
        for(int[] i : maze){
            gar.append("|  ");
            for(int j : i){
                ;
                if(j == 0) gar.append("X");
                else if (j == 2) gar.append("☻");
                else if (j == 3) gar.append("☻");
                else if (j == 5) gar.append(ConsoleColors.PURPLE + "X" + ConsoleColors.RED);
                else gar.append(" ");
                gar.append("  ");
            }
            gar.append("|");
            gar.append("\n");
        }
        return gar.toString();
    }
    public int[][] getMaze(){
        return maze;
    }
    public void setMaze(int[][] e){
        this.maze = e;
    }
    public void addGoal(Loc start, Loc end){
        maze[start.y][start.x] = 2;
        maze[end.y][end.x] = 3;
        this.start = start;
        this.end = end;
    }
    public Loc getStart(){
        return start;
    }
    public Loc getEnd(){
        return end;
    }



}