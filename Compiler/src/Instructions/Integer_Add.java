package Instructions;

public class Integer_Add implements Inst_Interface {

	public Variable var;
	public Operands op1,op2;
	
	public Integer_Add(Variable var,Operands op1, Operands op2)
	{
		this.var= var;
		this.op1= op1;
		this.op2 = op2;
	}
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Integer_Add;
	}

}
