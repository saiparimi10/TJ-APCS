// Name: Sai Parimi
// Date: 12/9/24
import java.io.*;
import java.util.*;
 
 
public class Stack {
    private ListNode top;
    public Stack() {
        top = null;
    }
    // .....
    public Object peek() {
        if(top == null) {
            throw new EmptyStackException();
        }
        return top.getValue();
    }
 
    public void push(Object item) {
        top = new ListNode(item, top);
    }
 
    public boolean isEmpty() {
        if(top == null) 
            return true; 
        return false;
    }
 
    public Object pop() {
        if(top == null) {
            throw new EmptyStackException();
        }
        Object temp = top.getValue();
        top = top.getNext();
        return temp;
    }
 
    public String toString() {
        if (top == null) {
            return "[]";
        }
        String res = "[";
        ListNode current = top;
        Stack temp = new Stack();
        while(current != null) {
            temp.push(current.getValue());
            current = current.getNext();
        }
        boolean f = true;
        while(!temp.isEmpty()) {
            if (!f) 
                res += ", ";
            res += temp.pop().toString();
            f = false;
        }
        res += "]";
        return res;
    }
 
}
