// Name: Sai Parimi 
// Date: 9/27/24
import java.io.*;
import java.util.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
       part_1_using_pig();
       // part_2_using_piglatenizeFile();
      
       /* extension only    
       String pigLatin = pig("What!?");
       System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
       pigLatin = pig("{(Hello!)}");
       System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
       pigLatin = pig("\"McDonald???\"");
       System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???" */
   }
 
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in); //input from the keyboard
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();     //reads up to white space
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }
 
   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
 
   public static String pig(String s) {
     
       if (s.length() == 0)
           return "";
       
       String startPunct = "";
       String endPunct = "";
       int start = 0;
       
       while (start < s.length() && punct.indexOf(s.charAt(start)) != -1) {
           startPunct += s.charAt(start);
           start++;
       }
       int end = s.length();
       
       while (end > start && punct.indexOf(s.charAt(end - 1)) != -1) {
           endPunct = s.charAt(end - 1) + endPunct;
           end--;
       }
       String word = s.substring(start, end);
       boolean wasCapital = Character.isUpperCase(word.charAt(0));
   
       if (word.length() == 0)
           return startPunct + endPunct + "NO VOWEL";
       
       int index = -1;
       boolean firstVowel = false;
       
       for (int i = 0; i < word.length(); i++) {
           char ch = Character.toLowerCase(word.charAt(i));
   
           if (ch == 'q' && i < word.length() - 1 && Character.toLowerCase(word.charAt(i + 1)) == 'u') {
               index = i + 2;
               break;
           }
   
           if (ch == 'y' && i != 0) {
               index = i;
               break;
           }
   
           if ((ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')) {
               index = i;
               if (i == 0) {
                   firstVowel = true;
               }
               break;
           }
       }
   
       String pigLatinWord = "";
       if (index == -1) {
           return"NO VOWEL";
       } else if (firstVowel) {
           pigLatinWord = word.substring(index) + word.substring(0, index) + "way";
       } else {
           pigLatinWord = word.substring(index) + word.substring(0, index) + "ay";
       }
   
       
       if (wasCapital) {
           
           pigLatinWord = Character.toUpperCase(pigLatinWord.charAt(0)) + pigLatinWord.substring(1);
   
          
           if (index > 0 && index < word.length()) {
               char firstMovedLetter = Character.toLowerCase(word.charAt(0));
               pigLatinWord = pigLatinWord.substring(0, word.length() - index) + firstMovedLetter + pigLatinWord.substring(word.length() - index + 1);
           }
       } else {
           pigLatinWord = pigLatinWord.toLowerCase();
       }
   
       return startPunct + pigLatinWord + endPunct;
     
   }
 
 
 
 
   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }
 
/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
     
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNextLine())
      {
        String line = infile.nextLine();
        String outline = "";
        String[] words = line.split(" ");
        for(String word : words) {
            outline += pig(word) + " ";
        }
        outfile.println(outline);
      } 
   
      outfile.close();
      infile.close();
     
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
     
      if (s.length() == 0) 
        return "";
 
    String startPunct = "";
    String endPunct = "";
    
    int start = 0;
    int end = s.length();
 
    while (start < s.length() && punct.indexOf(s.charAt(start)) != -1) {
        startPunct += s.charAt(start);
        start++;
    }
 
    while (end > start && punct.indexOf(s.charAt(end - 1)) != -1) {
        endPunct = s.charAt(end - 1) + endPunct;
        end--;
    }
    String word = s.substring(start, end);
    String reversedWord = "";
    
    for (int i = word.length()-1; i>=0; i--) {
        reversedWord += word.charAt(i);
    }
 
    if (Character.isUpperCase(word.charAt(0))) {
        reversedWord = Character.toUpperCase(reversedWord.charAt(0)) + reversedWord.substring(1,reversedWord.length()-1) + Character.toLowerCase(word.charAt(0));
    }
 
    return startPunct + reversedWord + endPunct; 
     
   }   
  
}
