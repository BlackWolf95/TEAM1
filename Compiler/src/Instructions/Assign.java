package Instructions;

public class Assign implements Inst_Interface {

	public Variable var;
	public Operands op;

	public Assign(Variable var, Operands op) {
		this.var= var;
		this.op = op;
	}

	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Assign;
	}
	
}
