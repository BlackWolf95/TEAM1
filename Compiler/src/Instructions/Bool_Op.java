package Instructions;
//import  java.util.*;
import ARMGen.fun;

public class Bool_Op extends Variable{
	public BoolExp boolexp;
//Constructor with boolean variable name, expression and function	
	public Bool_Op(String bool_name, BoolExp boolexp, fun fun_name) {
		super(bool_name, fun_name);   									//Calling variable class constructor
		this.boolexp = boolexp;
	}
//Constructor with boolean variable name, bool value and function	
	public Bool_Op(String bool_name, boolean bln, fun fun_name) {
		super(bool_name, fun_name);                                    //Calling variable class constructor
		if(bln) {
			this.boolexp = new True(bool_name, fun_name);	           //If bool value true
		}
		else {
			this.boolexp = new False(bool_name, fun_name);             //If bool value false
		}
	}
//Return boolean expression	
	public BoolExp get_expression() {
		return boolexp;
	}
}
