package Instructions;

public class Jump implements Inst_Interface{

	public Label label;
	
	public Jump(Label label) {
		this.label = label;
	}
	
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Jump;
	}

}
