
import java.io.*;
import registers.Alloc;
import registers.Registers;
import ARMGen.fun;
import ARMGen.ARMgenerator;
import ARMGen.DataStrucConversion;
import ASML.Asml;
import ASML_Code_Generation.AM_Exp;
import ASML_Code_Generation.AM_Print_Visitor;
import ASML_Code_Generation.AM_TransVisitor;
import Alpha_conversion.Alpha_con;
import Expression.*;
import java.util.*;
import K_Nor.KNor;
import Reduction_nested.Reduction_N;
import Visiteur.*;
//import Parser.*;
import Heights.*;

public class PrintInMain {
	
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
/*		      
		      System.out.println("------ Alpha ----"); 
		      Exp expressA = expressK.accept(new Alpha_con());
		      expressA.accept(new PrintVisitor());
		      System.out.println();
     	      /*
	     	  System.out.println("------ Reduction ----"); 
			  Exp expressR = expressA.accept(new Reduction_N() );
			  expressR.accept(new PrintVisitor());
			  System.out.println();
			  
			  System.out.println("------ ASML ----"); 
			  AM_Exp expressAM = expressR.accept(new AM_TransVisitor() );
			  expressAM.accept(new AM_Print_Visitor());
			  System.out.println();
			  */
			  System.out.println("------ ARM ----"); 
              ArrayList<Registers> reg = new ArrayList<Registers>(9);
              ArrayList<Registers> argreg= new ArrayList<Registers>(2);
              Registers.reg_initialization(reg, argreg);
              
              fun func = new fun("main", new ArrayList(), new ArrayList(), reg, argreg);
              DataStrucConversion data = new DataStrucConversion();
              data.visit(expression, func);
              
              Alloc alloc = new Alloc();
              alloc.allocation(func);
              
              List<fun> funlist = new ArrayList<fun>();
              funlist.add(func);
              ARMgenerator arm = new ARMgenerator();
              arm.armgen(funlist);
              StringBuffer result= arm.textBuffer;
  	          System.out.println(result);
			  		 		   
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
