import java.io.*;
import java.util.*;

public class GCA
{
	public static void main(String [] args)
	{
		GCA g = new GCA();
		System.out.println("Enter the no.of vertices (1 to ?)");
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		System.out.println("Enter no.of edges");
		int e = Integer.parseInt(sc.nextLine());
		System.out.println("Enter the vertices of edges(x;y)");
		ArrayList<Integer>[] adj = new ArrayList[n+1];  
		int[] count = new int[n+1];
	        for (int i = 0; i < n+1; i++) 
           		 {count[i] = 0;  adj[i] = new ArrayList(); }
		for(int i=0;i<e;i++)
		{
		 String s = sc.nextLine();
		 String[] s1 = s.split(";");
		 int a1 = Integer.parseInt(s1[0]);
		 int a2 = Integer.parseInt(s1[1]);
		 adj[a1].add(a2);
		 adj[a2].add(a1);
		 count[a1]++;
		 count[a2]++;
		}
	System.out.println("Enter no.of colours");
	int clr  = Integer.parseInt(sc.nextLine());
	Stack<Integer> stack = new Stack<Integer>();
	int c=0;
	while(c<n)
	{
 	 int b = g.small(count,stack);
	 count[b] = 100;
	 stack.push(b);
	 Iterator itr = adj[b].iterator();
	 while(itr.hasNext())
  	{ int p = (int)itr.next();
		int v = count[p];  count[p]--;}
	c++;
	}
	int[] clrn = new int[n+1];
	for(int i=1;i<n+1;i++)
		clrn[i] = 0;
	 c = 0;
	while(!stack.empty())
	{
	 int n1 = stack.pop();
	 if(adj[n1].size() > clr)
		{
			System.out.println("Colouring not possible");
			System.exit(0);
		}
	 c = 1;
	 int flag=0;
	 while(c!=0 && flag==0)
	 {
	 Iterator it1 = adj[n1].iterator();
	 while(it1.hasNext())
		{
                 if(clrn[(int)it1.next()]==c) // clr matches with neighbour
			 break;
		 if(!it1.hasNext()) // present clr doesn't match with any neighbour
			flag=1;
		}
	 if(flag==0)
		c = (c+1)%(clr+1);
         }
	if(c==0)
	{
		System.out.println("Colouring not possible");
		System.exit(0);
	}
	clrn[n1] = c;
	}
	System.out.println("Colours for graph nodes are:");
	for(int i=1;i<=n;i++)
		System.out.println("Node"+i+" Colour :"+clrn[i]);	  
}
 int small(int[] a,Stack b)
 {
     int j=0; 
     int k = 100;
	for(int i=1;i<a.length;i++)
	{
		if((a[i]<k) && !b.contains(i))
		 {
		   j=i;
		   k=a[j];
		}
	}
    return j;
}
}
