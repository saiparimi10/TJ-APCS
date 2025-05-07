// Name: Sai Parimi
 // Date: 3/4/25
 
/* 
   Assignment:  This hashing program results in collisions.
   You are to implement three different collision schemes: linear 
   probing, rehashing, and chaining.  Then implement a search 
   algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while(true)
      {
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
         if(itemNumber == -1)
            System.exit(0);
         String key = "Item" + itemNumber; 
      System.out.println("Searching for " + key);
      boolean found = table.contains(key); 
      } 
   }
}
 
/*********************************************/
interface Hashtable
{
   void add(Object obj);
   boolean contains(Object obj);
   Object[] getArray();   //for Codepost
}
/***************************************************/ 
 
class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
  
   public HashtableLinearProbe(int size)//constructor
   {
      array = new Object[size];
                            
   }   
   
   public Object[] getArray()   //for Codepost
   {
      return array;
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null) //if empty, insert
      {
         array[index] = obj;  
      }
      else //collision
       {
         System.out.println(obj + "\t" + code + "\tCollision at index "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\tindex" + index);
     }
   }  
   
   public int linearProbe(int index)
   {      
      //be sure to wrap around the array.
      while(array[(++index) % array.length] != null);
      return index % array.length;
 
   }
   
   public boolean contains(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return true;
         }
         else //search for it in a linear probe manner
         {
           index = (index+1) % array.length; 
         }
      }
      System.out.println("\t\t\tNot found!");
      return false;  //not found
   }
}
 
/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant;  
   
   public HashtableRehash(int size) //constructor
   {
        array = new Object[size];
        // find a constant that is relatively prime to the size of the array
        constant = 2;
        while(gcd(constant, size) != 1) {
         constant++; 
        }               
   }
   
   private int gcd(int a, int b) {
      if(a==0) return b;
      return gcd(b%a, a);
   }
   
   public Object[] getArray()   //for Codepost
   {
      return array;
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //if empty, insert
      {
         array[index] = obj;
         
      }
      else //collision
   {
         System.out.println(obj + "\t" + code +"\tCollision at index "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\tindex " + index);
    }
   }  
   
   public int rehash(int index)
   {
      while(array[index] != null) {
         index = (index + constant) % array.length;
      } 
      return index; 
   }
   
   public boolean contains(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return true;
         }
         else //search for it in a rehashing manner
         {
           index = (index + constant) % array.length; 
         }
      }
      System.out.println("\t\t\tNot found!");
      return false;  //not found
   }
}
 
/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   
   public HashtableChaining(int size)
   {
      //instantiate the array
      //instantiate the LinkedLists
      array = new LinkedList[size]; 
      for(int i = 0; i < size; i++) {
         array[i] = new LinkedList();
      }                     
   }
   
   public Object[] getArray()   //for Codepost
   {
      return array;
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at index " +index + ": "+ array[index]);
 }  
   
   public boolean contains(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         if(array[index].getFirst().equals(obj))  //found it
         {
            return true;
         }
         else //search for it in a chaining manner
         {
            for(int i = 1; i < array[index].size(); i++) {
               if(array[index].get(i).equals(obj)) {
                  return true;
               }
            }
         }
      }
      System.out.println("\t\t\tNot found!");
      return false;  //not found
   }
}
