// Name: Sai Parimi
// Date: 12/16/24
 
//  DoubleLinkedList, circular, with a dummy head node
//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.
 
class DLL  
{
   private int size;
   private DLNode head; //points to a dummy node--very useful--don't mess with it
   public DLL()  
   {
      head = new DLNode(null, null, null); 
      head.setNext(head); 
      head.setPrev(head);
      size = 0;
   } 
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode c = head;
      if (index < size / 2) {
         for (int i = 0; i <= index; i++) 
            c = c.getNext();   
      } else {
         for (int i = size; i > index; i--) 
            c = c.getPrev();  
      }
      DLNode newNode = new DLNode(obj, c.getPrev(), c);
      c.getPrev().setNext(newNode);
      c.setPrev(newNode);
      size++;               
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode c = head.getNext();
      for (int i = 0; i < index; i++) 
         c = c.getNext();
      return c.getValue();
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode c = head.getNext();
      for (int i = 0; i < index; i++) 
         c = c.getNext();
      Object old = c.getValue();
      c.setValue(obj);
      return old;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode c = head.getNext();
      for (int i = 0; i < index; i++) 
         c = c.getNext();
      c.getPrev().setNext(c.getNext());
      c.getNext().setPrev(c.getPrev());
      size--;
      return c.getValue();  
 
   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      DLNode newNode = new DLNode(obj, head, head.getNext());
      head.getNext().setPrev(newNode);
      head.setNext(newNode);
      size++;
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      DLNode newNode = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(newNode);
      head.setPrev(newNode);
      size++;
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst() {
      DLNode firstNode = head.getNext();
      Object value = firstNode.getValue();
      head.setNext(firstNode.getNext()); 
      firstNode.getNext().setPrev(head); 
      size--;
      return value; 
}
 
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      DLNode lastNode = head.getPrev();
      Object value = lastNode.getValue();
      head.setPrev(lastNode.getPrev()); 
      lastNode.getPrev().setNext(head); 
      size--; 
      return value; 
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
       An empty list returns [].
    */
   public String toString()
   {
      if(size==0) 
         return "[]";
      String res = "[";
      DLNode c = head.getNext();
      while (c != head) {
         res += c.getValue(); 
         if (c.getNext() != head) 
            res += ", "; 
         c = c.getNext(); 
      }
      res += "]"; 
      return res;
   }
}
