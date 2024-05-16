import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Maze mazeGenerator = new Maze(30);
        //mazeGenerator.generateMaze();
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the size of the maze: ");
        int size = kb.nextInt();
        Maze maze = new Maze(size);
        maze.generateMaze();
        //SolvableMaze s = new SolvableMaze(maze);
        //s.solveMaze();
        //maze.addGoal(s.getStart(), s.getEnd());

        //System.out.println("RAW MAZE\n" + maze.getRawMaze());
        System.out.println("SYMBOLIC MAZE\n" + maze.getFancyMaze());
    }
}