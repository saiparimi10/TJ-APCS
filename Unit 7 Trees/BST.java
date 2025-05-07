//Name: Sai Parimi
//Date: 2/13/25
 
interface BSTinterface
{
   public int size();
   public TreeNode getRoot();
   public boolean contains(String obj);
   public void add(String obj);           //does not balance
   //public void addBalanced(String obj);  //AVL
   //public void remove(String obj);       //BST with remove
   //public void removeBalanced(String obj); //extra lab 
   public String min();
   public String max();
   public String display();
   public String toString();   //in-order traversal
}
 
/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //accessor method
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      size++;
      root = add(root, s);
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t==null) {
         return new TreeNode(s);
      } else if(s.compareTo((String)t.getValue()) <= 0) {
         t.setLeft(add(t.getLeft(),s));
      } else {
         t.setRight(add(t.getRight(),s));
      }
      return t;
      
   }
     /*************************
      Copy the display() method from TreeLab. 
      **********************/
   public String display()
   {
      return display(root, 0); 
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
      String r = "";
      if (t==null) {
         return "";
      }
      r += display(t.getRight(), level+1);
      for(int i = 0; i < level; i++) {
         r += "\t";
      }
      r += t.getValue() + "\n";
      r += display(t.getLeft(), level+1);
      return r;
   }
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t==null) {
         return false;
      }
      else if(x.equals(t.getValue())) {
         return true;
      }
      else if(x.compareTo((String)t.getValue()) <= 0) {
         return contains(t.getLeft(), x);
      }
      return contains(t.getRight(), x);
   }
   
   public String min()
   {
      return min(root); 
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t==null) {
         return null;
      }
      while(t.getLeft() != null) {
         t = t.getLeft();
      } 
      return (String)t.getValue();  
   }
   
   public String max()
   {
      return max(root); 
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t==null) {
         return null;
      }  
      else if(t.getRight() == null) {
         return (String)t.getValue();
      }
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);   
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      if(t==null) {
         return null;
      }
      return toString(t.getLeft()) + "" + t.getValue() + " " + toString(t.getRight());
   }
}
