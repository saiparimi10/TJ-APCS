//  Name: Sai Parimi      
//  Date: 1/17/25
 
import java.io.*;
import java.util.*;
 
public class AuthorsNovels
{
   public static void main(String[] args) throws IOException
   {
      /*   test the AuthorEntry object  */
      AuthorEntry a = new AuthorEntry("Aaa");
      System.out.println("name: " + a.getName());
      System.out.println("novels: " + a.getNovels());
      System.out.println("toString(): " + a);
      AuthorEntry b = new AuthorEntry("bbb", "y");
      System.out.println("name: " + b.getName());
      b.addNovel("z");
      b.addNovel("y");
      b.addNovel("x");
      System.out.println("novels: " + b.getNovels());
      System.out.println("toString(): " + b);
      System.out.println(b.compareTo(a));   // 1
      System.out.println(a.compareTo(b));   // -1
      System.out.println("AAA".compareTo(a.getName())); // 0
      
      /*  start the lab  */
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      AuthorList authors = readAndMakeTheList(inputFile);
      String outFileName = "authorsNovelsOut.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      outputFile.println( authors.toString() );
      inputFile.close(); 					
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static AuthorList readAndMakeTheList(Scanner inputFile)
   {
      AuthorList theList = new AuthorList();
      while(inputFile.hasNextLine())
      {
         theList.readOneLine(inputFile.nextLine());
      }
      return theList;
   }
}
 
class AuthorList extends LinkedList<AuthorEntry>
{
    /**   you get a LinkedList for free   **/
   public AuthorList()
   {
      super();
   }
     /** extracts the author and book from oneLine.
         calls addAuthorEntry      
      */
   public void readOneLine(String oneLine) 
   {  
      String[] p = oneLine.split(", ");
      addAuthorEntry(p[0], p[1]);
   }
   
    /** use a listIterator.  Needs to call .previous() 
          either inserts a new AuthorEntry object, or 
          adds a novel to a previous AuthorEntry object,
          in alphabetic order
    */
   public void addAuthorEntry(String name, String book)
   {
      ListIterator<AuthorEntry> iterator = this.listIterator();
      AuthorEntry a = new AuthorEntry(name, book);
      while (iterator.hasNext()) {
         AuthorEntry current = iterator.next();
         if (current.getName().compareTo(a.getName()) == 0) {
            current.addNovel(book);
            return;
         } else if (a.getName().compareTo(current.getName()) < 0) {
            iterator.previous();
            iterator.add(a);
            return;
         }
      }
      iterator.add(a);
   }
   
   public String toString()
   {  
      String res = "";
      for(AuthorEntry a : this) 
         res += a.toString() + "\n";
      return res;
   }
 
}
 
class AuthorEntry implements Comparable<AuthorEntry>
{
   //fields
   private String name;
   private LinkedList<String> novels;
   
   //two constructors. argument n may be in lowercase. 
   public AuthorEntry(String n)
   {
      name = n.toUpperCase();
      novels = new LinkedList<>();
   }
   public AuthorEntry(String n, String book)
   {
     name = n.toUpperCase();
     novels = new LinkedList<>(); 
     novels.add(book);
   }
   
   /**  appends book to novels, but only if it is not already in that list.    
       */
   public void addNovel(String book)
   {
      if(!novels.contains(book))
         novels.add(book);
   }
   
   /** two standard accessor methods  */
   public String getName() {
      return name;
   }
   
   public LinkedList<String> getNovels() {
      return novels;
   }
   
        
   /**  pre:  name is not an empty string.  novels might be an empty LinkedList.
       uses:  either a for-each loop or an iterator
       post:  returns a string representation of this AuthorEntry in the format as 
              shown on each line of the output file.  
     */
    public String toString() {
      String res = this.name;
      if (!novels.isEmpty()) {
          res += ": [";
          for (int i = novels.size()-1; i >= 0; i--) {
              res += novels.get(i) + ", ";
          }
          if (res.endsWith(", ")) {
              res = res.substring(0, res.length()-2);
          }
          res += "]";
          String newRes = "]";
          for (int i = res.length()-2; i >= 0; i--) {
              newRes = res.charAt(i) + newRes;
          }
          res = newRes;
      }
      return res;
  }
  
 
   
   public int compareTo(AuthorEntry other) 
   {
      return this.name.compareTo(other.name);
   }
}
 
/**********************  SAMPLE RUN  ********************************
 name: AAA
 novels: []
 toString(): AAA
 name: BBB
 novels: [y, z, x]
 toString(): BBB: y, z, x
 1
 -1
 0
 
 Enter input file name: infile2
 Done.
 
 **********************************************************/
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
    */
