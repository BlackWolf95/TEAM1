import java_cup.runtime.*;
import java.io.*;
import java.util.*;


public class Main {
	
  static public void main(String argv[]) { 
	  int n = argv.length;
	  if (n == 0)
	  {
		  System.out.println("No commands to execute. -h for help");
	  }
	  else {
		  for (int i=0; i < n; i++)
		  { 
			  String arg = argv[i];
			  switch(arg)
			  {
			  case "-h":
				  System.out.println("\t-p\t in order to get parsed AST use -p with path to file e.g. -p /home/pc/adder.ml");
				  break;
			  case "-t":
				  System.out.println("NotYetImplemented");
				  break;
			  case "-v":
				  System.out.println("NotYetImplemented");
			  	  break;
			  case "-asml":
				  System.out.println("NotYetImplemented");
				  break;
			  case "-p":
				  PrintInMain.PrintAST(argv[i+1]);
				  break;
			  case "-o":
				  if (i == n)
				  {
					  System.out.println("Provide a file name for output");
				  }
				  else
				  {
					  System.out.println("NotYetImplemented");
				  }
				  break;
			  
			   default:
				  System.out.println("Unexpected argument: " + arg);
				  break;
			  }
	  		}
	  }
  }
}

