package Instructions;

public class Return implements Inst_Interface{

	public Operands op;
	
	public Return(Operands op) {
		this.op = op;
	}
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Return;
	}

}
