// Name: Sai Parimi
// Date: 9/20/24
public class Palindrome
{
   private String mySentence;
 
   //Precondition:  str is not empty.
   public Palindrome( String str )
   { 
      mySentence = str;
   }
   public String getSentence()
   {
      return mySentence; 
   }
 
//Returns a string of mySentence with all blanks removed.
//Postcondition:  Returned string contains just one word.
   public String removeBlanks()
   {  
      return mySentence.replace(" ", "");
   }
 
//Returns a string of mySentence with all letters in lowercase.
//Postcondition:  Number of words is not changed.
   public String lowerCase()
   {  
       String result =  mySentence.toLowerCase();
       return result;
   }
 
//Returns a string of mySentence with all punctuation removed.
//Postcondition:  Number of words is not changed.
   public String removePunctuation( )
   { 
      String result = mySentence.replaceAll("\\p{Punct}", "");
      return result;
   }
   
   //cleans up mySentence
   //prints the cleaned-up mySentence
   //returns true if mySentence is a palindrome, false otherwise.
   //calls the 3-arg isPalindrome(String, int, int)
   public boolean isPalindrome()
   {
      mySentence = removeBlanks();
      mySentence = lowerCase();
      mySentence = removePunctuation();
      System.out.println(mySentence);
      return isPalindrome(mySentence, 0, mySentence.length()-1);
   }
	
	//Precondition: s has no blanks, no punctuation, and is in lower case.
	//Recursive method. Returns true if s is a palindrome, false otherwise.
   public boolean isPalindrome( String s, int start, int end )
   {
      if (start >= end) 
         return true; 
 
      if (s.charAt(start) != s.charAt(end)) {
         return false; 
      }
      return isPalindrome(s, start + 1, end - 1); 
   }
}
