//import java_cup.runtime.*;
import java.io.*;
//import java.util.*;

import K_Nor.KNor;
import Expression.*;
import Visiteur.*;
//import Parser.*;
import Heights.*;
import registers.Alloc;
import registers.Registers;


public class Main {
	
	private static String output = "output.s";
	
  static public void main(String argv[]) { 
	  
	
	  int n = argv.length;
	  int i =0;
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
			  
		  case "-v":
			  System.out.println("TEAM1 Compiler v.1.1 Final submition");
			  break;
			  
		  default:
				  break;
		  }
		  
		  while (i < n)
		  {
			  switch(argv[i])
			  {
			  
			  case "-t":
				  System.out.println("NotYetImplemented");
				  break;
				  
			  case "-arm":
				  File f2 = new File(argv[1]);
				  if(f2.exists()) {
					  PrintInMain.PrintARM(argv[1]);
				  }
				  else {
					  System.out.println("Wrong file path");
				  }				  
				  break;
			  
			  case "-asml":
				  File f1 = new File(argv[1]);
				  if(f1.exists()) {
					  PrintInMain.PrintASMl(argv[1]);
				  }
				  else {
					  System.out.println("Wrong file path");
				  }		
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
				  i++;
				  if(argv[i].startsWith("-"))
					{
						System.out.println("Provide a file name for output");
					}
							  
				  output = argv[i];
				  try 
				  {
		             PrintStream out = new PrintStream(new FileOutputStream(output));
		            
		            // out.println("NotYetImplemented");
		             PrintInMain.PrintFileASMl(output);
		          } 
				  catch (FileNotFoundException e)
				  {
					  System.out.println("Provide a file name for output: " + e.getMessage());
		              
		          }
			  
			   default:
				 // System.out.println("Unexpected argument: " + argv[0]);
				  break;
	  		}
			  i++;
		  }
		  
	  }
	  
	   
  }
}

