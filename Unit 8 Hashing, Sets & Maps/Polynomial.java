// Name: Sai Parimi   
 // Date: 3/19/25
 
import java.util.*;
 
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   
   //precondition: both polynomials are in standard form
   //postcondition: terms with zero disappear. If all terms disappear (the size is zero), 
   //               add pair (0,0).
   public Polynomial add(Polynomial other);
   
   //precondition: both polynomials are in standard form
   //postcondition: terms with zero disappear. If all terms disappear (the size is zero), 
   //               add pair (0,0)
   public Polynomial multiply(Polynomial other);
   public String toString();
}
 
class Polynomial implements PolynomialInterface
{
   private TreeMap<Integer, Integer> t;
   
   public Polynomial() {
      t = new TreeMap<Integer, Integer>();
   }
   
   public void makeTerm(Integer exponent, Integer coefficient) {
      t.put(exponent, coefficient);
   }
   
   public Map<Integer, Integer> getMap() {
      return t;
   }
   
   public double evaluateAt(double d) {
      double res = 0;
      for(int exponent : t.keySet()) {
         res += t.get(exponent) * Math.pow(d, exponent);
      }
      return res;
   }
   
   public Polynomial add(Polynomial other) 
    {
        Polynomial res = new Polynomial();
        TreeSet<Integer> allExponents = new TreeSet<>(Collections.reverseOrder());
        allExponents.addAll(this.t.keySet());
        allExponents.addAll(other.getMap().keySet());
 
        for (Integer exponent : allExponents) 
        {
            int coeff1 = this.t.getOrDefault(exponent, 0);
            int coeff2 = other.getMap().getOrDefault(exponent, 0);
            int sum = coeff1 + coeff2;
            if (sum != 0) 
            {
                res.makeTerm(exponent, sum);
            }
        }
        return res;
    }
 
    public Polynomial multiply(Polynomial other) {
    Polynomial res = new Polynomial();
    for (Map.Entry<Integer, Integer> entry1 : t.entrySet()) {
        for (Map.Entry<Integer, Integer> entry2 : other.getMap().entrySet()) {
            int newExponent = entry1.getKey() + entry2.getKey();
            int newCoeff = entry1.getValue() * entry2.getValue();
            
            int updatedCoeff = res.getMap().getOrDefault(newExponent, 0) + newCoeff;
            
            if (updatedCoeff != 0) {
                res.makeTerm(newExponent, updatedCoeff);
            } else {
                res.getMap().remove(newExponent);
            }
        }
    }
    return res;
}
    public String toString() {
    if (t.isEmpty()) {return "0";}
 
    String res = "";
    boolean firstTerm = true;
 
    for (int exp : t.descendingKeySet()) { 
        int coeff = t.get(exp);
        if (coeff == 0) {
           continue;
        }
 
        if (!firstTerm) {
            if (coeff > 0) {
               res += " + ";
            }
            else {
               res += " - ";
            }
        }
        else if (coeff < 0) {
            res += "-";
        }
        
 
        if ((Math.abs(coeff) != 1) || (exp == 0)) {
            res += Math.abs(coeff);
        }
        if (exp == 1) {
            res += "x";
        }
        else if (exp > 1) {
           res += "x^" + exp;
        }
 
        firstTerm = false;
    }
    return res;
}
 
 
}
