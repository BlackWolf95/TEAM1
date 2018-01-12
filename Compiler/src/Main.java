//import java_cup.runtime.*;
import java.io.*;
//import java.util.*;

import K_Nor.KNor;
import Expression.*;
import Visiteur.*;
//import Parser.*;
import Heights.*;


public class Main {
	
  static public void main(String argv[]) { 
	  
	
	  int n = argv.length;
	  if (n == 0)
	  {
		  System.out.println("No commands to execute. -h for help");
	  }
	  else {
			  switch(argv[0])
			  {
			  case "-h":
				  System.out.println("\t-asml\t generate asml\n"
				  		+ "\t-o\t output into a file\n"
				  		+ "\t-p\t in order to get parsed AST use -p with path to file e.g. -p mincalm/adder.ml\n"
				  		+ "\t-t\t typechecking\n"
				  		+ "\t-v\t current vertion of the program\n");
				  break;
			  case "-t":
				  System.out.println("NotYetImplemented");
				  break;
			  case "-v":
				  System.out.println("TEAM1 Compiler v.1.0 Midterm submition");
				  break;
			  case "-asml":
				  System.out.println("NotYetImplemented");
				  break;
			  case "-p":
				  File f = new File(argv[1]);
				  if(f.exists()) {
					  PrintInMain.PrintAST(argv[1]);
				  }
				  else {
					  System.out.println("Wrong file path");
				  }				  
				  break;
			  case "-o":
				  if (argv[1] == null)
				  {
					  System.out.println("Provide a file name for output");
				  }
				  else
				  {
					  System.out.println("NotYetImplemented");
				  }
				  break;
			  
			   default:
				  System.out.println("Unexpected argument: " + argv[0]);
				  break;

	  		}
		  
	  }
	  
	   
  }
}

