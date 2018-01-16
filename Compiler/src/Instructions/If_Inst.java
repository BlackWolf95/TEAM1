package Instructions;
import ARMGen.fun;

import java.util.ArrayList;
import java.util.List;

public class If_Inst implements Inst_Interface {

	public Bool_Op cond;  //condition
	public fun fun_then;  //branch1
	public fun fun_else;  //branch2
//Constructor	
	public If_Inst(Bool_Op cond, fun fun_then, fun fun_else) {
		this.cond = cond;
		this.fun_then = fun_then;
		this.fun_else = fun_else;
	}
//Get the instruction type	
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.If_Inst;
	}
//Get the list of operands
	@Override
	public List<Object> get_operands() {
		// TODO Auto-generated method stub
		List<Object> operandlist = new ArrayList<Object>();
        for (Inst_Interface instr : this.fun_then.get_instruction()) {    //get the instructions of first branch
                for (Object obj : instr.get_operands()) {                 // get the operands of the instruction
                        operandlist.add(obj);                             //add to the operand list
                }
        }
        for (Inst_Interface instr : this.fun_else.get_instruction()) {   //get the instructions of the second branch
                for (Object obj : instr.get_operands()) {                //get the operands of the instruction
                        operandlist.add(obj);                            //add to the operand list
                }
        }
        return operandlist;
}
//Print the instruction
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("If " + this.cond + " then " + this.fun_then + " else " + this.fun_else);		
	}

}
