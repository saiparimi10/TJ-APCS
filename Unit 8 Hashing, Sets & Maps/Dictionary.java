// Name: Sai Parimi
// Date: 3/10/25
 
import java.io.*;
import java.util.*;
 
public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      PrintWriter outputFile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         outputFile = new PrintWriter(new FileWriter("dictionaryOutput.txt"));
      }
      catch(Exception e)
      {
         System.out.println( e );
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      outputFile.println("ENGLISH TO SPANISH");
      outputFile.println(display(eng2spn));
   
      Map<String, Set<String>>spn2eng = reverse(eng2spn);
      outputFile.println("SPANISH TO ENGLISH");
      outputFile.println(display(spn2eng));
      outputFile.close();
      
      System.out.println("File created.");
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> dict = new TreeMap<String, Set<String>>();
      while(infile.hasNext()) {
         String eng = infile.next();
         String spa = infile.next();
         add(dict, eng, spa);
      }
      return dict;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      Set<String> translations;
      if(dictionary.containsKey(word)) {
         translations = dictionary.get(word);
      }
      else {
         translations = new TreeSet<>();
      }
      translations.add(translation);
      dictionary.put(word, translations);
   }
   
   public static String display(Map<String, Set<String>> m)
   {
      String st = "";
      for(String s : m.keySet()) {
         st += s + " " + m.get(s) + "\n";
      }
      return st;
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> rev = new TreeMap<>();
      for(Map.Entry<String, Set<String>> entry : dictionary.entrySet()) {
         for(String translation : entry.getValue()) {
            add(rev, translation, entry.getKey());
         }
      }
      return rev;
   }
}
