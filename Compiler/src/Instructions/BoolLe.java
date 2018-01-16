package Instructions;
import ARMGen.fun;
import java.util.*;

public class BoolLe extends BoolExp {
	String bool_name;
	fun fun_name;
	public List<Object> operandlist = new ArrayList<Object>();
//Constructor with Boolean variable name, function and operands	
	public BoolLe(String bool_name, fun fun_name, Object obj1, Object obj2) {
		super(bool_name, fun_name);												//Calling BoolExp constructor
		this.bool_name = bool_name;
		this.fun_name = fun_name;
		operandlist.add(obj1);
		operandlist.add(obj2);				
	}
}
