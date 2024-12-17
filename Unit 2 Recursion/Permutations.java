// Name: Sai Parimi
// Date: 10/11/24
  
import java.util.*;
public class Permutations
{
   public static int count = 0;
    
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      leftRight("", n);  
              
      //oddDigits("", n);
      
      //superprime(n);
      //if(count==0)
         //System.out.println("no superprimes");
      //else
         // System.out.println("Count is "+count);
   }
   
    /**
     * Builds all the permutations of a string of length n containing Ls and Rs
     * @param s A string 
     * @param n An postive int representing the length of the string
     */
   public static void leftRight(String s, int n)
   {
      if(s.length() == n) {
         System.out.println(s);
      } else {
         leftRight(s+"L",n);
         leftRight(s+"R",n);
      }
 
   }
   
    /**
     * Builds all the permutations of a string of length n containing odd digits
     * @param s A string 
     * @param n A postive int representing the length of the string
     */
   public static void oddDigits(String s, int n)
   {
      if(s.length() == n) {
         System.out.println(s);
      } else {
         for(int k = 1; k < 10; k+= 2)
      oddDigits(s+k,n);
           
      }
   }
      
    /**
     * Builds all combinations of a n-digit number whose value is a superprime
     * @param n A positive int representing the desired length of superprimes  
     */
   public static void superprime(int n)
   {
      recur(2, n); 
      recur(3, n); 
      recur(5, n);
      recur(7, n);
   }
 
    /**
     * Recursive helper method for superprime
     * @param k The possible superprime
     * @param n A positive int representing the desired length of superprimes
     */
   private static void recur(int k, int n)
   {
      if (isPrime(k) ) {
         if (String.valueOf(k).length() == n) {
            System.out.println(k);
            count++;
         } else {
            for(int l = 1; l < 10; l+= 2)
               recur(k*10 + l, n);
           }
      }
   }
 
    /**
     * Determines if the parameter is a prime number.
     * @param n An int.
     * @return true if prime, false otherwise.
     */
   public static boolean isPrime(int n)
   {
      for (int j = 2; j < n-1; j++) {
         if (n%j == 0) {
            return false;
         }
      }
      return true;
   }
}
