// Name: Sai Parimi
// Date: 2/3/25
 
import java.util.*;
import java.io.*;
public class SeniorsFirst
{
   public static final double CHANCE_OF_CUSTOMER = 0.5; 
              
   public static void main(String[] args)
   {     
      PrintWriter outfile = setUpFile();      
      System.out.println("Seniors First Simulation! ");
      Scanner kb = new Scanner(System.in);
      System.out.print("How long, in minutes, should the simulation run? ");
      int duration = kb.nextInt();
      waitTimes(duration, outfile);  //run the simulation
      
      System.out.print("Simulation done.");
   } 
    
   public static PrintWriter setUpFile()
   {
      PrintWriter outfile = null; 
      try
      {
         outfile = new PrintWriter(new FileWriter("customerWaitTimes.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      return outfile;
   }
  
   public static void outfileCustomerServed(PrintWriter outfile, Customer c, int wait, int total)
   {   
      outfile.println("           Depart  "+ c + ", wait time was "+ wait + " minutes.  Total time so far: "+total+"\n");                    	
   }
   
   public static void outfileQueue(PrintWriter outfile, int min, PriorityQueue<Customer> pq)
   { 
      outfile.println("minute " + min + ". Serving " + pq.peek() +". Priority queue " + pq.toString() );
   }
  
   public static double calculateAverage(int totalMinutes, int customers)
   {
      return (int)(1.0 * totalMinutes/customers * 100)/100.0;
   }
   
   public static void waitTimes(int duration, PrintWriter outfile)
   {
        //cumulative variables
      int customers = 0;
      int customersCheckedOut = 0;
      String[] classes = new String[]{"Senior", "Junior", "Sophomor", "Freshman"};
      int[] served = new int[]{0,0,0,0};
      int[] longestWait = new int[]{0,0,0,0};
      int[] totalWait = new int[]{0,0,0,0};
      PriorityQueue<Customer> pq = new PriorityQueue<>();
      
      for (int min = 1; min <= duration; min++) {
         if (min <= duration && Math.random() < CHANCE_OF_CUSTOMER) {
            Customer newCustomer = new Customer(min);
            pq.add(newCustomer);
            customers++;
         }
         if (!pq.isEmpty()) {
            Customer current = pq.peek();
            if (current.getAtFront() == 0) {
               current.arrivedAtFront();
            }
            current.countDown();
            
            if (current.getAtFront() == 0) {
               pq.remove();
               int waitTime = min - current.getArrivalTime();
               int classIndex = current.getMyClass();
               served[classIndex]++;
               totalWait[classIndex] += waitTime;
               longestWait[classIndex] = Math.max(longestWait[classIndex], waitTime);
               outfileCustomerServed(outfile, current, waitTime, totalWait[classIndex]);
            }
         }
         outfileQueue(outfile, min, pq); 
      }
   
      
     /***************************************
           Write your code for the simulation.
           call outfileCustomerServed() to record the departure of a customer.
           call outfileQueue() to write the PriorityQueue to the file. 
           for duration minutes, allow students to join the queue.  Serve them.
           after duration minutes, no new students can join. Serve the students until the queue is empty. 
      **********************************/       
      
   
   
   
   
   /*   output the calculations to the file   */
      outfile.println("Customer\t\tTotal\t\tLongest\t\tAverage Wait");
      for(int x = 0; x < 4; x++)
      {
         outfile.println(classes[x] + "\t\t\t" + served[x] + "\t\t\t" + longestWait[x] + 
                                          "\t\t\t" + calculateAverage(totalWait[x], served[x]) );
      }
      outfile.close();  
   }
   
   static class Customer implements Comparable<Customer>      
   {
      private int arrivedAt;
      private int timeAtFront;
      private int clas;  //Senior=0, Junior =1, Sophomore=2, Freshman=3
      private boolean atFront;
      public Customer(int time)
      {
      arrivedAt = time;
      clas = (int) (Math.random() *4);
      timeAtFront = (int) (Math.random() * 5 + 2);
      atFront = false;
      }
      public int getArrivalTime()
      {
         return arrivedAt;
      }
      public void setAtFront()
      {
         atFront = true;
      }
      public int getAtFront()
      {
         return timeAtFront;   
      }
      public void arrivedAtFront()
      {
         setAtFront();   
      }
      public void countDown()
      {
         timeAtFront--;
      }
      public int getMyClass()
      {
         return clas;   
      }
      public int compareTo(Customer other)
      {
         if(this.atFront && !other.atFront) {
            return -1;
         } if(!this.atFront && other.atFront) {
            return 1;
         }
         return Integer.compare(this.clas, other.clas);
      }
      public String toString()
      {
         String[] classNames = {"Se", "Ju", "So", "Fr"};
         return getAtFront() + "-" + classNames[clas] + ":" + getArrivalTime();
      }
   }
}
 
/**************** Sample Run saved to a file **************************************
 minute 1: Serving null. Queue []
  minute 2: Serving null. Queue []
  minute 3: Serving 4-Customer:3. Queue [4-Customer:3]
  minute 4: Serving 3-Customer:3. Queue [3-Customer:3]
  minute 5: Serving 2-Customer:3. Queue [2-Customer:3]
  minute 6: Serving 1-Customer:3. Queue [1-Customer:3]
             Depart  0-Customer:3, wait time was 4 minutes.  Total time so far: 4
  
  minute 7: Serving null. Queue []
  minute 8: Serving null. Queue []
  minute 9: Serving 2-Customer:9. Queue [2-Customer:9]
  minute 10: Serving 1-Customer:9. Queue [1-Customer:9]
            Depart  0-Customer:9, wait time was 2 minutes.  Total time so far: 6
 
  minute 11: Serving 3-Customer:11. Queue [3-Customer:11]
  minute 12: Serving 2-Customer:11. Queue [2-Customer:11, 2-Customer:12]
  minute 13: Serving 1-Customer:11. Queue [1-Customer:11, 2-Customer:12, 3-Customer:13]
            Depart  0-Customer:11, wait time was 3 minutes.  Total time so far: 9
 
  minute 14: Serving 2-Customer:12. Queue [2-Customer:12, 3-Customer:13]
  minute 15: Serving 1-Customer:12. Queue [1-Customer:12, 3-Customer:13, 5-Customer:15]
            Depart  0-Customer:12, wait time was 5 minutes.  Total time so far: 14
 
  minute 17: Serving 3-Customer:13. Queue [3-Customer:13, 5-Customer:15]
  minute 18: Serving 2-Customer:13. Queue [2-Customer:13, 5-Customer:15]
  minute 19: Serving 1-Customer:13. Queue [1-Customer:13, 5-Customer:15]
            Depart  0-Customer:13, wait time was 7 minutes.  Total time so far: 21
 
  minute 20: Serving 5-Customer:15. Queue [5-Customer:15]
  minute 21: Serving 4-Customer:15. Queue [4-Customer:15]
  minute 22: Serving 3-Customer:15. Queue [3-Customer:15]
  minute 23: Serving 2-Customer:15. Queue [2-Customer:15]
  minute 24: Serving 1-Customer:15. Queue [1-Customer:15]
            Depart  0-Customer:15, wait time was 10 minutes.  Total time so far: 31
 
  minute 25: Serving null. Queue []
 
  Total customers served = 6
  Average wait time = 5.1
  Longest wait time = 10
  Longest queue = 3
****************************************************/
