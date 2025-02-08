// Name: Sai Parimi
// Date: 1/23/25
 
import java.util.*;
 
public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *"); 
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");   
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
 
 
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<String> stk = new Stack<String>();
      for(String str : postfixParts) {
         if(!isOperator(str)) { 
            stk.push(str); 
         } else if (str.equals("!")) { 
            double d = Double.parseDouble(stk.pop());
            stk.push("" + factorial(d));
         } else {
            double d = Double.parseDouble(stk.pop());
            double d2 = Double.parseDouble(stk.pop());
            stk.push("" + eval(d,d2,str));
         }
      }
      return Double.parseDouble(stk.pop());
   }
   
   public static double eval(double a, double b, String op)
   {
      if(op.equals("+"))
      {
         return b + a;
      }
      if(op.equals("-"))
      {
         return b - a;
      }
      if(op.equals("/"))
      {
         return b / a;
      }
      if(op.equals("*"))
      {
         return b * a;
      }
      if(op.equals("%"))
      {
         return b % a;
      }
      if(op.equals("^"))
      {
         return Math.pow(b, a);
      }
      return 0;
   }
   
   public static double factorial(double f) {
      if (f==1 || f==0) {
         return 1;
      }
      double res = 1;
      for (double i = f; i > 1; i--) {
         res *= i;
      }
      return res;
   }
   
   public static boolean isOperator(String op)
   {
      return (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("^") || op.equals("!") || op.equals("%"));
   }
}
 
/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/
