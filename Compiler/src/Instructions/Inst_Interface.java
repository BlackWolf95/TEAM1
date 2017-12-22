package Instructions;

public interface Inst_Interface {

	public enum inst_type{
		Integer_Add,
		Integer_Sub,
		Label,
		Jump,
		Assign,
		Return,
		Call
	}
	
	inst_type Get_Inst_type();
}
