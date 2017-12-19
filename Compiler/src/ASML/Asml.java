package ASML;
import Visiteur.ObjVisitor;
import Visiteur.Visitor;
import Expression.*;
import Tool.Id;
import Types.Type;

import java.util.List;
import java.util.*;

public class Asml {

	//list of local variables of the function
    public List<Var> local = new ArrayList<>();
    
    //list of expressions to be assigned to variables
    public List<Exp> exp = new ArrayList<>();
    
    //list of functions to be called
    public List<FunDef> function = new ArrayList<>();
    
    public Asml(List<Var> local, List<Exp> exp, List<FunDef> function){
        this.local = local;
        this.exp = exp;
        this.function = function;
    }      

    public String PrintAsml() {
    	String program;
    	int n = local.size();
    	program = "let _ =\n";
    	for (int i = 0; i < n; i++)
    	{
    		program += "let " + local.get(i) + " = "  + exp.get(i) + " in\n";
    	}
    	program += "call " + function;
        return program;
    }
    
}

