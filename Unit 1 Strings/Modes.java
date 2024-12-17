// Name: Sai Parimi
// Date: September 9th, 2024
 
public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      //test with other values in the tally
      System.out.println(toString(tally));
      int[] modes = calculateModes(tally);
      System.out.println(toString(modes));
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
         sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 1; k <= sum; k++)
         System.out.println(k + "\t\t" + kthDataValue(tally, k));
   }
     
  /**
   * precondition: tally.length > 0
   * postcondition: returns an int array that contains the modes(s);
   *                the array's length equals the number of modes.
   */
   public static int[] calculateModes(int[] tally)
   {

      int maxTally = findMax(tally);
      int count = 0;
      int[] modes;
      for (int k = 0; k < tally.length; k++) {
         if (tally[k] == maxTally) {
            count++;
         }
      }
      if(count == tally.length) {
         modes = new int[0];
      }
      else {
         modes = new int[count];
         count = 0;
         for (int x = 0; x < tally.length; x++) {
            if(tally[x] == maxTally) {
               modes[count] = x;
               count++;
            }
         } 
      } 
      return modes;

   }
     
  /**  
   * precondition:  tally.length > 0
   * 	             0 < k <= total number of values in data collection
   * postcondition: returns the kth value in the data collection
   *                represented by tally
   */
   public static int kthDataValue(int[] tally, int k)
   {

      int count = 0;
      for (int x = 0; x < tally.length; x++) {
            count += tally[x];
            if (count >= k) {
                return x;
            }
        }
      return -1;

    }
     
  /**
   * precondition:  nums.length > 0
   * postcondition: returns the maximal value in nums
   */
   public static int findMax(int[] nums)
   {

      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];

   }
   
   public static String toString(int[] arg)
   {

      if(arg.length == 0)
         return("[]");
      String s = "[";
      for(int k = 0; k < arg.length - 1; k++)
         s += arg[k] + ",";
      s += arg[arg.length - 1] + "]";
      return s;

   }  
}
