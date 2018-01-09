package Instructions;
import java.util.List;
import java.util.Collections;

public class Call implements Inst_Interface{

	public Variable retval;
	public List<Operands> arguments;
	public String name;
	
	public Call(Variable retval, List<Operands> arguments, String name) {
		this.retval = retval;
		this.arguments = Collections.unmodifiableList(arguments);
		this.name = name;
	}
	
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Call;
	}

}
