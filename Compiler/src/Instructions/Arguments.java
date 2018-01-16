package Instructions;
import ARMGen.fun;
//import java.util.*;
//import registers.*;

public class Arguments extends Variable{
	
	public String register;
	
	public Arguments(String name, String register, fun fun) {
		super(name,fun);          //Calling Variable class constructor
		this.register = register;
	}
//Get the argument register	
	public String get_arg_reg() {
		return register;
	}
	
}
