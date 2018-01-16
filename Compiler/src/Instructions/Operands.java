package Instructions;

public interface Operands {

	enum op_type 
	{
		Variable,
		Integer
	}
	
	op_type Get_Operand_Type();  
}
