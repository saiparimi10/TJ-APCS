// Name: Sai Parimi
// Date: 3/4/25
 
interface BSTinterface
{
   public int size();
   public TreeNode getRoot() ;
   public boolean contains(String obj);
   public void add(String obj);          //does not balance
   public void addBalanced(String obj);  //AVL
   public void remove(String obj);       //does not re-balance
   //public void removeBalanced(String obj); //extension
   public String min();
   public String max();
   public String display();
   public String toString();
}
 
public class BST_AVL implements BSTinterface
{
   private TreeNode root;
   private int size;
   
   public BST_AVL()
   {
      root = null;
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
   
   public TreeNode getRoot()  // accessor method
   {
      return root;
   }
   
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   
   private TreeNode add(TreeNode t, String s) // recursive helper method
   {      
      if (t == null) return new TreeNode(s);
      if (s.compareTo(t.getValue().toString()) < 0) 
         t.setLeft(add(t.getLeft(), s));
      else 
         t.setRight(add(t.getRight(), s));
      return t;
   }
   
   public boolean contains(String obj)
   {
      return contains(root, obj);
   }
   
   private boolean contains(TreeNode t, String x) // recursive helper method
   {
      if (t == null) return false;
      if (x.equals(t.getValue())) return true;
      if (x.compareTo((String)t.getValue()) < 0) return contains(t.getLeft(), x);
      return contains(t.getRight(), x);
   }
   
   public String min()
   {
      return min(root); 
   }
   
   private String min(TreeNode t)  // use iteration
   {
      if (t == null) return null;
      while (t.getLeft() != null) t = t.getLeft();
      return (String)t.getValue();  
   }
   
   public String max()
   {
      return max(root); 
   }
   
   private String max(TreeNode t)  // recursive helper method
   {
      if (t == null) return null;
      if (t.getRight() == null) return (String)t.getValue();
      return max(t.getRight());
   }
   
   /*************************
    Copy the display() method from TreeLab. 
    **********************/
   public String display()
   {
      return display(root, 0); 
   }
   
   private String display(TreeNode t, int level) // recursive helper method
   {
      if (t == null) return "";
      String r = display(t.getRight(), level + 1);
      r += "\t".repeat(level) + t.getValue() + "\n";
      r += display(t.getLeft(), level + 1);
      return r;
   }
   
   public String toString()
   {
      return toString(root);   
   }
   
   private String toString(TreeNode t)  // an in-order traversal. Use recursion.
   {
      if (t == null) return "";
      return toString(t.getLeft()) + t.getValue() + " " + toString(t.getRight());
   }
   
   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   
   private TreeNode remove(TreeNode current, String target)
   {
      if (current == null) return null;
      if (target.equals(current.getValue()))
      {
         if (current.getLeft() == null) return current.getRight();
         if (current.getRight() == null) return current.getLeft();
         String maxv = max(current.getLeft());
         current.setValue(maxv);
         current.setLeft(remove(current.getLeft(), maxv));
      }
      else if (target.compareTo((String)current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else
         current.setRight(remove(current.getRight(), target));
      return current;
   }
   
   private int height(TreeNode current)   // from TreeLab
   {
      if (current == null) return -1;
      return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
   }
   
   private int calcBalance(TreeNode current) // height to right minus height to left
   {
      return height(current.getRight()) - height(current.getLeft());
   }
   
   public void addBalanced(String value)  
   {
      root = addBalanced(root, value);
      size++;
   }
   
   // helper method for second approach
   private TreeNode addBalanced(TreeNode t, String strObj)
   {
      if (t == null) return new TreeNode(strObj);
      if (strObj.compareTo((String)t.getValue()) < 0) 
         t.setLeft(addBalanced(t.getLeft(), strObj));
      else 
         t.setRight(addBalanced(t.getRight(), strObj));
      return balanceTree(t);
   }
   
   /*  start the addBalanced methods */
   private TreeNode balanceTree(TreeNode t)   
   {
      if (t == null) return null;
      int balance = calcBalance(t);
      if (balance > 1)
      {
         if (calcBalance(t.getRight()) < 0) 
            t.setRight(right(t.getRight()));
         return left(t);
      }
      if (balance < -1)
      {
         if (calcBalance(t.getLeft()) > 0) 
            t.setLeft(left(t.getLeft()));
         return right(t);
      }
      return t;
   }
   
   /*  write the four rotation methods   */
   private TreeNode left(TreeNode t) {
      TreeNode c = t.getRight();
      t.setRight(c.getLeft());
      c.setLeft(t);
      return c;
   }
   private TreeNode right(TreeNode t) {
      TreeNode c = t.getLeft();
      t.setLeft(c.getRight());
      c.setRight(t);
      return c;
   }
   private TreeNode doubleLeft(TreeNode t) {
      t.setRight(right(t.getRight()));
      return left(t);
   }
   private TreeNode doubleRight(TreeNode t) {
      t.setLeft(left(t.getLeft()));
      return right(t);
   }
}
