package Instructions;

import java.util.ArrayList;
import java.util.List;

import ARMGen.fun;

public class Assign implements Inst_Interface {
	
	public List<Object> operandlist = new ArrayList<Object>();
//Assignment of two operands
	public Assign(fun fun, Operands op1, Operands op2)
	{
		this.operandlist.add(op1);
		this.operandlist.add(op2);

	}
//Assignment of any two objects	
	public Assign(fun fun, Object obj1, Object obj2)
	{
       this.operandlist.add(obj1);
       this.operandlist.add(obj2);
	}
//Assignment of instruction to a variable
	public Assign(fun fun, Variable var, Inst_Interface inst)
	{
       this.operandlist.add(var);
       this.operandlist.add(inst);
	}
//Get the instruction type
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Assign;
	}
//Get the list of operands
	@Override
	public List<Object> get_operands() {
		// TODO Auto-generated method stub
		List<Object> operandlist2 = new ArrayList<Object>();
		operandlist2.add(operandlist.get(0));
		if(operandlist.get(1) instanceof Inst_Interface) {  //if second operand is an instruction
			operandlist2.add(((Inst_Interface)(operandlist.get(1))).get_operands().get(0));   //get the operands of inner instruction
			operandlist2.add(((Inst_Interface)(operandlist.get(1))).get_operands().get(1));   //get the operands of inner instruction
		}
	    return operandlist2;
	}
//Print the instruction 
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(operandlist.get(0) + " := "+ operandlist.get(1));
	}
	
}
