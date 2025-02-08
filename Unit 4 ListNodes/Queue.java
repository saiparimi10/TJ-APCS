// Name: Sai Parimi
// Date: 12/9/24
import java.io.*;
import java.util.*;
 
public class Queue
{
    private ListNode first;
    private ListNode last;
    // constructor
 
    public Queue() {
        first = null;
        last = null;
    }
 
    public void add(Object obj) {
        if(last == null) {
            last = new ListNode(obj, null);
            first = last;
        }
        else {
            last.setNext(new ListNode(obj, null));
            last = last.getNext();
        }
    }
 
    public Object remove() {
        if(first == null) { return null;}
        ListNode temp = first;
        first = first.getNext();
        if(first == null) { last = null; }
        return temp.getValue();
    }
 
    public Object peek() {
        if(first == null) {
            return null;
        }
        return first.getValue();
    }
 
    public boolean isEmpty() {
        if(first == null) 
            return true; 
        return false;
    }
 
    public String toString() {
        if (first == null) {
            return "[]";
        }
        String res = "[";
        for(ListNode p = first; p.getNext() != null; p=p.getNext())
            res += p.getValue().toString() + ", ";
        return res + last.getValue().toString() + ']';
    }
}
