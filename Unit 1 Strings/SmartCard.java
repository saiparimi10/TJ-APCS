//Name: Sai Parimi   
//Date: 9/13/24
 
import java.text.DecimalFormat;
 
public class SmartCard 
{
   // instantiate the constants
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   // declare the private fields
   private String formattedBalance;
   private double balance;
   private boolean boarded;
   private Station s;
   
   // write the one-arg constructor
   public SmartCard(double givenBalance) {
      balance = givenBalance;
      s = null;
      formattedBalance = df.format(balance);
   }
   
   // write four getter methods 
   public double getBalance() {
      return balance;
   }
   
   public String getFormattedBalance() {
      return formattedBalance;
   }
   
   public boolean getIsBoarded() {
      return boarded;
   }
   
   public Station getBoardedAt() {
      return s;
   }
    
   // write the instance methods as described in the handout
   public void board(Station s) {
     
      if (getIsBoarded() == true) {
         System.out.println("Error: already boarded?!");
      }
      else if (balance < 0.50) {
         System.out.println("Insufficient funds to board. Please add more money.");
      }
      else {
         this.s = s;
         boarded = true;
      } 
     
   }
   
   public double cost(Station s) {
     
      int startZone = this.s.getZone();
      int endZone = s.getZone();
      if (Math.abs(startZone-endZone) == 0) {
         return 0.50;
      }
      else if (Math.abs(startZone-endZone) == 1) {
         return 1.25;
      }
      else if (Math.abs(startZone-endZone) == 2) {
         return 2.00;
      }
      else if (Math.abs(startZone-endZone) == 3) {
         return 2.75;
      }
      return 1;
     
   }
   
   public void exit(Station s) {
     
      if (getIsBoarded() == false) {
         System.out.println("Error: Did not board?!");
      }
      else if (cost(s) > balance) {
         System.out.println("Insufficient funds to exit. Please add more money.");
      }
      else {
         balance = balance - cost(s);
         formattedBalance = df.format(balance);
         boarded = false;
         System.out.println("From " + this.s + " to " + s + " costs $" + cost(s) + ". SmartCard has " + formattedBalance + ".");
         this.s = null;
      }
     
   }

   public void addMoney(double d) {
     
      balance = balance + d;
      formattedBalance = df.format(balance);
      System.out.println("$"+d+ " added. Your new balance is " + formattedBalance);
     
   }

}
