import java.util.*; 

class ICG1
{ 
	// A utility function to return precedence of a given operator 
	// Higher returned value means higher precedence 
	static int Prec(char ch) 
	{ 
		switch (ch) 
		{ 
		case '+': 
		case '-': 
			return 1; 
	
		case '*': 
		case '/': 
			return 2; 
	
		case '^': 
			return 3; 
		} 
		return -1; 
	} 
	static String infixToPostfix(String exp) 
	{ 
		// initializing empty String for result 
		String result = new String(""); 
		
		// initializing empty stack 
		Stack<Character> stack = new Stack<>(); 
		
		for (int i = 0; i<exp.length(); ++i) 
		{ 
			char c = exp.charAt(i); 
			
			// If the scanned character is an operand, add it to output. 
			if (Character.isLetterOrDigit(c)) 
				result += c; 
			
			// If the scanned character is an '(', push it to the stack. 
			else if (c == '(') 
				stack.push(c); 
			
			// If the scanned character is an ')', pop and output from the stack 
			// until an '(' is encountered. 
			else if (c == ')') 
			{ 
				while (!stack.isEmpty() && stack.peek() != '(') 
					result += stack.pop(); 
				
				if (!stack.isEmpty() && stack.peek() != '(') 
					return "Invalid Expression"; // invalid expression				 
				else
					stack.pop(); 
			} 
			else // an operator is encountered 
			{ 
				while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) 
					result += stack.pop(); 
				stack.push(c); 
			} 
	
		} 
		while (!stack.isEmpty()) 
			result += stack.pop(); 
	
		return result; 
	} 
	public static void main(String[] args) 
	{  
		System.out.println("Enter expression");
		Scanner sc = new Scanner(System.in);
                String infix=sc.nextLine();
                String post = infixToPostfix(infix);
		int count = 1;
		String extra = "";
		int i=0;
		while(i<post.length()-3)
		{
			String left = Character.toString(post.charAt(i++));
			if(Character.isDigit(post.charAt(i)))
				{left = post.substring(0,2);
  				i++;}
			System.out.println("Left "+left);
  			String right = Character.toString(post.charAt(i++));
			if(Character.isDigit(post.charAt(i)))
				{right = post.substring(2,4);
  				i++;}
			System.out.println("Right "+right);
			if(Character.isLetterOrDigit(post.charAt(i)))
				{ 
  				  if(extra.length()==0)
				   {
  				    extra = post.substring(0,2);
				     post = post.substring(2,post.length()); }
				  else
				   { extra +=post.substring(0,3);
				     post = post.substring(3,post.length());}
				  i=0;
				  System.out.println("Extra "+extra);
				 continue;
				}
			String t1 = left+Character.toString(post.charAt(i))+right;
			String p = Integer.toString(count);
			p = "t"+p;
			//System.out.println("Temp used "+p);
			System.out.println("\t\t\t"+p+" = "+t1);
		      if (Character.isDigit(post.charAt(1)) &&Character.isDigit(post.charAt(3)))
				post = p+post.substring(5,post.length());
			else if(Character.isDigit(post.charAt(1)))
			post = p+post.substring(4,post.length());
			else
			post = p+post.substring(3,post.length());			
                        System.out.println("Modified expr "+post);
			count++;
			i=0;
		 if(post.length()==3 && extra.length()>2)
			{
			 post = extra+post;
			 extra = "a";  System.out.println("Modified expr "+post);}
		}
		if(post.length()>2&& extra.length()>1)
		{
		String p = Integer.toString(count);
			p = "t"+p;
		String right = post.substring(0,2);
		post = post.substring(2,post.length());
		System.out.println("\t\t\t"+p+"="+extra+post+right); }

	} 
} 
