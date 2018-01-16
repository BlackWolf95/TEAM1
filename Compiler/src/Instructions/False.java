package Instructions;
import ARMGen.fun;

public class False extends BoolExp{
	public String bool_name;
	public fun fun_name;
	
	public False(String bool_name, fun fun_name) {
		super(bool_name, fun_name);
		this.bool_name = bool_name;
		this.fun_name = fun_name;
	}
}

