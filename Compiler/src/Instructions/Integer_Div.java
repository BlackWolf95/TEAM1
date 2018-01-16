package Instructions;
import java.util.*;
import ARMGen.fun;

public class Integer_Div implements Inst_Interface {

	public List<Object> operandlist = new ArrayList<Object>();
//Constructor	
	public Integer_Div(fun fun, Operands op1, Operands op2)
	{
		this.operandlist.add(op1);
		this.operandlist.add(op2);

	}
//Constructor	
	public Integer_Div(fun fun, Object obj1, Object obj2)
	{
       this.operandlist.add(obj1);
       this.operandlist.add(obj2);
	}
//Get the instruction type		
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Integer_Div;
	}
//Get the list of operands
	@Override
	public List<Object> get_operands() {
		// TODO Auto-generated method stub
		return operandlist;
	}
//Print the instruction
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("DIV "+ operandlist.get(0)+" "+operandlist.get(1));
	}

}
