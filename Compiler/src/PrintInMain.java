
import java.io.*;

import K_Nor.KNor;


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
		      
		      System.out.println("123");
//		      ObjVisitor<Exp> r = (ObjVisitor<Exp>) new KNor();
//		      Exp expKnor = expression.accept(r);
		      

		      System.out.println("------ Height of the AST ----");
		      int height = Height.computeHeight(expression);
		      System.out.println("using Height.computeHeight: " + height);

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
