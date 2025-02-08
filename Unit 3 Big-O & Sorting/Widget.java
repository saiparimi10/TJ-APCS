// Name: Sai Parimi
// Date: 11/26/24
 
public class Widget implements Comparable<Widget>
{
   //fields
   private int cubits;
   private int hands;
   
   //constructors
   public Widget() {
      this.cubits = 0;
      this.hands = 0;
   }
   public Widget(int cubits, int hands) {
      this.cubits = cubits;
      this.hands = hands;
   }
   public Widget(Widget other) {
      this.cubits = other.cubits;
      this.hands = other.hands;
   }
   
   //accessors and modifiers
   public int getCubits() { return cubits; }
   public int getHands() { return hands; }
   public void setCubits(int cubits) { this.cubits = cubits; }
   public void setHands(int hands) { this.hands = hands; }
   
   //compareTo(Widget) and equals(Widget)
   public int compareTo(Widget other) {
      if (this.cubits != other.cubits) {
         return this.cubits - other.hands;
      }
      return this.hands - other.hands;
   }
 
   public boolean equals(Widget object) {
      if (this == object) {
         return true;
      }
      if (object == null || getClass() != object.getClass()) {
         return false;
      }
      Widget other = (Widget) object;
      return this.cubits == other.cubits && this.hands == other.hands;
   }
   //toString
   public String toString() {
      return cubits + " cubits " + hands + " hands";
   }
}
