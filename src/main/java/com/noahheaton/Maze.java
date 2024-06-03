package com.noahheaton;

import java.util.*;

@SuppressWarnings("ALL")
public class Maze implements Cloneable {
    private int dim;
    private int[][] maze;
    private int[][] rawMaze;
    private Random rand = new Random();
    private Stack<Loc> stack = new Stack<>();
    private Loc start;
    private Loc end;
    private int count;

    /*
     * █ (Wall)
     * ░ (Path)
     * ☺ (Start)
     * ☻ (Stop)
     *
     */
    public Maze(int dim) {
        count = 0;
        this.dim = dim;
        this.maze = new int[dim][dim];
        this.rawMaze = new int[dim][dim];

    }
    @Override
    public Maze clone() {
        try {
            Maze cloned = (Maze) super.clone();
            // If there are any fields that are arrays or mutable objects,
            // you need to clone them here as well.
            // For example, if maze is an array:
            cloned.maze = this.maze.clone();
            // If rawMaze is an array:
            cloned.rawMaze = this.rawMaze.clone();
            // If start and end are mutable objects:
            cloned.start = new Loc(this.start.x, this.start.y);
            cloned.end = new Loc(this.end.x, this.end.y);
            // Do this for all fields that need to be deep copied
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
    public int[][] getRawMaze(){
        return rawMaze;
    }
    @SuppressWarnings("unused")
    public void printMaze(){System.out.println(Arrays.deepToString(maze));}
    public void generateMaze(){
        stack.push(new Loc(0,0));
        while (!stack.empty()){
            Loc next = stack.pop();
            if(isValidLoc(next)){

                maze[next.y][next.x] = 1;
                rawMaze[next.y][next.x] = 1;
                ArrayList<Loc> neighbors = (ArrayList<Loc>) findNeighbors(next);
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

    public List<Loc> findNeighbors(Loc loc){
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
    public String getUnsolvedMaze(){
        StringBuilder gar = new StringBuilder();
        for(int[] i : rawMaze){
            gar.append(Arrays.toString(i) + "\n" );
        }
        return gar.toString();
    }

    public String getUnsolvedFancyMaze() {
        StringBuilder gar = new StringBuilder();
        for (int[] i : rawMaze) {
            gar.append("| ");
            for (int j : i) {
                if (j == 0) {
                    gar.append("X "); // Add an extra space after 'X'
                } else if (j == 2) {
                    gar.append("@ "); // Add an extra space after '?'
                } else if (j == 3) {
                    gar.append("@ "); // Add an extra space after '?'
                } else {
                    gar.append("  "); // Add two spaces for empty cells
                }
            }
            gar.append("|\n"); // Add a space before the vertical bar
        }
        return gar.toString();
    }
    public String getSolvedMaze() {
        StringBuilder gar = new StringBuilder();
        for (int[] i : maze) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            gar.append("| ");
            for (int j : i) {
                if (j == 0) {
                    gar.append("X  "); // Add an extra space after 'X'
                } else if (j == 2) {
                    gar.append("@  "); // Add an extra space after '?'
                } else if (j == 3) {
                    gar.append("@  "); // Add an extra space after '?'
                } else if (j == 6) {
                    gar.append(ConsoleColors.GREEN + "X " + ConsoleColors.RED + " "); // Add an extra space after colored 'X'
                } else {
                    gar.append("   "); // Add two spaces for empty cells
                }
            }
            gar.append("|\n"); // Add a space before the vertical bar
        }
        return gar.toString();
    }
    public String getFancyMaze(int[][] maze) {
        int count = 0;
        StringBuilder gar = new StringBuilder();
        for (int[] i : maze) {
            // clear console
            System.out.print("\033[H\033[2J");
            System.out.flush();
            gar.append("| ");
            for (int j : i) {
                if (j == 0) {
                    gar.append("X "); // Add an extra space after 'X'
                } else if (j == 2) {
                    gar.append("@ "); // Add an extra space after '?'
                } else if (j == 3) {
                    gar.append("@ "); // Add an extra space after '?'
                } else if (j == 5) {
                    gar.append(ConsoleColors.PURPLE + "*" + ConsoleColors.RED + " "); // Add an extra space after colored 'X'
                } else if (j == 6) {
                    count++;
                    gar.append(ConsoleColors.GREEN + "O" + ConsoleColors.RED + " "); // Add an extra space after colored 'X'
                } else {
                    gar.append("  "); // Add two spaces for empty cells
                }
            }
            gar.append("|\n"); // Add a space before the vertical bar
        }
        gar.append("\nLength of solution: " + count);
        setCount(count);
        return gar.toString();
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
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
        rawMaze[start.y][start.x] = 2;
        rawMaze[end.y][end.x] = 3;
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