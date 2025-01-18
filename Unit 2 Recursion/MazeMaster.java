// Name: Sai Parimi
// Date: 10/22/24
 
import java.util.*;
import java.io.*;
 
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next()+".txt");   //append the .txt here
      // Maze m = new Maze();    //extension
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, return false.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, return false.");
      System.out.println("6: Mark only the correct path and list the steps.\n\tIf no path exists, return false.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   } 
}
 
class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
    * Do not comment it out.  Do not delete it.
	 */
   public Maze()
   {
      maze = null;
      startRow = -1;
      startCol = -1;
   }
	
	/** 
	 * Constructor.
    * Creates a "deep copy" of the array.
    * The autograder uses this constructor.
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)    //location of start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
 	/* 
	 * Write this one-arg constructor.
    * the filename already has ".txt"
    * Use a try-catch block.
	 * Use next(), not nextLine() 
    * Search the maze and save the location of 'S' 
	 */
   public Maze(String filename)    
   {
      try {
         Scanner infile = new Scanner(new File(filename));
         maze = new char[infile.nextInt()][infile.nextInt()];
         for (int r = 0; r < maze.length; r++) {
            String line = infile.next();
            for (int c = 0; c < maze[0].length; c++) {
               maze[r][c] = line.charAt(c);
               if (maze[r][c] == START) {
                  startRow = r;
                  startCol = c;
               }
            }
         }
      } catch(FileNotFoundException e) {}
 
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            markAll(startRow, startCol);
            break;
         case 2:
            int count = markAllAndCountRecursions(startRow, startCol);
            System.out.println("Number of recursions = " + count);
            break;
         case 3:
         case 4: 
            if( markTheCorrectPath(startRow, startCol) )
               System.out.println("Path found!"); 
            else           //use mazeNoPath
               System.out.println("No path exists."); 
            break;
         case 5:
            if( markCorrectPathAndCountSteps(startRow, startCol, 0) )
               System.out.println("Path found!"); 
            else           //use mazeNoPath
               System.out.println("No path exists."); 
            break;
         case 6: 
            if( markCorrectPathAndListSteps(startRow, startCol, "") )
               System.out.println("Path found!"); 
            else        //use mazeNoPath 
               System.out.println("No path exists.");
            break;
         default:
            System.out.println("File not found");  
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {
      if (r<0 || r>= maze.length || c< 0 || c>= maze[0].length || (maze[r][c] != DOT && maze[r][c] != START)) {
         return; 
      }
      char saved = maze[r][c];
      maze[r][c] = PATH;
      markAll(r+1, c);
      markAll(r-1, c);
      markAll(r,c+1);
      markAll(r,c-1);
      if (saved == START) {
         maze[r][c] = saved;
      }
   }
 
	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {
      if (r<0 || r>= maze.length || c<0 || c>= maze[0].length || (maze[r][c] != DOT && maze[r][c]!= START && maze[r][c]!=EXIT)) {
         return 1; 
      }
      maze[r][c] = PATH;
      return 1 + markAllAndCountRecursions(r+1, c) + markAllAndCountRecursions(r-1, c) + markAllAndCountRecursions(r, c+1) + markAllAndCountRecursions(r, c-1);
      
 
   }
 
/* 
 * From handout, #3 and #4
 * Solve the maze, OR the booleans, and mark the path through it with an asterisk
 *  Recur until you find E, then mark the path, return true.
 *  If no path is found, return false.
 */	
   public boolean markTheCorrectPath(int r, int c)
   {
      if (r<0||r>=maze.length||c<0||c>=maze[0].length){
         return false;
      }
      if (r<0 || r>= maze.length || c<0 || c>= maze[0].length || maze[r][c] != DOT || maze[r][c] != START || maze[r][c] != EXIT) {
         if(maze[r][c] == EXIT) {
            return true;
         }
         if (maze[r][c] == START || maze[r][c] == DOT) {
            char saved = maze[r][c];
            maze[r][c] = TEMP;
            boolean check = markTheCorrectPath(r+1,c) || markTheCorrectPath(r-1,c) || markTheCorrectPath(r,c+1) || markTheCorrectPath(r,c-1);
            if (check == false) {
               maze[r][c] = saved;
               return false;
            }
            if (check == true) {
               if(r == startRow && c == startCol) {
                  maze[r][c] = START;
                  return true;
               }
               maze[r][c] = PATH;
               return true;
            }
         }
      }
      return false;
  }
 
   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, return false.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      if (r<0||r>=maze.length||c<0||c>=maze[0].length){
         return false;
      }
      if (r<0 || r>= maze.length || c<0 || c>= maze[0].length || maze[r][c] != DOT || maze[r][c] != START || maze[r][c] != EXIT) {
         if(maze[r][c] == EXIT) {
            count++;
            return true;
         }
         if (maze[r][c] == START || maze[r][c] == DOT) {
            char saved = maze[r][c];
            maze[r][c] = TEMP;
            boolean check = markTheCorrectPath(r+1,c) || markTheCorrectPath(r-1,c) || markTheCorrectPath(r,c+1) || markTheCorrectPath(r,c-1);
            if (check == false) {
               maze[r][c] = saved;
               return false;
            }
            if (check == true) {
               if(r == startRow && c == startCol) {
                  maze[r][c] = START;
                  count++;
                  return true;
               }
               maze[r][c] = PATH;
               count++;
               return true;
            }
         }
      }
      return false; 
   }
   
 /*   #6.	Solve the maze, mark the path, list the (r,c) steps.
    */
   public boolean markCorrectPathAndListSteps(int r, int c, String steps)
   {
      if (r<0 || r>= maze.length || c<0 || c>= maze[0].length || maze[r][c] == WALL || maze[r][c] == TEMP || maze[r][c] == PATH) {
         return false; 
      }
      if(maze[r][c] == EXIT) {
         System.out.print("(" + r + "," + c + ")");
         return true;
      }
      maze[r][c] = PATH;
      if (markCorrectPathAndListSteps(r+1,c, PATH + "U") || markCorrectPathAndListSteps(r-1, c, PATH + "D") || markCorrectPathAndListSteps(r, c+1, PATH + "L") || markCorrectPathAndListSteps(r, c-1, PATH + "R")) {
         return true;
      }
      maze[r][c] = TEMP;
      return false;
   }
}
 
   /*****************************************
 
      ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 3
 Path found!
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 5
 Number of steps = 14
 Path found!
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 6
 (5,0) (5,1) (4,1) (3,1) (3,2) (3,3) (4,3) (5,3) (6,3) (6,4) (6,5) (6,6) (5,6) (4,6) (4,7)
 Path found!
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 **************************************/
