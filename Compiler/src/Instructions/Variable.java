package Instructions;
import Instructions.Operands.op_type;
import Types.Type;

public class Variable {

	public String var_name;
	public Type var_type;
	
	public Variable(String var_name) {
		this.var_name = var_name;
		this.var_type = null;
	}
	public Variable(String var_name, Type var_type) {
		
		this.var_name = var_name;
		this.var_type = var_type;
	    
	}
	
	public op_type Get_Operand_Type() {
		return op_type.Variable;
	}
}
