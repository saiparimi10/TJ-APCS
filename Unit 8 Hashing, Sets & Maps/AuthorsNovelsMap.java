	// name: Sai Parimi
// date: 3/6/25
 
import java.io.*;
import java.util.*;
 
public class AuthorsNovelsMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
   
      AuthorsMap authors = readAndMakeTheList(inputFile);
      PrintWriter outputFile = new PrintWriter(new FileWriter("authorsNovelsOut.txt"));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("File created.");
   }
   
   public static AuthorsMap readAndMakeTheList(Scanner inputFile)
   {
      AuthorsMap theAuthors = new AuthorsMap();
      while(inputFile.hasNextLine())
      {
         theAuthors.readOneLine(inputFile.nextLine());
      }
      return theAuthors;
   }
}
 
class AuthorsMap extends TreeMap<String, Set<String>>
{
  /**   when you extend a class, the constructor is optional  **/
 
    
   /** extracts the author and book from oneLine.
       calls addAuthorOrNovel      
      */
   public void readOneLine(String oneLine) 
   { 
      String[] part = oneLine.split(",");
      addAuthorOrNovel(part[0], part[1]);
   }
   
   /**  either inserts a new Author mapping, or updates a previous Author mapping
        */
   public void addAuthorOrNovel(String name, String book)
   {
      name = name.toUpperCase();
      if(!this.containsKey(name)) {
         Set<String> books = new TreeSet<String>();
         books.add(book);
         this.put(name, books);
      }
      Set<String> books = this.get(name);
      books.add(book);
   }
   
   public String toString()
   {
      String re = "";
      for(String author : this.keySet()) {
         re += author + ":";
         Set<String> books = this.get(author);
         for(String book : books) {
            re += " " + book + ",";
         }
         re = re.substring(0, re.length()-1) + "\n";
      }
      return re;
   }
}
