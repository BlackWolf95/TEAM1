package Instructions;

public class Label implements Inst_Interface,Operands {

	public static int count = 0;
	
	public String label_name;

	public Label(String label_name) {
		this.label_name = label_name;
	}
	
	public static Label next() {
		return new Label("l"+count++);
	}
	
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Label;
	}

	@Override
	public op_type Get_Operand_Type() {
		// TODO Auto-generated method stub
		return op_type.Label;
	}

}
