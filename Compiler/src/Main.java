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
	  
	  try {
	      Parser p = new Parser(new Lexer(new FileReader(argv[0])));
	      Exp expression = (Exp) p.parse().value;      
	      assert (expression != null);

	      System.out.println("------ AST ------");
	      expression.accept(new PrintVisitor());
	      System.out.println();						

	      System.out.println("------ Height of the AST ----");
	      int height = Height.computeHeight(expression);
	      System.out.println("using Height.computeHeight: " + height);
	      
	      System.out.println("------ K-norm ----");
	      Exp expressK = expression.accept(new KNor());
	      expressK.accept(new PrintVisitor());
	      
	      //ObjVisitor<Exp> r = (ObjVisitor<Exp>) new KNor();
	      //Exp expKnor = expression.accept(r);
	      //expKnor.accept(new PrintVisitor());
 
	      ObjVisitor<Integer> v = new HeightVisitor();
	      height = expression.accept(v);
	      System.out.println("using HeightVisitor: " + height);

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	 /* int n = argv.length;
	  if (n == 0)
	  {
		  System.out.println("No commands to execute. -h for help");
	  }
	  else {
			  switch(argv[0])
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
	  */
	   
  }
}

