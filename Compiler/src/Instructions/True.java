package Instructions;
import ARMGen.fun;

public class True extends BoolExp{
	public String bool_name;
	public fun fun_name;
	
	public True(String bool_name, fun fun_name) {
		super(bool_name, fun_name);
		this.bool_name = bool_name;
		this.fun_name = fun_name;
	}
}

