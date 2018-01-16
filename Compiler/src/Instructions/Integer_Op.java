package Instructions;
//import java.util.*;
import ARMGen.fun;
//import registers.*;

public class Integer_Op extends Variable  {

	public int val;   //value of int variable
	public Integer_Op(String var_name, int val, fun fun) {
		super(var_name, fun);  //Calling Variable class constructor
		this.val = val;
	}
//Get the value of Integer Variable
	public Integer get_val() {
		return val;
	}

}
