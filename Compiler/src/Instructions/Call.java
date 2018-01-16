package Instructions;
import java.util.List;
import java.util.ArrayList;

public class Call implements Inst_Interface{

	public List<Object> operandlist = new ArrayList<Object>();
	public String ret;
	public List<Object> arguments;
	public String name;
//Constructor	
	public Call(List<Object> arguments, String name) {
		this.arguments = arguments;
		this.name = name;
		this.ret = "r0";
	}
//Constructor	
	public Call(String ret , List<Object> arguments, String name) {
		this.ret = ret;
		this.arguments = arguments;
		this.name = name;
	}
//Get the type of the instruction
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Call;
	}
//Get the operands list
	@Override
	public List<Object> get_operands() {
		// TODO Auto-generated method stub
		operandlist.add(arguments.get(0));
		return operandlist;
	}
//Print the instruction
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("CALL: "+name);
		
	}
//get the return value	
	public String get_return() {
		return ret;
	}
//Get the name 
	public String get_name() {
		return name;
	}
//Get the arguments	
	public List<Object> get_args(){
		return arguments;
	}
}
