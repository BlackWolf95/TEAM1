package Instructions;

import java.util.*;

public interface Inst_Interface {

	public enum inst_type{
		Integer_Add,
		Integer_Sub,
		Integer_Mul,
		Integer_Div,
		Assign,
		Call,
		If_Inst,
		Noop
	}
//To get the type of Instruction	
	inst_type Get_Inst_type();
//To display the list of operands of an instruction	
	public List<Object> get_operands();
//To print the instruction	
	public void print();
	
}
