package com.noahheaton.menus;

import com.noahheaton.Maze;
import com.noahheaton.Solve;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ScreenSaver {
    private static int speed;
    public static void run(int size, int speed) throws FileNotFoundException {
        setSpeed(speed);

        //Sleep one second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        while (true) {


            Maze m = new Maze(size);
            Maze e = new Maze(size);
            Maze f = new Maze(size);
            m.generateMaze();
            //
            f = m.clone();

            e.setMaze(m.getRawMaze());




            // Starting a star in 1 minute
            System.out.println("Starting A* in 1 second...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            com.noahheaton.aStar.aMain.main(f);
            System.out.println("Starting BFS in 5 seconds...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //f.setMaze(m.getRawMaze());
            //com.noahheaton.aStar.aMain.main(m);
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
            // 5 second until A*


            //com.noahheaton.aStar.aMain.run(size, f, f);


            System.out.println("Tiles Scanned\nBFS: " + s.getBfsCount() + " DFS: " + s2.getDfsCount() + " A*: " + (com.noahheaton.aStar.aStarTrack.getMoves() - 2));
            System.out.println("Solution lengths\nBFS: " + m.getCount() + " DFS: " + e.getCount() + " A*: " + (com.noahheaton.aStar.aStarTrack.getSolution() - 2));
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
