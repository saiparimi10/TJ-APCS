// Name: Sai Parimi
// Date: 9/30/24
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
 
public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery_short.txt");
      //File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      //uncomment to see if you have read the data properly
      //for (int i = 0; i < cemetery.length; i++) 
         //System.out.println(cemetery[i]);
         
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery: ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge()); 
      //you may create other testing cases here
     
          
   }
   
   /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      try {
         int count = 0;
         Scanner infile = new Scanner(f);
         while(infile.hasNextLine()) {
            count++;
            infile.nextLine(); // moves the pointer.
         }
         infile.close();
         return count;
      } catch (FileNotFoundException e) {
         return 0;
      }
   }
 
   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray(File f, int num) {
      try {
         Person[] people = new Person[num];
         Scanner infile = new Scanner(f);
         for(int i = 0; i < num; i++) {
            people[i] = makeObjects(infile.nextLine());
         }
         infile.close();
         return people;
      } catch (FileNotFoundException e) {
         return null;
      }
   }
 
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String entry)
   {
      String[] parts = entry.split("\\s++");
      String age = entry.substring(37,41);
      String burialDate = entry.substring(24,36);
      String name = entry.substring(0,24);
   
      return new Person(name, burialDate, age);
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      if (arr == null || arr.length == 0 )
         return -1;
      int minimumAgeIndex = 0;
   
      for (int i = 1; i < arr.length; i++) {
         if (arr[i].getAge() < arr[minimumAgeIndex].getAge()) {
            minimumAgeIndex = i;
         }
      }
      return minimumAgeIndex;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      if (arr == null || arr.length == 0 )
         return -1;
      int maximumAgeIndex = 0;
   
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].getAge() > arr[maximumAgeIndex].getAge()) {
            maximumAgeIndex = i;
         }
      }
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].getAge() > arr[maximumAgeIndex].getAge()) {
            maximumAgeIndex = i;
         }
      }
      return maximumAgeIndex;
   }        
} 
 
class Person
{
   //constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   private String myName;
   private String myBurialDate;
   private double myAge;
      
   /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String name, String burialDate, String age)
   {
      myName = name;
      myBurialDate = burialDate;
      myAge = calculateAge(age);
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */
   public String getName() { 
      return myName; 
   }
   public double getAge() { 
      return myAge;
   }
   public String getBurialDate() { 
      return myBurialDate; 
   }
 
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
{
   a = a.trim();
   double result;
 
   if(a.endsWith("w")) {
      a = a.replace("w", ""); 
      result = Double.parseDouble(a) * 7.0 / 365.0;
   }
   else if(a.endsWith("d")) {
      a = a.replace("d", ""); 
      result = Double.parseDouble(a) / 365.0;
   }
   else {
      System.out.println(a.substring(a.length()-1));
      result = Double.parseDouble(a);
   }
   return Math.round(result * 10000.0) / 10000.0;
}
 
   // override To String()
   @Override
   public String toString() {
      return getName() + getBurialDate() + getAge();
   }
}
