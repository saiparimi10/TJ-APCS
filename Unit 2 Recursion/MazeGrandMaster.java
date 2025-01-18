// Name: Sai Parimi
// Date: 10/31/24
 
import java.util.*;
import java.io.*;
 
public class MazeGrandMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next()+".txt");  //append the .txt 
      m.display();      
      
      System.out.println("Options: ");
      System.out.println("1: Length of the shortest path\n\tIf no path exists, say so.");
      System.out.println("2: Length of the shortest path\n\tList the shortest path\n\tDisplay the shortest path\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      
      m.solve(sc.nextInt());
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
  
	
  /** 
	 * Constructor.
    * Creates a "deep copy" of the array.
    * We use this in Codepost. 
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
  /** 
	 * Write this one-arg constructor.
    * Use a try-catch block.
	 * Use next(), not nextLine() 
    * Search the maze to find the location of 'S' 
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
            int shortestPath = findShortestLengthPath(startRow, startCol);
            if( shortestPath < 999 )
               System.out.println("Shortest path is " + shortestPath);
            else
               System.out.println("No path exists."); 
            display();
            break;
            
         case 2:
            String strShortestPath = findShortestPath(startRow, startCol);
            if( strShortestPath.length()!=0 )
            {
               System.out.println("Shortest length path is: " + getPathLength(strShortestPath));
               System.out.println("Shortest path is: " + strShortestPath);
               markPath(strShortestPath);
               display();  //display solved maze
            }
            else
               System.out.println("No path exists."); 
            break;
         default:
            System.out.println("File not found");   
      }
   }
   
   
 /*  MazeGrandMaster 1   
     recur until you find E, then return the shortest path
     returns 999 if it fails
     precondition: Start can't match with Exit
 */ 
   public int findShortestLengthPath(int r, int c)
   {
      if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == WALL || maze[r][c] == TEMP) {
         return 999;
      }
      if (maze[r][c] == EXIT) {
         return 0;
      }
      char saved = maze[r][c];
      maze[r][c] = TEMP;
      int up = findShortestLengthPath(r-1, c);
      int right = findShortestLengthPath(r, c+1);
      int down = findShortestLengthPath(r+1, c);
      int left = findShortestLengthPath(r, c-1);
      int minLen = Math.min(up, Math.min(right, Math.min(down, left)));
      maze[r][c] = saved;
      return 1 + minLen;
   }  
   
/*  MazeGrandMaster 2   
    recur until you find E, then build the path with (r,c) locations
    and the number of steps, e.g. ((5,0),10),((5,1),9),((6,1),8),((6,2),7),
    ((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 
    as you build, choose the shortest path at each step
    returns empty String if there is no path
    precondition: Start can't match with Exit
 */
   public String findShortestPath(int r, int c)
   {
      if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == WALL || maze[r][c] == TEMP) {
         return "";
      }
      if (maze[r][c] == EXIT) { return "(("+r+","+c+"),0)";}
 
      char saved = maze[r][c];
      maze[r][c] = TEMP;
      
      String up = findShortestPath(r-1, c);
      String right = findShortestPath(r, c+1);
      String down = findShortestPath(r+1, c);
      String left = findShortestPath(r, c-1);
 
      String minPath = up;
      if (minPath.length() == 0 || right.length() > 0 && minPath.length() > right.length()) {
         minPath = right;
      }
      if (minPath.length() == 0 || down.length() > 0 && minPath.length() > down.length()) {
         minPath = down;
      }
      if (minPath.length() == 0 || left.length() > 0 && minPath.length() > left.length()) {
         minPath = left;
      }
      maze[r][c] = saved;
      if(minPath.length() == 0) {
         return "";
      }
      int minLen = minPath.split(",").length / 3;
      return "((" + r+ "," + c + ")," + minLen + ")," + minPath;
   }	
 
	/** MazeGrandMaster 2 
       returns the length, i.e., third number when the format of the strPath is 
            "((3,4),10),((3,5),9),..."
       returns 999 if the string is empty
       precondition: strPath is either empty or follows the format above
    */
   public int getPathLength(String strPath)
   {
      if (strPath.length() == 0) {
         return 999;
      }
      return strPath.split(",").length /3 -1;
   } 
 
  /** MazeGrandMaster 2 
      recursive method that takes a String created by findShortestPath(r, c)     
      in the form of ((5,0),10),((5,1),9),((6,1),8),((6,2),7),
              ((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),
              ((4,7),0) and marks the actual path in the maze 
      precondition: the String is either an empty String or one that    
                    has the format shown above
                    the (r,c) must be correct for this method to work 
   */
   public void markPath(String strPath)
   {
      if(strPath.equals("")) {
         return;
      }
      String[] nums = strPath.split(",");
      for (int i = 0; i < nums.length; i+=3) {
         int r = Integer.parseInt(nums[i].substring(2));
         int c = Integer.parseInt(nums[i+1].substring(0, nums[i+1].indexOf(")")));
         if (maze[r][c] == DOT) {
            maze[r][c] = PATH;
         } 
      }
      
   }
}
 
 
   /*************************************************************
 ----jGRASP exec: java MazeGrandMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 W......W
 WWWWWWWW
 
 Options: 
 1: Length of the shortest path
 	If no path exists, say so.
 2: Length of the shortest path
 	List the shortest path
 	Display the shortest path
 	If no path exists, say so.
 Please make a selection: 1
 Shortest path is 10
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 W......W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
 ******************************************************************/
 /**************************************************************
      ----jGRASP exec: java MazeGrandMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 W......W
 WWWWWWWW
 
 Options: 
 1: Length of the shortest path
 	If no path exists, say so.
 2: Length of the shortest path
 	List the shortest path
 	Display the shortest path
 	If no path exists, say so.
 Please make a selection: 2
 Shortest length path is: 10
 Shortest path is: ((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW*E
 S*W.WW*W
 W******W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  
  ******************************************/
