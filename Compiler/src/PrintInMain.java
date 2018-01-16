
import java.io.*;

import ASML.Asml;
import ASML_Code_Generation.AM_Exp;
import ASML_Code_Generation.AM_Print_Visitor;
import ASML_Code_Generation.AM_TransVisitor;
import Alpha_conversion.Alpha_con;
import Closure_Conversion.Closure_Con;
import Expression.*;
import ARMGen.*;

import K_Nor.KNor;
import Reduction_nested.Reduction_N;
import Visiteur.*;
//import Parser.*;
import Heights.*;

public class PrintInMain {
	
	public  static StringBuffer outp  = new StringBuffer();
	
	public static void PrintAST(String path)
	{
		try {
		      Parser p = new Parser(new Lexer(new FileReader(path)));
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
     	      System.out.println();
		      
		      System.out.println("------ Alpha ----"); 
		      Exp expressA = expressK.accept(new Alpha_con());
		      expressA.accept(new PrintVisitor());
		      System.out.println();
     	      
	     	  System.out.println("------ Reduction ----"); 
			  Exp expressR = expressA.accept(new Reduction_N() );
			  expressR.accept(new PrintVisitor());
			  System.out.println();
			  


		    System.out.println("------ Closure ----"); 
			  Exp expressC = expressA.accept(new Closure_Con() );
			  expressC.accept(new PrintVisitor());
			  System.out.println();
      
      System.out.println("------ ASML ----"); 
			 // AM_Exp expressAM = expressR.accept(new AM_TransVisitor() );
			  //expressAM.accept(new AM_Print_Visitor());
			  System.out.println();
			  		 		   
			  System.out.println("------ ARM ----");
			  ARMgenerator arm = new ARMgenerator();
			  arm.outputARM(outp);
			  System.out.println(outp);
				  
			  ObjVisitor<Integer> v = new HeightVisitor();
		      height = expression.accept(v);
		      System.out.println("using HeightVisitor: " + height);

		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}

	public static void Help ()
	{
		
	}
}
