package Instructions;

public class Integer_Op implements Operands  {

	public int val;
	public Integer_Op(int val) {
		this.val = val;
	}
	@Override
	public op_type Get_Operand_Type() {
		// TODO Auto-generated method stub
		return op_type.Integer;
	}

}
