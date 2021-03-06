import java.io.*;
class RegexEngine
{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        //	Entering the text on which we have to check
        
        System.out.println("Enter the text for Regex Pattern");
        String text = in.readLine();
        
        //	Entering the pattern to check the entered text
        
        System.out.println("Enter the pattern for Regex Engine");
        String pattern = in.readLine();
        
        //calling the match function
        
        System.out.println(new RegexEngine().match(pattern,text)||new RegexEngine().search(pattern, text));
        
    }   
    
    
    boolean matchThePattern(String pattern,String text)
    {
        if(pattern==null||pattern.isEmpty())//If any text matches an empty pattern
        {
            return true;
        }
        if(text==null||text.isEmpty())//If the pattern is defined but the text is not defined than there is no match
        {
            return false;
        }
        if(pattern.equalsIgnoreCase("."))//"."dot character acts as a wild-card any pattern matches with it
        {
        	return true;
        }
        return pattern.equals(text);
    }
    
    boolean match(String pattern,String text)
    {
    	if(pattern=="") //	Base Case to check if the Inputted pattern is Empty, than any inputted text is a match 
    	{
    		return true;
    	}
    	
    	//For $ character
    	
    	else if(pattern.equalsIgnoreCase("$")&&text.isEmpty())
		{
			return true;
    	}
    	
    	//	we are looking at 2nd letter of the pattern not the first
    	//	first character is to match whether is it 0 or 1
    	else if(pattern.length()>1)
    	{
	    	if(pattern.charAt(1)=='?')
			{
				return matchQuestionMark(pattern,text);
	    	}
	    	else if(pattern.charAt(1)=='*')
	    	{
	    		return matchStarChar(pattern,text);
	    	}
    	}
    	String pTemp = "";
    	String tTemp = "";
    	
    	//	For extracting the left pattern length
    	
    	for(int i=0;i<pattern.length();i++)
    	{
    		char ch = pattern.charAt(i);
    		if(i!=0)
    		{
    			pTemp+=ch;
    		}
    	}
    	
    	//	
    	
    	for(int i=0;i<text.length();i++)
    	{
    		char ch = text.charAt(i);
    		if(i!=0)
    		{
    			tTemp+=ch;
    		}
    	}
    		return matchThePattern(String.valueOf(pattern.charAt(0)),String.valueOf(text.charAt(0)))&&match(pTemp,tTemp);
    }
    
    //	For ^ character
    
    boolean search(String pattern,String text)
    {
    	String pTemp = "";
    	if(pattern.charAt(0)=='^')
    	{
    		for(int i=0;i<pattern.length();i++){
    			char ch = pattern.charAt(i);
    			if(i!=0)
    			{
    				pTemp+=ch;
    			}
    		}
    		return match(pTemp,text);
    	}
    	
    	
    	// This means that we test the pattern against every starting point of the text.
    	
    	
    	
    	/*
    	 *	for(int i=0;i<text.length();i++)
    	 *		return match(pattern,text.substring(i));
    	 *	return true;
    	 *
    	 */
    	
    	else
    	{
    		return match(".*" + pattern, text);
    	}
    }

    //	For ? character
    
    boolean matchQuestionMark(String pattern,String text)
    {
    	String pTemp = "";
    	String tTemp = "";
    	
    	//for extracting the pattern from index 2
    	
    	for(int i=0;i<pattern.length();i++){
    		char ch = pattern.charAt(i);
    		if(i!=0||i!=1)
    		{
    			pTemp+=ch;
    		}
    	}
    	
    	//For extracting the text from index 1
    	
    	for(int i=0;i<text.length();i++){
    		char ch = text.charAt(i);
    		if(i!=0)
    		{
    			tTemp+=ch;
    		}
    	}
    	
    	return matchThePattern(String.valueOf((pattern.charAt(0))),String.valueOf(text.charAt(0)))&&match(pTemp,tTemp)||(match(pTemp,text));
    	
    }
    
    //	For * character
    
    boolean matchStarChar(String pattern,String text)
    {
    	
    	//for extracting the pattern from index 2
    	
    	String pTemp = "";
    	for(int i=0;i<pattern.length();i++){
    		char ch = pattern.charAt(i);
    		if(i!=0||i!=1)
    		{
    			pTemp+=ch;
    		}
    	}
    	

    	
    	//For extracting the text from index 1
    	
    	String tTemp = "";
    	for(int i=0;i<text.length();i++){
    		char ch = text.charAt(i);
    		if(i!=0)
    		{
    			tTemp+=ch;
    		}
    	}
    	
    	return matchThePattern(String.valueOf(pattern.charAt(0)),String.valueOf(text.charAt(0)))&&match(pattern,tTemp)||(match(pTemp,text));
    }
}
