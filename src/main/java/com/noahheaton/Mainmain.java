package com.noahheaton;
import com.noahheaton.menus.CSVFilesOutput;
import com.noahheaton.menus.mainSolve;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class Mainmain {
    private static int speed;
    public static void main(String[] args) throws FileNotFoundException {

        //Sleep one second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //mainSolve.run();
        //CSVFilesOutput.run();
        int size = Integer.parseInt(args[0]);
        setSpeed(Integer.parseInt(args[1]));
        //int turns = Integer.parseInt(args[2]);
        Scanner kb = new Scanner(System.in);
        while (true) {


            Maze m = new Maze(size);
            Maze e = new Maze(size);

            m.generateMaze();
            e.setMaze(m.getRawMaze());
            Solve s = new Solve(m, m.getStart(), m.getEnd());
            Solve s2 = new Solve(e, m.getStart(), m.getEnd());
            s.stepBfs(m, m.getStart(), m.getEnd(), new boolean[size][size]);
            System.out.println(m.getFancyMaze(m.getMaze()));
            //System.out.println("Press anything to continue to DFS");
            //kb.next();
            // sleep 5 seconds
            System.out.println("\n\nStarting DFS in 5 seconds...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            s2.stepDfs(e, m.getStart(), m.getEnd(), new boolean[size][size]);
            System.out.println(e.getFancyMaze(e.getMaze()));
            System.out.println("Tiles Scanned\nBFS: " + s.getBfsCount() + " DFS: " + s2.getDfsCount());
            System.out.println("Solution lengths\nBFS: " + m.getCount() + " DFS: " + e.getCount());
            // sleep 5 seconds
            System.out.println("\n\nStarting again in 5 seconds...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }



    }
    public static void setSpeed(int s){
        speed = s;
    }
    public static int getSpeed(){
        return speed;
    }
}
