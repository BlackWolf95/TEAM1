package Instructions;

public interface Operands {

	enum op_type 
	{
		Variable,
		Integer,
		Label
	}
	
	op_type Get_Operand_Type();
}
